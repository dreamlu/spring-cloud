package com.deercoder.common.util.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeUtil {

	private static final String CHARSET = "UTF-8";

	/**
	 * 普通二维码创建
	 *
	 * @param contents
	 * @param width
	 * @param height
	 * @param response
	 * @return
	 */
	public Void encode(String contents, int width, int height, HttpServletResponse response) {
		//生成条形码时的一些配置
		Map<EncodeHintType, Object> hints = new HashMap<>();
		// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 内容所使用字符集编码
		//hints.put(EncodeHintType.CHARACTER_SET, CHARSET);

		BitMatrix bitMatrix;
		try {
			OutputStream out = response.getOutputStream();// 不生成文件, 直接返回 new FileOutputStream(qrFile);
			// 生成二维码
			bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public File encodeWithLogo(File qrFile, File logoFile, File newQrFile) {
		OutputStream os = null;
		try {
			Image         image2      = ImageIO.read(qrFile);
			int           width       = image2.getWidth(null);
			int           height      = image2.getHeight(null);
			BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			//BufferedImage bufferImage =ImageIO.read(image) ;
			Graphics2D g2 = bufferImage.createGraphics();
			g2.drawImage(image2, 0, 0, width, height, null);
			int matrixWidth = bufferImage.getWidth();
			int matrixHeigh = bufferImage.getHeight();

			//读取Logo图片
			BufferedImage logo = ImageIO.read(logoFile);
			//开始绘制图片
			g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);//绘制
			BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			g2.setStroke(stroke);// 设置笔画对象
			//指定弧度的圆角矩形
			RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, 20, 20);
			g2.setColor(Color.white);
			g2.draw(round);// 绘制圆弧矩形

			//设置logo 有一道灰色边框
			BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			g2.setStroke(stroke2);// 设置笔画对象
			RoundRectangle2D.Float round2 = new RoundRectangle2D.Float((matrixWidth / 5 * 2) + 2, matrixHeigh / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
			g2.setColor(new Color(128, 128, 128));
			g2.draw(round2);// 绘制圆弧矩形

			g2.dispose();

			bufferImage.flush();
			os = new FileOutputStream(newQrFile);
			ImageIO.write(bufferImage, "jpg", os);
			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return newQrFile;
	}

	/**
	 * ZXing解码
	 *
	 * @param qrFile
	 * @return
	 */
	public String decode(File qrFile) {
		BufferedImage image  = null;
		Result        result = null;
		try {
			image = ImageIO.read(qrFile);
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap    bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Map<DecodeHintType, Object> hints = new HashMap<>();
			hints.put(DecodeHintType.CHARACTER_SET, CHARSET);

			result = new MultiFormatReader().decode(bitmap, hints);
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.getText();
	}
}
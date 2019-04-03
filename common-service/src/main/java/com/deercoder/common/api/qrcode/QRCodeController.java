package com.deercoder.common.api.qrcode;

import com.deercoder.common.util.qrcode.QRCodeUtil;
import com.deercoder.commons.lib.Lib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/qrcode")
public class QRCodeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String uploadPath = "/images";
	private int    width      = 300;
	private int    height     = 300;

	@Autowired
	private QRCodeUtil qrCodeUtil;

	/**
	 * 获取二维码
	 *
	 * @param uri
	 * @param response
	 * @return
	 */
	@GetMapping("/encode")
	@ResponseBody
	public Object encode(String uri, HttpServletResponse response) {

		if (uri == null || uri.equals("")) {
			return Lib.GetMapData(Lib.CodeText, "uri不能为空");
		}
		qrCodeUtil.encode(uri, width, height, response);
		return null;
	}

	@GetMapping("/decode")
	@ResponseBody
	public Object decodeQR(String uri, HttpServletResponse response) {

//        String realUploadPath = request.getServletContext().getRealPath(uploadPath) ;
//        File uploadDir = new File(realUploadPath);
//
//        String imageName = String.format("%s_qr.png", userId);
//        File qrFile = new File(uploadDir, imageName);
//        String result = qrCodeUtil.decode(qrFile);
//
//        ModelAndView mv = new ModelAndView("decode_qr") ;
//        mv.addObject("result", result);
		return null;
	}
}
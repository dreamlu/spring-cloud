package com.deercoder.common.api.wx.pay;

import com.deercoder.common.config.wx.WxPayConfiguration;
import com.deercoder.common.model.wx.AppletEntPay;
import com.deercoder.commons.api.GetInfoN;
import com.deercoder.commons.lib.Lib;
import com.github.binarywang.wxpay.bean.entpay.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.deercoder.commons.util.Util.randNum;

/**
 * 企业付款相关接口
 */
@Slf4j
@RequestMapping("/wx/pay")
@RestController
public class EntPayController {

	/**
	 * <pre>
	 * 企业付款业务是基于微信支付商户平台的资金管理能力，为了协助商户方便地实现企业向个人付款，针对部分有开发能力的商户，提供通过API完成企业付款的功能。
	 * 比如目前的保险行业向客户退保、给付、理赔。
	 * 企业付款将使用商户的可用余额，需确保可用余额充足。查看可用余额、充值、提现请登录商户平台“资金管理”https://pay.weixin.qq.com/进行操作。
	 * 注意：与商户微信支付收款资金并非同一账户，需要单独充值。
	 * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
	 * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers
	 * </pre>
	 *
	 * @param applet 请求对象
	 */
	//@ApiOperation(value = "企业付款到零钱")
	@PostMapping("/entPay")
	public GetInfoN entPay(@RequestBody AppletEntPay applet) throws WxPayException {

		EntPayRequest request = new EntPayRequest();
		request.setAppid(applet.getAppId());
		request.setOpenid(applet.getOpenId());
		request.setAmount(applet.getAmount());

		// 订单号
		String partnerTradeNo = System.nanoTime() + randNum(6);
		request.setNonceStr("通知地址uri");
		request.setPartnerTradeNo(partnerTradeNo);    // 订单号
		//request.setOpenid();
		request.setCheckName("NO_CHECK"); // 校验用户姓名选项
		request.setDescription("提现到零钱"); // 备注
		request.setSpbillCreateIp("127.0.0.1");
		new Thread(() -> {
			//无返回值的业务代码
			try {
				WxPayConfiguration.getWxPayService(request.getAppid()).getEntPayService().entPay(request);
			} catch (Exception e) {
				log.error(e.getMessage());
				//return Lib.GetMapData(Lib.CodeError, Lib.MsgWithDrawError, e.getMessage());
			}

		}).start();

		Map<String, Object> map = new HashMap<>();
		map.put("partnerTradeNo", partnerTradeNo);
		return Lib.GetMapDataSuccess(map);
	}

	/**
	 * <pre>
	 * 查询企业付款API
	 * 用于商户的企业付款操作进行结果查询，返回付款操作详细结果。
	 * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_3
	 * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo
	 * </pre>
	 *
	 * @param partnerTradeNo 商户订单号
	 */
	//@ApiOperation(value = "查询企业付款到零钱的结果")
	@GetMapping("/queryEntPay")
	public Object queryEntPay(String appId, String partnerTradeNo) throws WxPayException {
		EntPayQueryResult entPayQueryResult = WxPayConfiguration.getWxPayService(appId).getEntPayService().queryEntPay(partnerTradeNo);
		if (!entPayQueryResult.getStatus().equals("SUCCESS")) {
			return Lib.GetMapData(Lib.CodeError, Lib.MsgError, new HashMap<String, String>() {
						{
							put("status", entPayQueryResult.getStatus());
						}
					}
			);

		}
		return Lib.MapSuccess;
	}
//
//
//  /**
//   * <pre>
//   * 获取RSA加密公钥API.
//   * RSA算法使用说明（非对称加密算法，算法采用RSA/ECB/OAEPPadding模式）
//   * 1、 调用获取RSA公钥API获取RSA公钥，落地成本地文件，假设为public.pem
//   * 2、 确定public.pem文件的存放路径，同时修改代码中文件的输入路径，加载RSA公钥
//   * 3、 用标准的RSA加密库对敏感信息进行加密，选择RSA_PKCS1_OAEP_PADDING填充模式
//   * （eg：Java的填充方式要选 " RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING"）
//   * 4、 得到进行rsa加密并转base64之后的密文
//   * 5、 将密文传给微信侧相应字段，如付款接口（enc_bank_no/enc_true_name）
//   *
//   * 接口默认输出PKCS#1格式的公钥，商户需根据自己开发的语言选择公钥格式
//   * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=24_7&index=4
//   * 接口链接：https://fraud.mch.weixin.qq.com/risk/getpublickey
//   * </pre>
//   *
//   * @return the public key
//   * @throws WxPayException the wx pay exception
//   */
//  //@ApiOperation(value = "获取RSA加密公钥")
//  @GetMapping("/getPublicKey")
//  public String getPublicKey() throws WxPayException {
//    return this.wxService.getEntPayService().getPublicKey();
//  }
//

	/**
	 * 企业付款到银行卡.
	 * <pre>
	 * 用于企业向微信用户银行卡付款
	 * 目前支持接口API的方式向指定微信用户的银行卡付款。
	 * 文档详见：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=24_2
	 * 接口链接：https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank
	 * </pre>
	 *
	 * @param applet 请求对象
	 * @return the ent pay bank result
	 * @throws WxPayException the wx pay exception
	 */
	//@ApiOperation(value = "企业付款到银行卡")
	@PostMapping("/payBank")
	public Object payBank(@RequestBody AppletEntPay applet) throws Exception {

		EntPayBankRequest request = new EntPayBankRequest();
		request.setAppid(applet.getAppId());
		request.setAmount(applet.getAmount());
		request.setEncBankNo(applet.getEncBankNo());
		request.setEncTrueName(applet.getEncTrueName());
		request.setBankCode(applet.getBankCode());

		// 订单号
		String partnerTradeNo = System.nanoTime() + randNum(6);
		request.setNonceStr("通知地址uri");
		request.setPartnerTradeNo(partnerTradeNo);    // 订单号
		//request.setOpenid();
		request.setDescription("提现到银行卡"); // 备注
		new Thread(() -> {
			//无返回值的业务代码
			try {
				WxPayService wxPayService = WxPayConfiguration.getWxPayService(request.getAppid());

				// 这里签名bug
				// 根据文档 appid 不参与签名
				//wxPayService.getConfig().setAppId(null);
				request.setAppid(null);
				wxPayService.getEntPayService().payBank(request);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}).start();

		Map<String, Object> map = new HashMap<>();
		map.put("partnerTradeNo", partnerTradeNo);
		return Lib.GetMapDataSuccess(map);
	}

	/**
	 * 企业付款到银行卡查询.
	 * <pre>
	 * 用于对商户企业付款到银行卡操作进行结果查询，返回付款操作详细结果。
	 * 文档详见：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=24_3
	 * 接口链接：https://api.mch.weixin.qq.com/mmpaysptrans/query_bank
	 * </pre>
	 *
	 * @param partnerTradeNo 商户订单号
	 * @return the ent pay bank query result
	 * @throws WxPayException the wx pay exception
	 */
	//@ApiOperation(value = "查询企业付款到银行卡的结果")
	@GetMapping("/queryPayBank")
	public Object queryPayBank(String appid, String partnerTradeNo) throws WxPayException {
		EntPayBankQueryResult entPayBankQueryResult = WxPayConfiguration.getWxPayService(appid).getEntPayService().queryPayBank(partnerTradeNo);

		if (!entPayBankQueryResult.getStatus().equals("SUCCESS")) {
			return Lib.GetMapData(Lib.CodeError, Lib.MsgError, new HashMap<String, String>() {
						{
							put("status", entPayBankQueryResult.getStatus());
						}
					}
			);

		}
		return Lib.MapSuccess;
	}

}
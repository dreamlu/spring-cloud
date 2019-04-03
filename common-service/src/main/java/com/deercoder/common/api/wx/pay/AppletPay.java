package com.deercoder.common.api.wx.pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 小程序支付所需参数
 * @author dreamlu
 * @date 2019/03/19
 */
@Data
public class AppletPay {

	private String timeStamp;// 时间戳

	private String nonceStr;// 随机串

	@XStreamAlias("package")
	@JsonProperty("package")
	private String repay_id;

	private String signType;// 签名方式

	private String paySign; // 签名
}

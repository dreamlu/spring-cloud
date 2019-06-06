package com.deercoder.common.model.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 退款model 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 *
 * @author dreamlu
 * @date 2019/06/03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refund {

	private String appId;

	private Integer totalFee;

	private String outTradeNo;

	private Integer refundFee;

	private String refundDesc;
}

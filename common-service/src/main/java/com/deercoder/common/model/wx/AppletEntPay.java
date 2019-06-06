package com.deercoder.common.model.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小程序提现相关参数
 *
 * @author dreamlu
 * @date 2019/03/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppletEntPay {

	// appId
	private String appId;

	// 大小写统一问题
	private String openId;

	// 金额, 分
	private Integer amount;

	// ====== 以下为提现至银行卡时额外参数======
	// 银行卡号
	private String encBankNo;

	// 收款人姓名
	private String encTrueName;

	// 银行代码
	private String bankCode;
}

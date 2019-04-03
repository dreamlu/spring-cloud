package com.deercoder.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信相关
 * @author dreamlu
 * @date 2019/03/22
 */
@Data
@NoArgsConstructor
public class Sms {

	// 手机号/电话
	private String phone;

	// 验证码
	private String code;
}

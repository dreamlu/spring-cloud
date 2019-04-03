package com.deercoder.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小程序相关参数
 * @author dreamlu
 * @date 2019/03/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applet {

	// appid
	private String appid;

	// code
	private String code;

	private String sessionKey;

	private String signature;

	private String rawData;

	private String encryptedData;

	private String iv;
}

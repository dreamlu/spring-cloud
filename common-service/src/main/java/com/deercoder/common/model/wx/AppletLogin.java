package com.deercoder.common.model.wx;

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
public class AppletLogin {

	private String sessionKey;

	private String openId;

	private String unionId;
}

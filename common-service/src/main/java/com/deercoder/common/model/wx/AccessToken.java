package com.deercoder.common.model.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dreamlu
 * @date 2019/04/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {

	private String access_token;

	private String expires_in;

	private String errcode;

	private String errmsg;
}

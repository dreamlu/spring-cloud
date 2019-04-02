package com.deercoder.commons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * token model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel {
	// 用户 id
	private Long   uid;
	// 随机生成的 uuid
	private String token;
}

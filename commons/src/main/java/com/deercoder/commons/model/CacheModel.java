package com.deercoder.commons.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 缓存model
 * author: dreamlu
 */
@JsonDeserialize    // jackjson异常问题
@NoArgsConstructor  // 解决方案： https://blog.csdn.net/u010429286/article/details/78395775
@Data
@AllArgsConstructor
public class CacheModel implements Serializable {
	// 时间, 分钟
	private Long   time;
	// 数据
	private Object data;

}

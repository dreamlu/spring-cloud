package com.deercoder.commons.lib.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * jpa 实体类转换器
 * author: dreamlu
 */
public class JpaJson implements AttributeConverter<Object, String> {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	// 自定义类型替代Object
	// error: jpa @Convert(converter = xxx.class), 不支持表达式
	// private Class<T> valueType;

	// 写
	@Override
	public String convertToDatabaseColumn(Object meta) {
		try {
			return objectMapper.writeValueAsString(meta);
		} catch (JsonProcessingException ex) {
			return null;
			// or throw an error
		}
	}

	// 读
	@Override
	public Object convertToEntityAttribute(String dbData) {
		try {
			// 数据为null, 不转化
			if (dbData == null) {
				return null;
			}
			return objectMapper.readValue(dbData, Object.class); // 对应类型.class
		} catch (IOException ex) {
			System.out.println("json 转换异常: " + dbData);
			return null;
		}
	}
}
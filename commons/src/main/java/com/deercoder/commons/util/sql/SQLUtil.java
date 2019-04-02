package com.deercoder.commons.util.sql;

import com.deercoder.commons.util.hump.Hump;

import java.lang.reflect.Field;

/**
 * sql 相关处理
 * @author dreamlu
 * @date 2019/03/22
 */
public class SQLUtil {

	/**
	 * 根据model类, 获得对应的select *中 *的替代sql
	 * @param model
	 * @return
	 */
	public static String getSQLColumn(Class<?> model){
		Field[] fields = model.getFields();
		StringBuffer sb = new StringBuffer();

		for(Field field : fields) {
			sb.append(fields);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);

		return sb.toString();
	}

	/**
	 * 驼峰转换
	 * 根据model类, 获得对应的select *中 *的替代sql
	 * @param model
	 * @return
	 */
	public static String getSQLColumnHump(Class<?> model){
		return Hump.humpToLine(getSQLColumn(model));
	}
}

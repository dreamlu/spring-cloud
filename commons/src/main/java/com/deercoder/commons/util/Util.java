package com.deercoder.commons.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;

/**
 * util 小工具类
 * @author dreamlu
 */
public class Util {

	/**
	 * 随机数
	 *
	 * @param len
	 * @return
	 */
	public static String randNum(Integer len) {

		return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1)));
	}

	/**
	 * 根据属性名获取属性值
	 * 不考虑从祖先类继承的属性,只获取当前类属性,包括四类访问权限,private,protect,default,public
	 *
	 * @param fieldName 字段名
	 * @param object    操作对象
	 * @return
	 */
	public static Object getFieldValue(String fieldName, Object object) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			//设置对象的访问权限，保证对private的属性的访问
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {

			return null;
		}
	}


	/**
	 * 给对象的属性值赋值
	 * 注: 暂无反射删除方法
	 *
	 * @param fieldName 字段名
	 * @param value     字段值
	 * @param object    操作对象
	 * @return
	 */
	public static Boolean setFieldValue(String fieldName, Object value, Object object) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			//设置对象的访问权限，保证对private的属性的访问
			field.setAccessible(true);
			field.set(object, value);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * 获取对象中指定的json字段对象, 待测试
	 *
	 * @param fieldName 字段名
	 * @param data      指定对象
	 * @return
	 */
	public static Object getJsonData(String fieldName, Object data) throws JSONException {

		// list 类型
		if (data instanceof List) {

			JSONArray jsonArray = new JSONArray(data);

			return jsonArray.toJSONObject(jsonArray).get(fieldName);
		} else {
			JSONObject jsonData = new JSONObject(data);
			return jsonData.get(fieldName);
		}
	}

	/**
	 * 判断对象以及内容是否为空
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isObjEmpty(Object obj) {
		try {
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);

				if (f.get(obj) != null) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
					return false;
				}
			}
			return true;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return false;
	}
}

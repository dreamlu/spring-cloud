package com.wbkjcom.commons.lib;

/**
 * des: 空字符串转null
 */
public class Field {
	public static void EmptyToNull(Object obj) {
		//		// 取得name属性对应的Field对象
//		Field[] fields = shop.getClass().getDeclaredFields();
//		for (Field field : fields)
//		{
//			// 对于每个属性，获取属性名
//			String varName = field.getName();
//			try
//			{
//				// 取消属性的访问权限控制，即使private属性也可以进行访问
//				boolean access = field.isAccessible();
//				if(!access) field.setAccessible(true);
//
//				//从obj中获取field变量
//				Object o = field.get(shop);
//				System.out.println("变量： " + varName + " = " + o);
//
//				if(o == "") {
//					try {
//						// 调用set()方法给对应属性赋值
//						field.set(o, new Object[]{null});
//					}catch (IllegalArgumentException e){
//						e.printStackTrace();
//					}
//				}
//
//				if(!access) field.setAccessible(false);
//			}
//			catch (Exception ex)
//			{
//				ex.printStackTrace();
//			}
//		}
	}
}

package com.ly.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

public class BeanUtils {

	static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将请求参数转换设置到bean的各个字段中
	 * @param request
	 * @param t
	 * @return
	 */
	public static <T> T toBean(ServletRequest request, T t) {
		//输入的值对象t为空则退出
		if (t == null)
			return t;
		for (Field f : t.getClass().getDeclaredFields()) {
			//获取参数值
			String svalue = request.getParameter(f.getName());
			//空值跳过
			if (svalue == null)
				continue;
			try {
				//声明转换后的值变量
				Object ovalue = cast(svalue, f.getType());
				f.setAccessible(true);
				f.set(t, ovalue);
			} catch (Exception e) {
				System.err.println(
						"请求参数解析错误：参数名=" + f.getName() + "，字段类型=" + f.getType() + "，参数值=" + svalue);
			}
		}

		return t;

	}

	public static <T> T asBean(ServletRequest request, Class<T> cls) {
		try {
			return toBean(request, cls.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static Map<Object, Object> asMap(Object... objs) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		for (int i = 0; i < objs.length / 2; i++) {
			map.put(objs[i * 2], objs[i * 2 + 1]);
		}
		return map;
	}

	/**
	 * 将字符串转成指定的类型，如果 输入值为空，或者转换过程出现异常，则返回默认值
	 * @param svalue
	 * @param cls
	 * @param defaultValue 默认值
	 * @return
	 */
	public static <T> T cast(String svalue, Class<T> cls, T defaultValue) {
		if (svalue == null) {
			return defaultValue;
		} else {
			try {
				T ret = cast(svalue, cls);
				return ret == null ? defaultValue : ret;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				return defaultValue;
			}
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * 将字符串转成指定的类型
	 * @param svalue
	 * @param cls
	 * @return
	 */
	public static <T> T cast(String svalue, Class<T> cls) throws ParseException {
		//空值跳过
		if (svalue == null)
			return null;
		//声明转换后的值变量
		T ovalue = null;
		//判断字段的类型
		switch (cls.getName().toLowerCase()) {
		case "java.lang.string":
			ovalue = (T) svalue;
			break;
		case "java.lang.byte":
			ovalue = (T) Byte.valueOf(Byte.parseByte(svalue));
			break;
		case "java.lang.short":
			ovalue = (T) Short.valueOf(Short.parseShort(svalue));
			break;
		case "java.lang.integer":
			ovalue = (T) Integer.valueOf(Integer.parseInt(svalue));
			break;
		case "java.lang.long":
			ovalue = (T) Long.valueOf(Long.parseLong(svalue));
			break;
		case "java.lang.float":
			ovalue = (T) Float.valueOf(Float.parseFloat(svalue));
			break;
		case "java.lang.double":
			ovalue = (T) Double.valueOf(Double.parseDouble(svalue));
			break;
		case "java.lang.boolean":
			ovalue = (T) Boolean.valueOf(Boolean.parseBoolean(svalue));
			break;
		case "java.util.date":
			ovalue = (T) new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(svalue);
			break;
		case "java.sql.date":
			ovalue = (T) new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(svalue);
			ovalue = (T) new java.sql.Date(((Date) ovalue).getTime());
			break;
		case "java.sql.timestamp":
			ovalue = (T) new SimpleDateFormat(DEFAULT_DATETIME_FORMAT).parse(svalue);
			ovalue = (T) new Timestamp(((Date) ovalue).getTime());
			break;
		case "java.lang.character":
			ovalue = (T) Character.valueOf(svalue.charAt(0));
			break;
		}
		return ovalue;
	}

	public static boolean canCast(Class<?> cls) {
		if (cls.isPrimitive()) {
			return true;
		} else {
			switch (cls.getName().toLowerCase()) {
			case "java.lang.string":
			case "java.lang.integer":
			case "java.lang.long":
			case "java.lang.boolean":
			case "java.util.date":
			case "java.sql.date":
			case "java.sql.timestamp":
			case "java.lang.float":
			case "java.lang.double":
			case "java.lang.character":
			case "java.lang.byte":
			case "java.lang.short":
				return true;
			default:
				return false;
			}
		}
	}

	/**
	 * 根据方法名返回匹配的方法对象
	 * @param cls
	 * @param methodName
	 * @return
	 */
	public static Method[] getMethodsByName(Class<?> cls, String methodName) {
		ArrayList<Method> list = new ArrayList<Method>();
		for (Method m : cls.getDeclaredMethods()) {
			if (m.getName().equals(methodName)) {
				m.setAccessible(true);
				list.add(m);
			}
		}
		return list.toArray(new Method[list.size()]);

	}

}

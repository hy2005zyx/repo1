package com.ly.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
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
				System.err.println("请求参数解析错误：参数名=" + f.getName() + "，字段类型=" + f.getType() + "，参数值=" + svalue);
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
	public static <T> T cast(String svalue, Class<T> cls) {
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
		case "java.lang.character":
			ovalue = (T) Character.valueOf(svalue.charAt(0));
			break;
		case "java.sql.date":		// 注意：接收日期类型有格式的问题
			ovalue = (T) Date.valueOf(svalue);
			break;
		case "java.sql.timestamp":	// 注意：接收日期类型有格式的问题
			ovalue = (T) Timestamp.valueOf(svalue);
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

	/**
	 * 返回第一个不为空的值
	 * @param values
	 * @return
	 */
	public static Object notNull(Object... values) {
		for (Object o : values) {
			if (o != null) {
				return o;
			}
		}
		return null;
	}

	/**
	 * 从对象中获取一个字段值
	 * @param bean		对象
	 * @param fieldName	字段名
	 * @return			字段值
	 */
	public static Object getValue(Object bean, String fieldName) {
		Class<?> cls = bean.getClass();
		try {
			// 获取字段
			Field field = cls.getDeclaredField(fieldName);
			// 设置字段可以直接访问
			field.setAccessible(true);
			// 返回字段值
			return field.get(bean);
		} catch (NoSuchFieldException | SecurityException e) {
			IOUtils.println("%s没有这个字段：%s", cls.getName(), fieldName);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			IOUtils.println("%s无法或该字段值：%s", cls.getName(), fieldName);
		}
		return null;
	}

	/**
	 * 迭代一个对象，该对象可以是：数组、集合、Map、实体对象
	 * @param items
	 * @param fields
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Iterable<Object> each(Object items, Object... fields) {
		if (items instanceof Collection) {
			return ((Collection) items);
		}

		int tempCount;
		if (items instanceof Object[]) {
			tempCount = ((Object[]) items).length;
		} else if (fields.length == 1 && fields[0] != null && fields[0] instanceof Collection) {
			fields = ((Collection) fields[0]).toArray();
			tempCount = fields.length;
		} else {
			tempCount = fields.length;
		}

		final int _count = tempCount;
		final Object[] _fields = fields;

		/**
		 * 对 items 进行迭代
		 */
		return new Iterable<Object>() {
			@Override
			public Iterator<Object> iterator() {
				return new Iterator<Object>() {
					int i = 0;

					@Override
					public boolean hasNext() {
						return i < _count;
					}

					@Override
					public Object next() {
						Object ret;
						if (items instanceof Object[]) {
							ret = ((Object[]) items)[i];
						} else if (items instanceof Map) {
							ret = ((Map) items).get(_fields[i]);
						} else {
							ret = getValue(items, "" + _fields[i]);
						}
						i++;
						return ret;
					}
				};
			}

		};
	}

}

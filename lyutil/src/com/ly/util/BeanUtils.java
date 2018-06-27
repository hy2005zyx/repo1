package com.ly.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;

public class BeanUtils {

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
	 * 支持类型：	8种基本数据类型、String、java.math.BigDecimal、
	 * 			java.sql.Date、java.sql.Timestamp
	 * @param svalue
	 * @param cls
	 * @return
	 */
	public static <T> T cast(String svalue, Class<T> cls) {
		//空值跳过
		if (svalue != null)
			//判断字段的类型
			switch (cls.getName()) {
			case "java.lang.String":
				return (T) svalue;
			case "int":
			case "java.lang.Integer":
				return (T) Integer.valueOf(svalue);
			case "long":
			case "java.lang.Long":
				return (T) Long.valueOf(svalue);
			case "float":
			case "java.lang.Float":
				return (T) Float.valueOf(svalue);
			case "double":
			case "java.lang.Double":
				return (T) Double.valueOf(svalue);
			case "boolean":
			case "java.lang.Boolean":
				return (T) Boolean.valueOf(svalue);
			case "java.sql.Date":
				return (T) java.sql.Date.valueOf(svalue);
			case "java.sql.Timestamp":
				return (T) Timestamp.valueOf(svalue);
			case "java.math.BigDecimal":
				return (T) new java.math.BigDecimal(svalue);
			case "byte":
			case "java.lang.Byte":
				return (T) Byte.valueOf(svalue);
			case "short":
			case "java.lang.Short":
				return (T) Short.valueOf(svalue);
			case "char":
			case "java.lang.Character":
				return svalue.length() > 1 ? (T) Character.valueOf(svalue.charAt(0)) : null;
			}
		return null;
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

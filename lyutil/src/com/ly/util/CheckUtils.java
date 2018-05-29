package com.ly.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CheckUtils<T extends Exception> {

	/**
	 * 允许为空
	 */
	public static final String ALLOW_EMPTY = null;

	/**
	 * 判断对象是否为空或空字符串
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		} else if (o instanceof String) {
			return ((String) o).trim().isEmpty();
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串长度是否超过范围
	 * @param string 判断长度的字符串
	 * @param min 最小长度，为0不判断
	 * @param max 最大长度，为0不判断
	 * @return
	 */
	public static boolean isOutBound(String string, int min, int max) {
		if (min > 0 && string.trim().length() < min || max > 0 && string.trim().length() > max) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据正则表达式判断字符串是否合法，不合法则抛出异常
	 * @param string 要判断的字符串
	 * @param emptyMsg 字符串为空的信息，如果该参数为空
	 * @param regexWithMsg 正则表达式验证格式数组，格式如下：
	 * 			{验证表达式1,错误信息1,验证表达式2,错误信息2,验证表达式3,错误信息3……}
	 */
	public static <T extends Exception> void check(Object value, Class<T> cls, String emptyMsg,
			String... regexWithMsg) throws T {
		String msg = check(value, emptyMsg, regexWithMsg);
		if (msg != null) {
			throwException(cls, msg);
		}
	}

	/**
	 * 根据正则表达式判断字符串是否合法，不合法则返回报错信息
	 * @param string 要判断的字符串
	 * @param emptyMsg 字符串为空的信息，如果该参数为空
	 * @param regexWithMsg 正则表达式验证格式数组，格式如下：
	 * 			{验证表达式1,错误信息1,验证表达式2,错误信息2,验证表达式3,错误信息3……}
	 */
	public static <T extends Exception> String check(Object value, String emptyMsg,
			String... regexWithMsg) throws T {
		// 判断输入值为空，空错误消息不为空
		if (isEmpty(value) && emptyMsg != null) {
			//字符串为空输出的信息
			return emptyMsg;
		} else {
			String string = "" + value;
			for (int i = 0; i < regexWithMsg.length - 1; i += 2) {
				if (!string.matches(regexWithMsg[i])) {
					//不匹配则返回对应信息（数组下一个元素就是报错信息）
					return regexWithMsg[i + 1];
				}
			}
		}
		return null;
	}

	/**
	 * 输入的信息不为空，则抛出异常
	 * @param cls
	 * @param msg
	 * @throws T
	 */
	private static <T extends Exception> void throwException(Class<T> cls, String msg) throws T {
		Constructor<T> c;
		try {
			c = cls.getDeclaredConstructor(String.class);
			throw c.newInstance(msg);
		} catch (NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}

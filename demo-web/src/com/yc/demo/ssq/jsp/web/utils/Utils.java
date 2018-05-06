package com.yc.demo.ssq.jsp.web.utils;

public class Utils {

	public static String replaceAll(String str, String regex, Object... objs) {
		int i = 0;
		while (str.matches(".*" + regex + ".*")) {
			str = str.replaceFirst(regex, "" + objs[i++ % objs.length]);
		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(replaceAll("sjk?ksf??j","\\?",1,2,3));
	}
}

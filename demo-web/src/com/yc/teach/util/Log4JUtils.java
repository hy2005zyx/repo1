package com.yc.teach.util;

import org.apache.log4j.Logger;

import sun.reflect.Reflection;

public class Log4JUtils {

	public static Logger getLogger() {
		return Logger.getLogger(Reflection.getCallerClass().getName());
	}
}

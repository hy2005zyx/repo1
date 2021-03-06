package com.ly.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String MM_DD = "MM-dd";
	public static final String MM_SS = "mm:ss";

	public static String format(String fmt, Date date) {
		return new SimpleDateFormat(fmt).format(date);
	}

	public static String format(String fmt) {
		return new SimpleDateFormat(fmt).format(new Date());
	}

}

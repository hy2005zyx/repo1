package com.yc.common.util;

public class DBHelperTest {

	public static void main(String[] args) {
		System.out.println(DBHelper.findAll("select * from menu", null));
	}

}

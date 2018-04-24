package com.yc.common.util;

import com.yc.bean.User;
import com.yc.dao.DBHelper;

public class DBHelperTest {

	public static void main(String[] args) {
		DBHelper dbh = new DBHelper();
		dbh.doUpdate("insert into user (name) values ('111')", null);
		System.out.println(dbh.find("select * from user", null, User.class));
		
	}

}

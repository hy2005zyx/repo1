package com.yc.common.util;

import java.util.Date;

import com.yc.teach.bean.User;
import com.yc.teach.util.DBHelper;

public class DBHelperTest {

	public static void main(String[] args) {
		/*DBHelper.doUpdate("insert into user (name,pwd,email,tel,sex,birthday) values (?,?,?,?,?,?)", 
				"test", "123", "111@qq.com", "123456", 1, new Date());*/
		System.out.println(DBHelper.find("select * from user", User.class));

	}

}

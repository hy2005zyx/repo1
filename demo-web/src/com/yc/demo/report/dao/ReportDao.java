package com.yc.demo.report.dao;

import java.util.List;
import java.util.Map;

import com.yc.teach.util.DBHelper;

public class ReportDao {
	public List<Map<String, Object>> selectByMonth(String year, String month) {
		String sql = "select * from lottery where substr(opendate,1,7)=?";
		String date = year + "-" + month;
		return DBHelper.findAll(sql, date);
	}
}

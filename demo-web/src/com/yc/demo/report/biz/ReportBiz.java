package com.yc.demo.report.biz;

import java.util.List;
import java.util.Map;

import com.yc.demo.report.dao.ReportDao;

public class ReportBiz {

	private ReportDao dao = new ReportDao();

	public List<Map<String, Object>> findByMonth(String year, String month) {
		return dao.selectByMonth(year, month);
	}

	public List<Map<String, Object>> sumSaleByYear(String year) {
		return dao.sumSaleByYear(year);
	}

	public List<Map<String, Object>> sumByOneMonth(String year, String month) {
		return dao.sumByOneMonth(year, month);
	}

	public List<Map<String, Object>> sumMonthByYear(String year) {
		return dao.sumMonthByYear(year);
	}

	public List<Map<String, Object>> sumSeasonByYear(String year) {
		return dao.sumSeasonByYear(year);
	}

	public List<Map<String, Object>> countBollByYear(String year) {
		return dao.countBollByYear(year);
	}

}

package com.yc.demo.report.dao;

import java.util.List;
import java.util.Map;

import com.ly.util.DBHelper;

public class ReportDao {
	public List<Map<String, Object>> selectByMonth(String year, String month) {
		String sql = "select * from lottery where substr(opendate,1,7)=?";
		String date = year + "-" + month;
		return DBHelper.findAll(sql, date);
	}
	
	public List<Map<String,Object>> sumSaleByYear(String year){
		String sql = "select sum(replace(sale,',','')) value, "
				+ " ''''||substr(opendate,6,2)||'月''' name " + 
				" from lottery" + 
				" where substr(opendate,1,4) = ?" + 
				" group by substr(opendate,1,7)";
		return DBHelper.findAll(sql, year);
	}
	
	/**
	 * 统计指定月份，在最近5年内的销售额变化
	 * @param year
	 * @param month
	 * @return
	 */
	public List<Map<String, Object>> sumByOneMonth(String year, String month) {
		String sql = "select ''''||substr(opendate,1,7)||'''' ym,"
				+ " sum(replace(sale,',','')) sale"
				+ "  from lottery"
				+ " where substr(opendate,1,7) between ? and ?"
				+ "   and substr(opendate,6,2) = ?"
				+ " group by substr(opendate,1,7)";
		String begin = Integer.parseInt(year) - 5 + "-" + month;
		String end = year + "-" + month;
		return DBHelper.findAll(sql, begin, end, month);
	}
	
	/**
	 * 统计指定年度，最高月销售额，最低月销售额，平均月销售额
	 * @param year
	 * @param month
	 * @return
	 */
	public List<Map<String, Object>> sumMonthByYear(String year) {
		/**用子查询先查制定年每月总金额，再求最大值、最小值、平均值，最后使用 union all 连接三个查询
		 * 三个子查询主要变化的（sql语句的第二行）是每个查询的汇总函数：max、min、avg
		 */
		String sql = 
				//最高月
				" SELECT '''最高月：' || SUBSTR (ym, 6, 2) || '月''' month, " + 
				"       MAX (sumsale) sale" +   // 注意这行代码的变化
				" FROM   (SELECT SUBSTR (opendate, 1, 7) ym, " + 
				"               SUM (REPLACE (sale, ',', '')) sumsale" + 
				"        FROM   lottery" + 
				"        WHERE  SUBSTR (opendate, 1, 4) = ?" + 
				"        GROUP  BY SUBSTR (opendate, 1, 7)) a" + 
				" GROUP  BY SUBSTR (ym, 1, 4)" + 
				//最低月
				" UNION ALL" + 
				" SELECT '''最低月：' || SUBSTR (ym, 6, 2) || '月''', " + 
				"       MIN (sumsale) maxsale" +   // 注意这行代码的变化
				" FROM   (SELECT SUBSTR (opendate, 1, 7) ym, " + 
				"               SUM (REPLACE (sale, ',', '')) sumsale" + 
				"        FROM   lottery" + 
				"        WHERE  SUBSTR (opendate, 1, 4) = ?" + 
				"        GROUP  BY SUBSTR (opendate, 1, 7)) a" + 
				" GROUP  BY SUBSTR (ym, 1, 4)" + 
				//平均值
				" UNION ALL" + 
				" SELECT '''平均销售额''', " + 
				"       AVG (sumsale) maxsale" +   // 注意这行代码的变化
				" FROM   (SELECT SUBSTR (opendate, 1, 7) ym, " + 
				"               SUM (REPLACE (sale, ',', '')) sumsale" + 
				"        FROM   lottery" + 
				"        WHERE  SUBSTR (opendate, 1, 4) = ?" + 
				"        GROUP  BY SUBSTR (opendate, 1, 7)) a" + 
				" GROUP  BY SUBSTR (ym, 1, 4)";
		return DBHelper.findAll(sql, year, year, year);
	}
	
	/**
	 * 统计指定年度，各个季度的销售总额
	 * @param year
	 * @param month
	 * @return
	 */
	public List<Map<String, Object>> sumSeasonByYear(String year) {
		/**
		 * 月份是 1~12，减去1，再整除3，等于（0~3），再加1，得到季度数（1~4）
		 * 即：(月份 - 1) / 3 + 1 = 季度
		 */
		String sql = "SELECT '''第' || ((CAST (SUBSTR (opendate, 6, 2) AS int) - 1) / 3 + 1) || '季度''' name, " + 
				"       SUM (REPLACE (sale, ',', '')) value" + 
				" FROM   lottery" + 
				" WHERE  SUBSTR (opendate, 1, 4) = ?" + 
				" GROUP  BY (CAST (SUBSTR (opendate, 6, 6) AS int) - 1) / 3";
		return DBHelper.findAll(sql, year);
	}
	
	/**
	 * 统计指定年度，各个中奖号码出现的次数
	 * @param year
	 * @param month
	 * @return
	 */
	public List<Map<String, Object>> countBollByYear(String year) {
		/**
		 * 使用联合查询将各个球的中奖号码汇总，再统计号码的出现次数
		 */
		String sql = "select boll name,count(*) value from (" + 
				" select redone boll from lottery where substr(opendate,1,4)=?" + 
				" union all" + 
				" select redtwo from lottery where substr(opendate,1,4)=?" + 
				" union all" + 
				" select redthree from lottery where substr(opendate,1,4)=?" + 
				" union all" + 
				" select redfour from lottery where substr(opendate,1,4)=?" + 
				" union all" + 
				" select redfive from lottery where substr(opendate,1,4)=?" + 
				" union all" + 
				" select redsix from lottery where substr(opendate,1,4)=?" + 
				" union all" + 
				" select blue from lottery where substr(opendate,1,4)=?)" + 
				" group by boll";
		return DBHelper.findAll(sql, year, year, year, year, year, year, year);
	}

}

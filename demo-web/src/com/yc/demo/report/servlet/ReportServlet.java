package com.yc.demo.report.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.demo.report.biz.ReportBiz;

@WebServlet("/report.servlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportBiz biz = new ReportBiz();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 获取参数
		 */
		String type = request.getParameter("type");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year == null) {
			year = "2017";
		}
		if (month == null) {
			month = "10";
		}
		switch (type) {
		case "bar":
			bar(request, response, year, month);
			break;
		case "line":
			line(request, response, year, month);
			break;
		case "pie":
			pie(request, response, year, month);
			break;
		case "1":
			sumByOneMonth(request, response, year, month);
			break;
		case "2":
			sumMonthByYear(request, response, year);
			break;
		case "3":
			sumSeasonByYear(request, response, year);
			break;
		case "4":
			countBollByYear(request, response, year);
			break;
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 曲线图
	 */
	protected void line(HttpServletRequest request,
			HttpServletResponse response, String year, String month)
			throws ServletException, IOException {
		/**
		 * 查询数据
		 */
		List<Map<String, Object>> list = biz.findByMonth(year, month);
		/**
		 * 构建显示数据
		 */
		// 名称列表
		List<String> nameList = new ArrayList<String>();
		// 数值列表
		List<Long> valueList = new ArrayList<Long>();

		for (Map<String, Object> m : list) {
			// 添加名称
			nameList.add((String) m.get("num"));
			// 添加销售金额，由于销售金额是货币格式字符串，如：2,345,436，因此要进行格式转换
			String svalue = (String) m.get("sale");
			// 去除逗号
			svalue = svalue.replaceAll(",", "");
			// 转换成数字
			Long lvalue = Long.parseLong(svalue);
			// 添加数值
			valueList.add(lvalue);
		}
		/**
		 * 设置属性，向页面推送数据
		 */
		request.setAttribute("nameList", nameList);
		request.setAttribute("valueList", valueList);
		request.getRequestDispatcher("/report/line-simple.jsp").forward(request,
				response);
	}

	protected void bar(HttpServletRequest request, HttpServletResponse response,
			String year, String month) throws ServletException, IOException {
		/**
		 * 查询数据
		 */
		List<Map<String, Object>> list = biz.findByMonth(year, month);
		/**
		 * 构建显示数据
		 */
		// 名称列表
		List<String> nameList = new ArrayList<String>();
		// 数值列表
		List<Long> valueList = new ArrayList<Long>();

		for (Map<String, Object> m : list) {
			// 添加名称
			nameList.add((String) m.get("num"));
			// 添加销售金额，由于销售金额是货币格式字符串，如：2,345,436，因此要进行格式转换
			String svalue = (String) m.get("firstprize");
			// 去除逗号
			svalue = svalue.replaceAll(",", "");
			// 转换成数字
			Long lvalue = Long.parseLong(svalue);
			// 添加数值
			valueList.add(lvalue);
		}
		/**
		 * 设置属性，向页面推送数据
		 */
		request.setAttribute("nameList", nameList);
		request.setAttribute("valueList", valueList);
		request.getRequestDispatcher("/report/bar-simple.jsp").forward(request,
				response);
	}

	protected void pie(HttpServletRequest request, HttpServletResponse response,
			String year, String month) throws ServletException, IOException {
		/**
		 * 查询数据
		 */
		List<Map<String, Object>> list = biz.sumSaleByYear(year);
		/**
		 * 构建显示数据
		 */
		// 名称列表
		List<String> nameList = new ArrayList<String>();
		// 数值列表
		List<Map<String, Object>> valueList = list;

		for (Map<String, Object> m : list) {
			// 添加名称
			nameList.add((String) m.get("name"));
		}
		/**
		 * 设置属性，向页面推送数据
		 */
		request.setAttribute("nameList", nameList);
		request.setAttribute("valueList", valueList);
		request.getRequestDispatcher("/report/pie-simple.jsp").forward(request,
				response);
	}
	
	private void countBollByYear(HttpServletRequest request,
			HttpServletResponse response, String year) throws ServletException, IOException {
		/**
		 * 查询数据
		 */
		List<Map<String, Object>> list = biz.countBollByYear(year);
		/**
		 * 构建显示数据
		 */
		// 名称列表
		List<String> nameList = new ArrayList<String>();
		// 数值列表
		List<Map<String, Object>> valueList = list;

		for (Map<String, Object> m : list) {
			// 添加名称
			nameList.add((String) m.get("name"));
		}
		/**
		 * 设置属性，向页面推送数据
		 */
		request.setAttribute("nameList", nameList);
		request.setAttribute("valueList", valueList);
		request.getRequestDispatcher("/report/pie-2.jsp").forward(request,
				response);
	}

	private void sumSeasonByYear(HttpServletRequest request,
			HttpServletResponse response, String year) throws ServletException, IOException {
		/**
		 * 查询数据
		 */
		List<Map<String, Object>> list = biz.sumSeasonByYear(year);
		/**
		 * 构建显示数据
		 */
		// 名称列表
		List<String> nameList = new ArrayList<String>();
		// 数值列表
		List<Map<String, Object>> valueList = list;

		for (Map<String, Object> m : list) {
			// 添加名称
			nameList.add((String) m.get("name"));
		}
		/**
		 * 设置属性，向页面推送数据
		 */
		request.setAttribute("nameList", nameList);
		request.setAttribute("valueList", valueList);
		request.getRequestDispatcher("/report/pie-1.jsp").forward(request,
				response);
		
	}

	private void sumMonthByYear(HttpServletRequest request,
			HttpServletResponse response, String year) throws ServletException, IOException {
		/**
		 * 查询数据
		 */
		List<Map<String, Object>> list = biz.sumMonthByYear(year);
		/**
		 * 构建显示数据
		 */
		// 名称列表
		List<String> nameList = new ArrayList<String>();
		// 数值列表
		List<Object> valueList = new ArrayList<Object>();

		for (Map<String, Object> m : list) {
			// 添加名称
			nameList.add((String) m.get("month"));
			// 添加数值，由于该字段已经在sql语句中求和过了，就是数值类型，因此不用转型了
			valueList.add(m.get("sale"));
		}
		/**
		 * 设置属性，向页面推送数据
		 */
		request.setAttribute("nameList", nameList);
		request.setAttribute("valueList", valueList);
		request.getRequestDispatcher("/report/bar-1.jsp").forward(request,
				response);
		
	}

	private void sumByOneMonth(HttpServletRequest request,
			HttpServletResponse response, String year, String month) throws ServletException, IOException {
		/**
		 * 查询数据
		 */
		List<Map<String, Object>> list = biz.sumByOneMonth(year, month);
		/**
		 * 构建显示数据
		 */
		// 名称列表
		List<String> nameList = new ArrayList<String>();
		// 数值列表
		List<Object> valueList = new ArrayList<Object>();

		for (Map<String, Object> m : list) {
			// 添加名称
			nameList.add((String) m.get("ym"));
			// 添加数值，由于该字段已经在sql语句中求和过了，就是数值类型，因此不用转型了
			valueList.add(m.get("sale"));
		}
		/**
		 * 设置属性，向页面推送数据
		 */
		request.setAttribute("nameList", nameList);
		request.setAttribute("valueList", valueList);
		request.getRequestDispatcher("/report/line-1.jsp").forward(request,
				response);
	}

}

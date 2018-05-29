package com.yc.demo.back.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.teach.servlet.AutoServlet;
import com.ly.util.BeanUtils;
import com.ly.util.DBHelper;
import com.yc.teach.vo.EasyUIPage;

@WebServlet("/user.s")
public class UserServlet extends AutoServlet {
	private static final long serialVersionUID = 1L;

	protected void query(HttpServletRequest req, HttpServletResponse resp, EasyUIPage p)
			throws IOException {
		Integer page = p.getPage();
		Integer rows = p.getRows();
		page = page == null ? 1 : page;
		rows = rows == null ? 5 : rows;
		int start = ( page - 1) * rows;
		Object lists = DBHelper.find("select * from user limit ?,?", start, rows);
		Object total = DBHelper.find("select count(*) cnt from user").get(0)
				.get("cnt");
		Map<?, ?> m = BeanUtils.asMap("rows", lists, "total", total);
		Gson g = new Gson();
		resp.getWriter().print(g.toJson(m));
	}

}

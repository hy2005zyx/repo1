package com.yc.demo.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String vcode = (String) request.getSession().getAttribute("vcode");
		if (vcode == null || vcode
				.equalsIgnoreCase(request.getParameter("vcode")) == false) {
			response.getWriter().write("验证码错误！");
		} else if ("admin".equals(request.getParameter("uname"))
				&& "123".equals(request.getParameter("pwd"))) {
			//移除验证码
			request.getSession().removeAttribute("vcode");
			response.getWriter().write("ok");
		} else {
			response.getWriter().write("用户名或密码错误！");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

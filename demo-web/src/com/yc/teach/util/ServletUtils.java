package com.yc.teach.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtils {

	/**
	 * 根据 request 中 action 参数值，调用servlet中的方法，
	 * 要求 servlet 的业务方法前两位必须是 request 和 response，
	 * 后面的参数自动装配，注意后面的参数只能使用实体类
	 * @param servlet
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void action(HttpServlet servlet, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			throw new ServletException("没有指定的方法：" + action);
		}
		Method[] ms = BeanUtils.getMethodsByName(servlet.getClass(), action);
		Method actionMethod = ms[0];
		Parameter[] ps = actionMethod.getParameters();
		Object[] args = null;
		if (ps.length < 2) {
			throw new ServletException("参数数量必须大于等于2，且前2个必须请求和响应：" + action);
		} else if (ps.length == 2) {
			args = new Object[] { req, resp };
		} else {
			args = new Object[ps.length];
			args[0] = req;
			args[1] = resp;
			for (int i = 2; i < ps.length; i++) {
				args[i] = BeanUtils.asBean(req, ps[i].getType());
			}
		}
		try {
			actionMethod.invoke(servlet, args);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			throw new ServletException(e);
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof ServletException) {
				throw (ServletException) e.getCause();
			} else if (e.getCause() instanceof IOException) {
				throw (IOException) e.getCause();
			} else {
				throw new ServletException(e.getCause());
			}
		}
	}

}

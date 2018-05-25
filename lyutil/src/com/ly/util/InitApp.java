package com.ly.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitApp implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		setBasePath(application);
		openMe(application);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * 设置应用上下文路径
	 * @param application
	 */
	public static void setBasePath(ServletContext application) {
		String basePath = application.getContextPath();
		application.setAttribute("basePath", basePath);
	}
	
	/**
	 * 设置 Tomcat 服务器默认访问当前应用
	 * @param application
	 */
	public static void openMe(ServletContext application) {
		if("false".equals(application.getInitParameter("openMe"))) {
			return;
		}
		String tomcatPath = System.getenv("CATALINA_HOME");
		System.out.println("Tomcat 路径：" + tomcatPath);
		String indexPath = null;
		if (tomcatPath != null) {
			indexPath = tomcatPath + "/webapps/ROOT";
		} else {
			File f = new File(application.getRealPath("/"));
			indexPath = f.getParent() + "/ROOT";
		}
		
		if(new File(indexPath).exists() == false) {
			System.out.println("未定位到 Tomcat 的 ROOT 目录！OpenMe 设置终止！");
			return;
		} else {
			indexPath += "/index.jsp";
		}

		
		System.out.println("Tomcat 默认访问项目：" + application.getContextPath());
		System.out.println("Tomcat 生成JSP文件：" + indexPath);
		File indexFile = new File(indexPath);
		BufferedWriter out = null;
		try {
			if (indexFile.exists() == false) {
				indexFile.createNewFile();
			}
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(indexFile), "utf-8"));
			out.write(
					"<%@ page session=\"false\" pageEncoding=\"UTF-8\" contentType=\"text/html; charset=UTF-8\" %>\r\n");
			out.write("<%response.sendRedirect(\""
					+ application.getContextPath() + "\");%>");
		} catch (Exception e) {
			System.out.println(
					"文件创建失败，请确认该服务器是否是 Tomcat 服务器：" + indexFile.getPath());
			System.out.println("错误原因：" + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

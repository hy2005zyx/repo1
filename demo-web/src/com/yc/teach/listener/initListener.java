package com.yc.teach.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 项目初始化
 *
 */
@WebListener
public class initListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent se)  { 
    	
    }

    public void contextInitialized(ServletContextEvent se)  { 
    	String basePath = se.getServletContext().getContextPath();
    	se.getServletContext().setAttribute("basePath", basePath);
    }
	
}

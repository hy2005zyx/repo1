package com.yc.teach.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 项目初始化
 *
 */
@WebListener
/**
 * 实现 ServletContextListener（应用上下文生命周期监听器）接口，
 * 项目启动时会自动执行 contextInitialized 方法
 */
public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent se)  { 
    	
    }

    public void contextInitialized(ServletContextEvent se)  { 
    	System.out.println("工程初始化");
    	String basePath = se.getServletContext().getContextPath();
    	//添加 basePath 保存工程名，方便el表达式使用
    	se.getServletContext().setAttribute("basePath", basePath);
    }
	
}

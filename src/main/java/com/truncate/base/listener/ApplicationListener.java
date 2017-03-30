package com.truncate.base.listener;

import com.truncate.base.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 描述:	servlet上下文监听器
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 19:33
 */
public class ApplicationListener implements ServletContextListener
{

	private static final Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		if(logger.isInfoEnabled())
		{
			logger.info("Application is start...");
		}
		ServletContext context = servletContextEvent.getServletContext();
		Application.setServletContext(context);
		Application.setRootPath(context.getRealPath("/"));
		init();
	}

	public void init()
	{

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{
		if(logger.isInfoEnabled())
		{
			logger.info("Application is stop...");
		}
	}
}

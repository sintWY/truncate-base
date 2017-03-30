package com.truncate.base;

import javax.servlet.ServletContext;

/**
 * 描述: 应用相关信息
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 19:31
 */
public class Application
{

	private static String rootPath;

	private static ServletContext servletContext;

	public static String getRootPath()
	{
		return rootPath;
	}

	public static void setRootPath(String rootPath)
	{
		Application.rootPath = rootPath;
	}

	public static ServletContext getServletContext()
	{
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext)
	{
		Application.servletContext = servletContext;
	}
}

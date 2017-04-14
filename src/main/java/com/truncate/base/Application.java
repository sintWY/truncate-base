package com.truncate.base;

import com.truncate.base.jdbc.DBSourceManager;

import javax.servlet.ServletContext;

/**
 * 描述: 应用相关信息
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 19:31
 */
public abstract class Application
{

	//应用根路径
	private static String rootPath;

	//servlet上下文
	private static ServletContext servletContext;

	public static String getRootPath()
	{
		return rootPath;
	}

	public static ServletContext getServletContext()
	{
		return servletContext;
	}

	/**
	 *@描述：引用初始化动作
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/31
	 *@时间:22:03
	 */
	public static void init(ServletContext servletContext)
	{
		Application.servletContext = servletContext;
		Application.rootPath = servletContext.getRealPath("/");

		DBSourceManager.init();
	}
}

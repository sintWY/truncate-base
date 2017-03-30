package com.truncate.base.config;

import com.truncate.base.interceptor.Interceptor;

import java.util.LinkedHashSet;

/**
 * 描述: 拦截器栈节点
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 21:58
 */
public class InterceptorStack
{

	//栈名称
	private String stackName;

	//引用的拦截器
	private LinkedHashSet<Interceptor> interceptors = new LinkedHashSet<Interceptor>();

	public String getStackName()
	{
		return stackName;
	}

	public void setStackName(String stackName)
	{
		this.stackName = stackName;
	}

	public LinkedHashSet<Interceptor> getInterceptors()
	{
		return interceptors;
	}

	public void setInterceptors(LinkedHashSet<Interceptor> interceptors)
	{
		this.interceptors = interceptors;
	}

	public void addtInterceptor(Interceptor interceptor)
	{
		interceptors.add(interceptor);
	}
}

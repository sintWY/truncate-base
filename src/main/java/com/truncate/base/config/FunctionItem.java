package com.truncate.base.config;

import com.truncate.base.interceptor.Interceptor;

import java.util.LinkedHashSet;

/**
 * 描述: 功能节点
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 12:39
 */
public class FunctionItem
{

	//功能编号
	private int id;

	//类型
	private int type;

	//实现类名称
	private String className;

	//第三方功能号
	private int thirdNo;

	//描述
	private String description;

	//拦截器对象集合
	private LinkedHashSet<Interceptor> interceptors = new LinkedHashSet<Interceptor>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public int getThirdNo()
	{
		return thirdNo;
	}

	public void setThirdNo(int thirdNo)
	{
		this.thirdNo = thirdNo;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public LinkedHashSet<Interceptor> getInterceptors()
	{
		return interceptors;
	}

	public void addInterceptors(LinkedHashSet<Interceptor> interceptors)
	{
		if(interceptors != null && !interceptors.isEmpty())
		{
			this.interceptors.addAll(interceptors);
		}
	}
}

package com.truncate.base.config;

import com.truncate.base.interceptor.Interceptor;

import java.util.LinkedHashSet;

/**
 * ����: ���ܽڵ�
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 12:39
 */
public class FunctionItem
{

	//���ܱ��
	private int id;

	//����
	private int type;

	//ʵ��������
	private String className;

	//���������ܺ�
	private int thirdNo;

	//����
	private String description;

	//���������󼯺�
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

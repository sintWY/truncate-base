package com.truncate.base.config;

import com.truncate.base.constant.Constant;
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
	private String functionType;

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

	public String getFunctionType()
	{
		return functionType;
	}

	public void setFunctionType(String functionType)
	{
		this.functionType = functionType;
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

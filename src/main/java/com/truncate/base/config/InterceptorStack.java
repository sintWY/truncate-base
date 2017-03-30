package com.truncate.base.config;

import com.truncate.base.interceptor.Interceptor;

import java.util.LinkedHashSet;

/**
 * ����: ������ջ�ڵ�
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 21:58
 */
public class InterceptorStack
{

	//ջ����
	private String stackName;

	//���õ�������
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

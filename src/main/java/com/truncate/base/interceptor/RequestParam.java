package com.truncate.base.interceptor;

import com.truncate.base.util.ConvertUtil;
import com.truncate.base.util.IPUtil;
import com.truncate.base.util.RandomUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 请求参数
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年04月01日
 * 创建时间: 10:35
 */
public class RequestParam
{

	//请求编号
	private String serialNo;

	//请求IP
	private String requestIp;

	//请求参数
	private Map<String, String> paraMap;

	//请求功能号
	private int funcNo;

	public RequestParam(HttpServletRequest request)
	{
		this.funcNo = ConvertUtil.str2Int(request.getParameter("funcNo"));
		this.serialNo = RandomUtil.getUUID();
		this.requestIp = IPUtil.getRequestIp(request);
		this.paraMap = new HashMap<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements())
		{
			String key = enumeration.nextElement();
			String value = request.getParameter(key);
			paraMap.put(key, value);
		}
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getRequestIp()
	{
		return requestIp;
	}

	public void setRequestIp(String requestIp)
	{
		this.requestIp = requestIp;
	}

	public Map<String, String> getParaMap()
	{
		return paraMap;
	}

	public void setParaMap(Map<String, String> paraMap)
	{
		this.paraMap = paraMap;
	}

	public int getFuncNo()
	{
		return funcNo;
	}

	public void setFuncNo(int funcNo)
	{
		this.funcNo = funcNo;
	}

	public String getValue(String name)
	{
		return this.paraMap.get(name);
	}
}

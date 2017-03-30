package com.truncate.base.domain;

import com.truncate.base.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����: �����
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 14:35
 */
public class ResultVo
{

	private static final String DEFAULT_RESULT_KEY = "defaultResult";

	//�����
	private Map resultMap;

	public ResultVo()
	{
		resultMap = new HashMap();
		resultMap.put("error_no", "0");
		resultMap.put("error_message", "����ɹ�");
	}

	public int getErrorNo()
	{
		return (Integer) resultMap.get("error_no");
	}

	public void setErrorNo(int errorNo)
	{
		resultMap.put("error_no", errorNo);
	}

	public String getErrorMessage()
	{
		return (String) resultMap.get("error_message");
	}

	public void setErrorMessage(String errorMessage)
	{
		resultMap.put("error_message", errorMessage);
	}

	public void setResult(Map resultMap)
	{
		setResult(DEFAULT_RESULT_KEY, resultMap);
	}

	public void setResult(String key, Map resultMap)
	{
		List resultList = new ArrayList();
		resultList.add(resultMap);
		setResult(key, resultList);
	}

	public void setResult(String key, List resultList)
	{
		resultMap.put(key, resultList);
	}

	public void setResult(List resultList)
	{
		setResult(DEFAULT_RESULT_KEY, resultList);
	}

	public Map getResultMap()
	{
		return resultMap;
	}

	public static void main(String[] args)
	{
		ResultVo resultVo = new ResultVo();
		Map map = new HashMap();
		map.put("one", "t1");
		map.put("two", "t2");
		resultVo.setResult(map);
		System.out.println(JsonUtil.toString(resultVo.getResultMap()));
	}
}

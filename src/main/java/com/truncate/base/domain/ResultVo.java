package com.truncate.base.domain;

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
	private Map<String, Object> resultMap;

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

	public void setResult(DataRow dataRow)
	{
		setResult(DEFAULT_RESULT_KEY, dataRow);
	}

	public void setResult(String key, DataRow dataRow)
	{
		List<DataRow> resultList = new ArrayList<DataRow>();
		resultList.add(dataRow);
		setResult(key, resultList);
	}

	public void setResult(String key, List<DataRow> resultList)
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

	public DataRow getData(String name)
	{
		List<DataRow> tempList = (List<DataRow>) resultMap.get(name);
		if(tempList != null && !tempList.isEmpty())
		{
			return tempList.get(0);
		}
		return new DataRow();
	}

	public DataRow getData()
	{
		return getData(DEFAULT_RESULT_KEY);
	}

	public List<DataRow> getList()
	{
		return getList(DEFAULT_RESULT_KEY);
	}

	public List<DataRow> getList(String name)
	{
		List<DataRow> tempList = (List<DataRow>) resultMap.get(name);
		if(tempList == null)
		{
			return new ArrayList<DataRow>();
		}
		return tempList;
	}
}

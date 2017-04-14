package com.truncate.base.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: 结果集
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 14:35
 */
public class ResultVo
{

	private static final Logger logger = LoggerFactory.getLogger(ResultVo.class);

	private static final String DEFAULT_RESULT_KEY = "default_result";

	//结果集
	private Map<String, Object> resultMap;

	public ResultVo()
	{
		resultMap = new HashMap();
		resultMap.put("error_no", 0);
		resultMap.put("error_message", "请求成功");
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
		if(dataRow != null && !dataRow.isEmpty())
		{
			resultList.add(dataRow);
		}
		else
		{
			if(logger.isDebugEnabled())
			{
				logger.debug("打包的结果集为空，请检查!");
			}
		}
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

	public void setResult(DataPage dataPage)
	{
		setResult(DEFAULT_RESULT_KEY, dataPage);
	}

	public void setResult(String name, DataPage dataPage)
	{
		List<DataRow> tempList = new ArrayList<DataRow>();
		DataRow dataRow = new DataRow();
		dataRow.set("current_page", dataPage.getCurrentPage());
		dataRow.set("total_page", dataPage.getTotalPages());
		dataRow.set("num_per_page", dataPage.getNumPerPage());
		dataRow.set("total_row", dataPage.getTotalRows());
		tempList.add(dataRow);
		List<DataRow> dataList = dataPage.getDataList();
		if(dataList != null && !dataList.isEmpty())
		{
			tempList.addAll(dataList);
		}
		setResult(name, tempList);
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

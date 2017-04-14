package com.truncate.base.domain;

import java.util.List;

/**
 * 描述: 分页数据对象
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月31日
 * 创建时间: 19:31
 */
public class DataPage
{

	//当前页
	private int currentPage;

	//每页数量
	private int numPerPage;

	//总行数
	private int totalRows;

	//总页数
	private int totalPages;

	//起始数据行
	private int startRow;

	private List<DataRow> dataList;

	public DataPage(int currentPage, int numPerPage, int totalRows)
	{
		this.currentPage = currentPage;
		this.totalRows = totalRows;
		this.numPerPage = numPerPage;
		this.totalPages = totalRows % numPerPage == 0 ? totalRows / numPerPage : totalRows / numPerPage + 1;
		this.startRow = (currentPage - 1) * numPerPage;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getNumPerPage()
	{
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage)
	{
		this.numPerPage = numPerPage;
	}

	public int getTotalRows()
	{
		return totalRows;
	}

	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}

	public int getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	public List<DataRow> getDataList()
	{
		return dataList;
	}

	public void setDataList(List<DataRow> dataList)
	{
		this.dataList = dataList;
	}

	public int getStartRow()
	{
		return startRow;
	}

	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}

}

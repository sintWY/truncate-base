package com.truncate.base.domain;

import java.util.List;

/**
 * ����: ��ҳ���ݶ���
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��31��
 * ����ʱ��: 19:31
 */
public class DataPage
{

	//��ǰҳ
	private int currentPage;

	//ÿҳ����
	private int numPerPage;

	//������
	private int totalRows;

	//��ҳ��
	private int totalPages;

	//��ʼ������
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

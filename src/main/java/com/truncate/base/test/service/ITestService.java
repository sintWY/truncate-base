package com.truncate.base.test.service;

import com.truncate.base.domain.DataPage;
import com.truncate.base.domain.DataRow;

import java.util.List;

/**
 * ����: ���Է���ӿ�
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��31��
 * ����ʱ��: 20:59
 */
public interface ITestService
{

	DataRow queryMap(String name);

	List<DataRow> queryList();

	DataPage queryDataPage(int currentPage, int numPerPage);
}

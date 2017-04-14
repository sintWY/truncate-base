package com.truncate.base.test.service.impl;

import com.truncate.base.domain.DataPage;
import com.truncate.base.domain.DataRow;
import com.truncate.base.jdbc.dbsession.DBSession;
import com.truncate.base.test.service.ITestService;
import com.truncate.base.util.SpringUtil;

import java.util.List;

/**
 * ����: ���Է���ʵ����
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��31��
 * ����ʱ��: 20:59
 */
public class TestServiceImpl implements ITestService
{

	@Override
	public DataRow queryMap(String name)
	{
		DBSession dbSession = SpringUtil.getBean("sessionTemplate");
		String sql = "SELECT * FROM T_STUDENT WHERE NAME = ?";
		DataRow dataRow = dbSession.queryMap(sql, new Object[] { name });
		dbSession.close();
		return dataRow;
	}

	@Override
	public List<DataRow> queryList()
	{
		DBSession dbSession = SpringUtil.getBean("sessionTemplate");
		String sql = "SELECT * FROM T_STUDENT ORDER BY NAME ASC";
		List<DataRow> dataRowList = dbSession.queryList(sql);
		dbSession.close();
		return dataRowList;
	}

	@Override
	public DataPage queryDataPage(int currentPage, int numPerPage)
	{
		DBSession dbSession = SpringUtil.getBean("sessionTemplate");
		String sql = "SELECT * FROM T_STUDENT ORDER BY NAME ASC";
		DataPage dataPage = dbSession.queryPage(sql, currentPage, numPerPage);
		dbSession.close();
		return dataPage;
	}
}

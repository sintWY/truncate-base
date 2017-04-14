package com.truncate.base.test.service.impl;

import com.truncate.base.domain.DataPage;
import com.truncate.base.domain.DataRow;
import com.truncate.base.jdbc.dbsession.DBSession;
import com.truncate.base.test.service.ITestService;
import com.truncate.base.util.SpringUtil;

import java.util.List;

/**
 * 描述: 测试服务实现类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月31日
 * 创建时间: 20:59
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

package com.truncate.base.test.service;

import com.truncate.base.domain.DataPage;
import com.truncate.base.domain.DataRow;

import java.util.List;

/**
 * 描述: 测试服务接口
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月31日
 * 创建时间: 20:59
 */
public interface ITestService
{

	DataRow queryMap(String name);

	List<DataRow> queryList();

	DataPage queryDataPage(int currentPage, int numPerPage);
}

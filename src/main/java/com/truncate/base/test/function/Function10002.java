package com.truncate.base.test.function;

import com.truncate.base.domain.DataPage;
import com.truncate.base.domain.ResultVo;
import com.truncate.base.function.BaseFunction;
import com.truncate.base.test.service.ITestService;
import com.truncate.base.util.SpringUtil;

/**
 * 描述: 测试查询DataPage
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月31日
 * 创建时间: 21:15
 */
public class Function10002 extends BaseFunction
{

	private ITestService testService = SpringUtil.getBean("baseTest");

	@Override
	public ResultVo execute()
	{
		int currentPage = getIntParameter("current_page", 1);
		int numPerPage = getIntParameter("num_per_page", 10);
		DataPage dataPage = testService.queryDataPage(currentPage, numPerPage);
		resultVo.setResult(dataPage);
		return resultVo;
	}
}

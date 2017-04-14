package com.truncate.base.test.function;

import com.truncate.base.domain.DataRow;
import com.truncate.base.domain.ResultVo;
import com.truncate.base.function.BaseFunction;
import com.truncate.base.test.service.ITestService;
import com.truncate.base.util.SpringUtil;

import java.util.List;

/**
 * 描述: 测试查询list
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月31日
 * 创建时间: 21:15
 */
public class Function10001 extends BaseFunction
{

	private ITestService testService = SpringUtil.getBean("baseTest");

	@Override
	public ResultVo execute()
	{
		List<DataRow> dataList = testService.queryList();
		resultVo.setResult(dataList);
		return resultVo;
	}
}

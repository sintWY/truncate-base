package com.truncate.base.test.function;

import com.truncate.base.domain.DataRow;
import com.truncate.base.domain.ResultVo;
import com.truncate.base.function.BaseFunction;
import com.truncate.base.test.service.ITestService;
import com.truncate.base.util.SpringUtil;

/**
 * ����: ���Բ�ѯmap
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��31��
 * ����ʱ��: 21:15
 */
public class Function10000 extends BaseFunction
{

	private ITestService testService = SpringUtil.getBean("baseTest");

	@Override
	public ResultVo execute()
	{
		String name = getAndCheckParameter("name");
		DataRow dataRow = testService.queryMap(name);
		resultVo.setResult(dataRow);
		return resultVo;
	}
}

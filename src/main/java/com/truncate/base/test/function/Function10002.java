package com.truncate.base.test.function;

import com.truncate.base.domain.DataPage;
import com.truncate.base.domain.ResultVo;
import com.truncate.base.function.BaseFunction;
import com.truncate.base.test.service.ITestService;
import com.truncate.base.util.SpringUtil;

/**
 * ����: ���Բ�ѯDataPage
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��31��
 * ����ʱ��: 21:15
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

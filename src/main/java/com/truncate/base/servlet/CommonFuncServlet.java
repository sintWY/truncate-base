package com.truncate.base.servlet;

import com.truncate.base.domain.ResultVo;
import com.truncate.base.exception.CommonException;
import com.truncate.base.exception.ErrorCode;
import com.truncate.base.exception.ErrorCodeManager;
import com.truncate.base.interceptor.ActionInvocation;
import com.truncate.base.interceptor.BaseActionInvocation;
import com.truncate.base.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ����: ����servlet
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��30��
 * ����ʱ��: 0:33
 */
public class CommonFuncServlet extends HttpServlet
{

	private static final Logger logger = LoggerFactory.getLogger(CommonFuncServlet.class);

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ResultVo resultVo = new ResultVo();
		try
		{
			ActionInvocation actionInvocation = new BaseActionInvocation(request, response);
			resultVo = actionInvocation.invoke();
		}
		catch(CommonException commonException)
		{
			resultVo.setErrorNo(commonException.getErrorCode());
			resultVo.setErrorMessage(commonException.getMessage());
			logger.error("����ʧ��,������룺{},������Ϣ��{}", new Object[] { commonException.getErrorCode(), commonException.getMessage() });
		}
		catch(Exception exception)
		{
			resultVo.setErrorNo(ErrorCode.SYS_ERROR);
			resultVo.setErrorMessage(ErrorCodeManager.getErrorMessage(ErrorCode.SYS_ERROR));
			logger.error("ϵͳ�쳣!", exception);
		}
		catch(Throwable throwable)
		{
			resultVo.setErrorNo(ErrorCode.SYS_ERROR);
			resultVo.setErrorMessage(ErrorCodeManager.getErrorMessage(ErrorCode.SYS_ERROR));
			logger.error("ϵͳ�쳣!", throwable);
		}

		write(resultVo, response);
	}

	private void write(ResultVo resultVo, HttpServletResponse response)
	{
		PrintWriter writer = null;
		try
		{
			response.setContentType("text/html");
			writer = response.getWriter();
			writer.write(JsonUtil.toString(resultVo.getResultMap()));
			writer.flush();
		}
		catch(Exception e)
		{
			logger.error("д���ݵ��ͻ���ʧ��!", e);
		}
		finally
		{
			if(writer != null)
			{
				writer.close();
			}
		}
	}
}

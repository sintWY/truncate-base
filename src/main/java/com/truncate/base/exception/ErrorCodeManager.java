package com.truncate.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: ErrorCode管理类
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月28日
 * 创建时间: 22:06
 */
public class ErrorCodeManager
{

	private static final Logger logger = LoggerFactory.getLogger(ErrorCodeManager.class);

	private static final Map<String, String> ERROR_CODE_MAP = new HashMap<String, String>();

	static
	{
		initErrorCode();
	}

	public static void initErrorCode()
	{
		try
		{
			Field[] fields = ErrorCode.class.getFields();
			if(fields != null && fields.length > 0)
			{
				for(Field field : fields)
				{
					int errorCode = field.getInt(null);
					String errorMessage = "错误码对应的错误信息未配置!";
					ErrorMessage errorMessageAnnotation = field.getAnnotation(ErrorMessage.class);
					if(errorMessageAnnotation != null)
					{
						errorMessage = errorMessageAnnotation.value();
					}
					ERROR_CODE_MAP.put(errorCode + "", errorMessage);
				}
			}
		}

		catch(IllegalAccessException e)
		{
			logger.error("初始化错误代码到内存失败!", e);
		}
	}

	/**
	 *@描述：根据错误代码获取错误信息
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/28
	 *@时间:22:45
	 *
	 */
	public static String getErrorMessage(int errorCode)
	{
		return ERROR_CODE_MAP.get(errorCode + "");
	}

	/**
	 *@描述：根据错误代码获取错误信息，替换参数
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/29
	 *@时间:11:36
	 *
	 */
	public static String getErrorMessage(int errorCode, Object[] targets)
	{
		String errorMessage = ERROR_CODE_MAP.get(errorCode + "");
		return String.format(errorMessage, targets);
	}
}

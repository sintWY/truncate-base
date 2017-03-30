package com.truncate.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * ����: ErrorCode������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��28��
 * ����ʱ��: 22:06
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
					String errorMessage = "�������Ӧ�Ĵ�����Ϣδ����!";
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
			logger.error("��ʼ��������뵽�ڴ�ʧ��!", e);
		}
	}

	/**
	 *@���������ݴ�������ȡ������Ϣ
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/28
	 *@ʱ��:22:45
	 *
	 */
	public static String getErrorMessage(int errorCode)
	{
		return ERROR_CODE_MAP.get(errorCode + "");
	}

	/**
	 *@���������ݴ�������ȡ������Ϣ���滻����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/29
	 *@ʱ��:11:36
	 *
	 */
	public static String getErrorMessage(int errorCode, Object[] targets)
	{
		String errorMessage = ERROR_CODE_MAP.get(errorCode + "");
		return String.format(errorMessage, targets);
	}
}

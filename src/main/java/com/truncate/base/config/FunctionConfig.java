package com.truncate.base.config;

import com.truncate.base.constant.Constant;
import com.truncate.base.exception.CommonException;
import com.truncate.base.util.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����: ���ܺ������ļ�
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��29��
 * ����ʱ��: 12:38
 */
public class FunctionConfig
{

	private static final Logger logger = LoggerFactory.getLogger(FunctionConfig.class);

	//���ܺ������ļ�
	private static final String FUNCTION_CONFIG_FILE = "function.xml";

	//���ܺų�
	private static final Map<Integer, FunctionItem> FUNCTION_MAP = new HashMap<Integer, FunctionItem>();

	//���ܺ���
	private static final Map<String, List<FunctionItem>> FUNCTION_GROUP_MAP = new HashMap<String, List<FunctionItem>>();

	static
	{
		loadFunctionConfig();
		InterceptorConfig.init();
	}

	public static void loadFunctionConfig()
	{
		Document document = XmlUtil.getDocumentFromClassPath(FUNCTION_CONFIG_FILE);
		Element rootElement = document.getRootElement();

		//��������
		List<Element> resources = rootElement.elements("resource");
		for(Element resourceElement : resources)
		{
			String refUrl = resourceElement.attributeValue("ref");
			String groupName = resourceElement.attributeValue("name");
			if(logger.isDebugEnabled())
			{
				logger.debug("���ع��ܺ������ļ���[{}]", new Object[] { refUrl });
			}
			try
			{
				Document refDocument = XmlUtil.getDocumentFromClassPath(refUrl);
				rootElement = refDocument.getRootElement();
				loadFunctionElement(rootElement, groupName);
			}
			catch(CommonException e)
			{
				logger.warn("��ȡ�ļ�����!", e);
			}
		}
	}

	private static void loadFunctionElement(Element rootElement, String groupName)
	{
		//���ع��ܺ�
		List<Element> functionElements = rootElement.elements("function");
		List<FunctionItem> functionItemList = new ArrayList<FunctionItem>();
		for(Element functionElement : functionElements)
		{
			int id = Integer.valueOf(functionElement.attributeValue("id"));
			String type = functionElement.attributeValue("type");
			String thirdNoStr = functionElement.attributeValue("thirdNo");
			int thirdNo = StringUtils.isEmpty(thirdNoStr) ? id : Integer.valueOf(thirdNoStr);
			String className = functionElement.attributeValue("class");
			String description = functionElement.attributeValue("description");

			FunctionItem item = new FunctionItem();
			item.setId(id);
			if(Constant.FunctionType.LOCAL.equals(type))
			{
				item.setFunctionType(Constant.FunctionType.LOCAL);
			}
			else if(Constant.FunctionType.THIRD.equals(type))
			{
				item.setFunctionType(Constant.FunctionType.THIRD);
			}
			item.setClassName(className);
			item.setThirdNo(thirdNo);
			item.setDescription(description);

			if(logger.isDebugEnabled())
			{
				logger.debug("���ܺ�[{}]�������!", new Object[] { id });
			}

			FUNCTION_MAP.put(id, item);
			functionItemList.add(item);
		}
		FUNCTION_GROUP_MAP.put(groupName, functionItemList);
	}

	/**
	 *@�������жϹ��ܺ��Ƿ����
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/29
	 *@ʱ��:14:09
	 *
	 */
	public static boolean conyainsFuncNo(int funcNo)
	{
		return FUNCTION_MAP.containsKey(funcNo);
	}

	/**
	 *@��������ȡָ���Ĺ��ܺŶ���
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/29
	 *@ʱ��:14:10
	 *
	 */
	public static FunctionItem getFunctionItem(int funcNo)
	{
		return FUNCTION_MAP.get(funcNo);
	}

	/**
	 *@��������ȡָ����Ĺ��ܺ��б�
	 *@����:truncate(wy940407@163.com)
	 *@����:2017/3/29
	 *@ʱ��:22:32
	 *
	 */
	public static List<FunctionItem> getGroupFunctionList(String groupName)
	{
		return FUNCTION_GROUP_MAP.get(groupName);
	}
}

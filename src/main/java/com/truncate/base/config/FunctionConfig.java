package com.truncate.base.config;

import com.truncate.base.util.XmlUtil;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: 功能号配置文件
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月29日
 * 创建时间: 12:38
 */
public class FunctionConfig
{

	private static final Logger logger = LoggerFactory.getLogger(FunctionConfig.class);

	//功能号配置文件
	private static final String FUNCTION_CONFIG_FILE = "function.xml";

	//功能号池
	private static final Map<Integer, FunctionItem> FUNCTION_MAP = new HashMap<Integer, FunctionItem>();

	//功能号组
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

		//加载引用
		List<Element> resources = rootElement.elements("resource");
		for(Element resourceElement : resources)
		{
			String refUrl = resourceElement.attributeValue("ref");
			String groupName = resourceElement.attributeValue("name");
			if(logger.isDebugEnabled())
			{
				logger.debug("加载功能号配置文件：{}", new String[] { refUrl });
			}
			Document refDocument = XmlUtil.getDocumentFromClassPath(refUrl);
			rootElement = refDocument.getRootElement();
			loadFunctionElement(rootElement, groupName);
		}
	}

	private static void loadFunctionElement(Element rootElement, String groupName)
	{
		//加载功能号
		List<Element> functionElements = rootElement.elements("function");
		List<FunctionItem> functionItemList = new ArrayList<FunctionItem>();
		for(Element functionElement : functionElements)
		{
			int id = Integer.valueOf(functionElement.attributeValue("id"));
			int type = Integer.valueOf(functionElement.attributeValue("type"));
			String thirdNoStr = functionElement.attributeValue("thirdNo");
			int thirdNo = StringUtils.isEmpty(thirdNoStr) ? id : Integer.valueOf(thirdNoStr);
			String className = functionElement.attributeValue("class");
			String description = functionElement.attributeValue("description");

			FunctionItem item = new FunctionItem();
			item.setId(id);
			item.setType(type);
			item.setClassName(className);
			item.setThirdNo(thirdNo);
			item.setDescription(description);

			if(logger.isDebugEnabled())
			{
				logger.debug("功能号{}加载完成!", new Object[] { id });
			}

			FUNCTION_MAP.put(id, item);
			functionItemList.add(item);
		}
		FUNCTION_GROUP_MAP.put(groupName, functionItemList);
	}

	/**
	 *@描述：判断功能号是否存在
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/29
	 *@时间:14:09
	 *
	 */
	public static boolean conyainsFuncNo(int funcNo)
	{
		return FUNCTION_MAP.containsKey(funcNo);
	}

	/**
	 *@描述：获取指定的功能号对象
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/29
	 *@时间:14:10
	 *
	 */
	public static FunctionItem getFunctionItem(int funcNo)
	{
		return FUNCTION_MAP.get(funcNo);
	}

	/**
	 *@描述：获取指定组的功能号列表
	 *@作者:truncate(wy940407@163.com)
	 *@日期:2017/3/29
	 *@时间:22:32
	 *
	 */
	public static List<FunctionItem> getGroupFunctionList(String groupName)
	{
		return FUNCTION_GROUP_MAP.get(groupName);
	}
}

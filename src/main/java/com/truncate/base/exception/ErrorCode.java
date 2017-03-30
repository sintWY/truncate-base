package com.truncate.base.exception;

/**
 * 描述: 错误码
 * 版权: Copyright (c) 2017
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月28日
 * 创建时间: 22:07
 */
public class ErrorCode
{

	/**系统级别异常**/
	@ErrorMessage("系统异常")
	public static final int SYS_ERROR = -100000;

	/**数据库级别异常**/
	@ErrorMessage("数据库异常")
	public static final int DB_ERROR = -200000;

	/**网络级别异常**/
	@ErrorMessage("网络异常")
	public static final int NETWORK_ERROR = -300000;

	/**第三方系统异常**/
	@ErrorMessage("三方系统[%s]异常")
	public static final int THIRD_SYS_ERROR = -400000;

	/****/

	/**项目级别异常**/
	@ErrorMessage("项目级别异常")
	public static final int PROJECT_ERROR = -900000;

	@ErrorMessage("参数[%s]不能为空")
	public static final int EMPTY_ARGUMENT_ERROR = -900001;

	@ErrorMessage("[%s]不存在")
	public static final int NOT_EXISTS_DATA_ERROR = -900002;

	@ErrorMessage("[%s]不是[%s]的实现类")
	public static final int CLASS_IMPL_ERROR = -900003;

	@ErrorMessage("暂不支持的功能类型：[%s]")
	public static final int NOT_SUPPORT_FUNCTION_TYPE_ERROR = -900004;

	@ErrorMessage("重复请求")
	public static final int REPEATABILITY_REQUEST_ERROR = -900005;

	@ErrorMessage("文件内容格式不正确")
	public static final int FILE_CONTENT_FORMAT_ERROR = -900006;

	@ErrorMessage("您还没有登录")
	public static final int NOT_LOGIN_ERROR = -900007;
}

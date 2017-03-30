package com.truncate.base.exception;

/**
 * ����: ������
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(wy940407@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��28��
 * ����ʱ��: 22:07
 */
public class ErrorCode
{

	/**ϵͳ�����쳣**/
	@ErrorMessage("ϵͳ�쳣")
	public static final int SYS_ERROR = -100000;

	/**���ݿ⼶���쳣**/
	@ErrorMessage("���ݿ��쳣")
	public static final int DB_ERROR = -200000;

	/**���缶���쳣**/
	@ErrorMessage("�����쳣")
	public static final int NETWORK_ERROR = -300000;

	/**������ϵͳ�쳣**/
	@ErrorMessage("����ϵͳ[%s]�쳣")
	public static final int THIRD_SYS_ERROR = -400000;

	/****/

	/**��Ŀ�����쳣**/
	@ErrorMessage("��Ŀ�����쳣")
	public static final int PROJECT_ERROR = -900000;

	@ErrorMessage("����[%s]����Ϊ��")
	public static final int EMPTY_ARGUMENT_ERROR = -900001;

	@ErrorMessage("[%s]������")
	public static final int NOT_EXISTS_DATA_ERROR = -900002;

	@ErrorMessage("[%s]����[%s]��ʵ����")
	public static final int CLASS_IMPL_ERROR = -900003;

	@ErrorMessage("�ݲ�֧�ֵĹ������ͣ�[%s]")
	public static final int NOT_SUPPORT_FUNCTION_TYPE_ERROR = -900004;

	@ErrorMessage("�ظ�����")
	public static final int REPEATABILITY_REQUEST_ERROR = -900005;

	@ErrorMessage("�ļ����ݸ�ʽ����ȷ")
	public static final int FILE_CONTENT_FORMAT_ERROR = -900006;

	@ErrorMessage("����û�е�¼")
	public static final int NOT_LOGIN_ERROR = -900007;
}

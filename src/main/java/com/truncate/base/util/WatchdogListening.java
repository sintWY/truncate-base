package com.truncate.base.util;

/**
 * ����: watchdog����
 * ��Ȩ: Copyright (c) 2017
 * ����: truncate(truncate@163.com)
 * �汾: 1.0 
 * ��������: 2017��03��27��
 * ����ʱ��: 10:45
 */
public abstract class WatchdogListening extends Thread
{

	//Ĭ�ϼ��ʱ��
	private static final long DEFAULT_DELAY_TIME = 60000L;

	//���ʱ��
	private long delayTime;

	//�Ƿ������̣߳���Ҫ��������֮ǰ����Դ��⣬�Ƿ���Ҫ��������
	protected boolean isStart()
	{
		return true;
	}

	//true�������˱仯 false��δ�仯
	protected abstract boolean isChange();

	//true���޸ĳɹ� false���޸�ʧ��
	protected abstract boolean doChange();

	//��ʵ�֣��������ѡ���Ƿ���д
	//�仯�¼�ִ�гɹ�����
	protected void doChangeSuccess()
	{
	}

	//��ʵ�֣��������ѡ���Ƿ���д
	//�仯�¼�ִ��ʧ�ܴ���
	protected void doChangeFail()
	{
	}

	public WatchdogListening()
	{
		this(DEFAULT_DELAY_TIME);

	}

	public WatchdogListening(long delayTime)
	{
		//���ػ��߳�����
		setDaemon(true);
		this.delayTime = delayTime;
	}

	public long getDelayTime()
	{
		return delayTime;
	}

	public void setDelayTime(long delayTime)
	{
		this.delayTime = delayTime;
	}

	@Override
	public void run()
	{
		while(isStart())
		{
			if(isChange())
			{
				if(doChange())
				{
					doChangeSuccess();
				}
				else
				{
					doChangeFail();
				}
			}

			try
			{
				Thread.sleep(delayTime);
			}
			catch(InterruptedException e)
			{
			}
		}
	}

}

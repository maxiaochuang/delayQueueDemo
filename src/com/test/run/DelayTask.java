package com.test.run;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Delayed {

	//创建时间
	private long createTime;
	//初始超时时间
	private long delayTime;
	//开始时间
	private long startTime;
	private String data;

	public DelayTask(String data, long delayTime, TimeUnit unit) {
		this.createTime = System.currentTimeMillis();
		this.delayTime = TimeUnit.MILLISECONDS.convert(delayTime, unit);
		this.startTime = this.createTime + this.delayTime;
		this.data = data;
	}

	@Override
	public int compareTo(Delayed arg0) {
		if (this.startTime > ((DelayTask) arg0).startTime) {
			return 1;
		} else if (this.startTime < ((DelayTask) arg0).startTime) {
			return -1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit timeUnit) {
		return timeUnit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	public void printContent() {
		System.out.println("This Object, CreateTime:" + createTime + "  Delay:" + delayTime + "  startTime:" + startTime
				+ "  data:" + data);

	}
}

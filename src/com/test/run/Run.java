package com.test.run;

import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Run {

	public static void main(String[] args) {
		DelayQueue<DelayTask> dbq = new DelayQueue<DelayTask>();
		ExecutorService executor = Executors.newFixedThreadPool(7);
		executor.submit(() -> {
			while (true) {
				try {
					DelayTask dt = new DelayTask(UUID.randomUUID().toString(), 2, TimeUnit.SECONDS);
					System.out.println("写入延迟2秒..");
					dt.printContent();
					dbq.put(dt);
					System.out.println("queue size:" + dbq.size());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		executor.submit(() -> {
			while (true) {
				try {
					DelayTask task = dbq.take();
					if (task != null) {
						System.out.println("获取1 take task...");
						task.printContent();
					}
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		executor.submit(() -> {
			while (true) {
				try {
					DelayTask task = dbq.take();
					if (task != null) {
						System.out.println("获取2 take task...");
						task.printContent();
					}
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
	}
}

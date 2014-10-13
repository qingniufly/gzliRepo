package com.simon.concurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

	class UnsafeTask implements Runnable {
		private Date startDate;

		@Override
		public void run() {
			startDate = new Date();
			System.out.printf("Starting Thread: %s : %s\n", Thread
					.currentThread().getId(), startDate);
			try {
				TimeUnit.SECONDS.sleep(6 + (int) Math.rint(Math.random() * 10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("Thread Finished: %s : %s\n", Thread
					.currentThread().getId(), startDate);
		}

	}

	public static class SafeTask implements Runnable {
		private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
			protected Date initialValue() {
				return new Date();
			};
		};

		@Override
		public void run() {
			System.out.printf("Starting Thread: %s : %s\n", Thread
					.currentThread().getId(), startDate.get());
			try {
				TimeUnit.SECONDS.sleep(6 + (int) Math.rint(Math.random() * 10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("Thread Finished: %s : %s\n", Thread
					.currentThread().getId(), startDate.get());
		}

	}

	public static void main(String[] args) {
//		UnsafeTask task = new ThreadLocalTest().new UnsafeTask();
		SafeTask task = new SafeTask();
		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(task);
			t.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

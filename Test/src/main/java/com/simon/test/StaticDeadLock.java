package com.simon.test;

public class StaticDeadLock {
	
	private static final Object o = new Object();

	static {
		System.out.println(Thread.currentThread().getName() + ":static block enter");
		MyThread thread = new MyThread();
		thread.start();

		try {
			thread.join();
//			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ":static block exit");
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + ":all is well.");
	}

	static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + ":inside mythread");
			o.getClass();
			System.out.println(Thread.currentThread().getName() + ":" + o.getClass().getName());
		}
	}
}

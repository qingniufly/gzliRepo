package com.simon.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 在线程里处理不受控制的异常 Java里有2种异常: 检查异常（Checked exceptions）:
 * 这些异常必须强制捕获它们或在一个方法里的throws子句中。 例如， IOException 或者ClassNotFoundException。
 * 未检查异常（Unchecked exceptions）: 这些异常不用强制捕获它们。例如， NumberFormatException。
 *
 * 在一个线程 对象的 run() 方法里抛出一个检查异常，我们必须捕获并处理他们。 因为 run() 方法不接受 throws
 * 子句。当一个非检查异常被抛出，默认的行为是在控制台写下stack trace并退出程序。 幸运的是, Java
 * 提供我们一种机制可以捕获和处理线程对象抛出的未检测异常来避免程序终结。 在这个指南中, 我们将用实例来学习这个机制。
 * 
 * @author simon
 *
 */
public class CustomUncaughtExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread: %s\n", t.getId());
		System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n", t.getState());
	}

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				int a = Integer.parseInt("TTT");
				System.out.println(a);
			}
		});
		t.setUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());
		t.start();
	}

}

package com.simon.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author  : simon
 * @version : Aug 3, 2014 2:34:00 PM
 *
 **/
public class FutureTaskTest {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		FutureTask<String> task = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(20 * 1000);
				return Thread.currentThread().getName();
			}
		});

		try {
			executor.execute(task);
			System.out.println("Main thread running...");
			String result = task.get();
			System.out.printf("result:%s%n", result);
			executor.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}

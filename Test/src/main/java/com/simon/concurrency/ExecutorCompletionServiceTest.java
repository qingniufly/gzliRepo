package com.simon.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorCompletionServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// get all result
		int tNum = 10;
		ExecutorService exec = Executors.newFixedThreadPool(tNum);
		ExecutorCompletionService<String> ecs = new ExecutorCompletionService<String>(exec);
		for (int i = 0; i < tNum; i++) {
			ecs.submit(new StrCallable(i));
		}
		
		for (int i = 0; i < tNum; i++) {
			System.out.println(ecs.take().get());
		}
//		exec.shutdown();
		
		ecs = new ExecutorCompletionService<String>(exec);
		
		// get only one result
		System.out.println("Get one result and exit");
		List<Future<String>> futures = new ArrayList<>();
		for (int i = 0; i < tNum; i++) {
			futures.add(ecs.submit(new StrCallable(i)));
		}
		for (int i = 0; i < tNum; i++) {
			String result = ecs.take().get();
			if (result != null) {
				System.out.println("Finally result is : " + result);
				break;
			}
		}
		for (Future<String> f : futures) {
			f.cancel(true);
		}
		exec.shutdown();
	}

	static class StrCallable implements Callable<String> {

		private int i;

		public StrCallable() {
		}

		public StrCallable(int i) {
			this.i = i;
		}

		@Override
		public String call() throws Exception {
			Random rand = new Random();
			int n = rand.nextInt(10);
			System.out.println("Thread" + i + " will sleep " + n + "s");
			TimeUnit.SECONDS.sleep(n);
			return "task return : " + i;
		}

	}

}

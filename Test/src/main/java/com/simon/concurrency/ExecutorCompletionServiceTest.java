package com.simon.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorCompletionServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		int tNum = 10;
		ExecutorService exec = Executors.newFixedThreadPool(tNum);
		ExecutorCompletionService<String> ecs = new ExecutorCompletionService<String>(exec);
		for (int i = 0; i < tNum; i++) {
			ecs.submit(new StrCallable(i));
		}
		
		for (int i = 0; i < tNum; i++) {
			System.out.println(ecs.take().get());
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
			TimeUnit.SECONDS.sleep(rand.nextInt(10));
			return "task return : " + i;
		}

	}

}

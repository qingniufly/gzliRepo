package com.simon.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CounterTest {

	public static Counter getCounter(String type) {
		Counter counter = null;
		switch (type.toLowerCase()) {
			case "atomic":
				counter = new AtomicCounter();
				break;
			case "cas":
				try {
					counter = new CASCounter();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "lock":
				counter = new LockCounter();
				break;
			case "sync":
				counter = new SyncCounter();
				break;
			default:
				counter = new StupidCounter();
				break;
		}
		return counter;
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		int numOfThreads = 1000;
		int numOfIncrements = 100_000;
		ExecutorService es = Executors.newFixedThreadPool(numOfThreads);
		Counter counter = getCounter("cas");
		long start = System.currentTimeMillis();
		for (int i = 0; i < numOfThreads; i++) {
			es.submit(new CounterClient(counter, numOfIncrements));
		}
		es.shutdown();
		es.awaitTermination(1, TimeUnit.MINUTES);
		System.out.println("Counter result:" + counter.getCount());
		System.out.println("Time elapsed(ms):" + (System.currentTimeMillis() - start));
		
	}
	
}

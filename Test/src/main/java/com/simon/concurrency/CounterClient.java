package com.simon.concurrency;

public class CounterClient implements Runnable {

	private Counter counter;
	
	private int num;
	
	public CounterClient(Counter counter, int num) {
		this.counter = counter;
		this.num = num;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < num; i++) {
			counter.increase();
		}
		
	}

}

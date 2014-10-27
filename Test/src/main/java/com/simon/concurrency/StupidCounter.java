package com.simon.concurrency;

public class StupidCounter implements Counter {

	private long counter = 0;
	
	@Override
	public void increase() {
		counter++;
	}

	@Override
	public long getCount() {
		return counter;
	}

}

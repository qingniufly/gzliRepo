package com.simon.concurrency;

public class SyncCounter implements Counter {

	private long counter = 0;
	
	@Override
	public synchronized void increase() {
		counter++;
	}

	@Override
	public long getCount() {
		return counter;
	}

}

package com.simon.concurrency;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {

	private AtomicLong counter = new AtomicLong(0);
	
	@Override
	public void increase() {
		counter.incrementAndGet();
	}

	@Override
	public long getCount() {
		return counter.get();
	}

}

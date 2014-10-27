package com.simon.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockCounter implements Counter {

	private long counter = 0;
	private Lock lock = new ReentrantReadWriteLock().writeLock();
	
	@Override
	public void increase() {
		lock.lock();
		counter++;
		lock.unlock();
	}

	@Override
	public long getCount() {
		return counter;
	}

}

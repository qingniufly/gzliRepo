package com.simon.lmax.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements Counter {

	private static long foo = 0L;
	
	private static Lock lock = new ReentrantLock(); 
	
	private static String name = "Lock Counter";
	
	/**
	 * Single Thread
	 * Run cost : 8808
	 */
	@Override
	public void increment() {
		for (long l = 0; l < 500000000L; l++) {
			lock.lock();
			try {
				foo++;
			} finally {
				lock.unlock();
			}
		}
		System.out.println(name + " : " + foo);
	}
	
}

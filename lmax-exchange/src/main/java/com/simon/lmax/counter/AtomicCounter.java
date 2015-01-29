package com.simon.lmax.counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {

	private static AtomicLong atomicFoo = new AtomicLong(0l);
	
	private static String name = "Atomic Counter";
	
	/**
	 * Single Thread 
	 * Run cost : 5105
	 */
	@Override
	public void increment() {
		for (long l = 0; l < 500000000L; l++) {
			atomicFoo.getAndIncrement();
		}
		System.out.println(name + " : " + atomicFoo.get());
	}

}

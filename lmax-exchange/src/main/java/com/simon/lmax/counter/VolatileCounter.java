package com.simon.lmax.counter;

public class VolatileCounter implements Counter {

	private static volatile long vfoo = 0l;
	
	private static String name = "Volatile Counter";
	
	/**
	 * Single Thread
	 * Run cost : 3203
	 */
	@Override
	public void increment() {
		for (long l = 0; l < 500000000L; l++) {
			vfoo++;
		}
		System.out.println(name + " : " + vfoo);
	}
	
}

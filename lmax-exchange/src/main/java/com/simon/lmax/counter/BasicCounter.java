package com.simon.lmax.counter;



public class BasicCounter implements Counter {

	private static long foo = 0L;
	
	private static String name = "Basic Counter";
	
	/**
	 * Single Thread
	 * Run cost : 295
	 */
	public void increment() {
		for (long l = 0; l < 500000000L; l++) {
			foo++;
		}
		System.out.println(name + " : " + foo);
	}
	
}

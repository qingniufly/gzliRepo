package com.simon.designpattern.singleton;

public class Singleton {

	private static Singleton instance;
	
	/**
	 * private constructor
	 */
	private Singleton() {
	}
	
	/**
	 * may have more than one instance in multi-thread environment
	 * @return
	 */
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
}

package com.simon.designpattern.singleton;

public class EarlyInitSingleton {
	
	/**
	 * let JVM make the initialization
	 */
	private static EarlyInitSingleton instance = new EarlyInitSingleton();
	
	private EarlyInitSingleton() {
	}

	public static EarlyInitSingleton getInstance() {
		return instance;
	}
	
}

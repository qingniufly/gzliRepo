package com.simon.designpattern.singleton;

public class DoubleCheckLockingSingleton {

	private static volatile DoubleCheckLockingSingleton instance;
	
	private DoubleCheckLockingSingleton() {
	}
	
	public static DoubleCheckLockingSingleton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckLockingSingleton.class) {
				if (instance == null) {
					instance = new DoubleCheckLockingSingleton();
				}
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
		String lock = new String("lock");
		synchronized (lock) {
			System.out.println(lock);
		}
	}
	
}

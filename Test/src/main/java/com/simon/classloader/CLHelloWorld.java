package com.simon.classloader;

public class CLHelloWorld {

	public void sayHello() {
		System.out.println("Hello, world!");
		System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());
	}
	
	public static void main(String[] args) {
		try {
			Class<?> cls = ClassLoader.getSystemClassLoader().loadClass("com.simon.classloader.CLHelloWorld");
			CLHelloWorld ucl = (CLHelloWorld)cls.newInstance();
			ucl.sayHello();
		} catch (Throwable  e) {
			e.printStackTrace();
		}
	}

}

package com.simon.test;

public class StaticBlockTest {

	private static String S = "Hello, World!";
	
	static {
		System.out.println(S);
	}
	
	public static void main(String[] args) {
		System.out.println("main method executed");
	}
	
}

package com.simon.jvm;

public class StringInternTest {
	
	public static void main(String[] args) {
		
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1 == str1.intern());
		
		String str2 = new StringBuilder("j").append("ava").toString();
		System.out.println(str2 == str2.intern());
		
	}

}

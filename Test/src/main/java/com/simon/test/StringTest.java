package com.simon.test;

public class StringTest {

	public static void main(String[] args) {
		String str = "abc";
		String str1 = new String("abc");
		String str2 = "abc";
		System.out.println("== [] " + (str == str1));
		System.out.println("== []" + (str == str2));
		System.out.println("equals [] " + str.equals(str1));
	}

}

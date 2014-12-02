package com.simon.test;

public class StringTest {

	public static void main(String[] args) {
		String str = "abc";
		String str1 = new String("abc");
		String str2 = "abc";
		String str3 = "a" + new String("bc");
		String str4 = "bc";
		final String bc = "bc";
		String str5 = "a" + str4;
		String str6 = "a" + bc;
		final String str7 = getBC();
		String str8 = "a" + str7;
		String a = "a";
		String b = "b";
		String c = "c";
		String s = a + b + c;
		System.out.println("== [] " + (str == str1));
		System.out.println("== []" + (str == str2));
		System.out.println("equals [] " + str.equals(str1));
		System.out.println("== []" + (str == str3));
		System.out.println("== []" + (str == str5));
		System.out.println("== []" + (str == str6));
		System.out.println("== []" + (str == str8));
		System.out.println("== []" + (str == s));
	}
	
	private static String getBC() {
		return "bc";
	}

}

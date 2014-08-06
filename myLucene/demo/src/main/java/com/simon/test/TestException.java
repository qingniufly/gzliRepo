package com.simon.test;

public class TestException {

	static boolean bValue;

	public static void main(String[] args) {
		System.out.println(bValue);
//		Test t = new Test();
//		t.a ++;
		try {
			test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test() throws Exception {
		throw new Exception("test");
	}

}

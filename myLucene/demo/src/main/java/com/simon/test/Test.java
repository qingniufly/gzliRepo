package com.simon.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

public class Test {

	@SuppressWarnings("unused")
	private volatile int a = -0;

	private static String getNull() {
		return null;
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.a++;

		System.out.println(new DateTime(Test.getNull()));

		Pattern p = Pattern.compile("\\d{1,2}(\\.\\d{1,4})?");
		Matcher m = p.matcher("612");
		System.out.println(m.matches());

		System.out.println(new String("2014-05-14 17:22:40").split("\\D").length);

		char ch = '李';
		System.out.println(ch);
	}

}

package com.simon.main;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * @Author  : simon
 * @version : May 15, 2014 2:58:20 PM
 *
 **/
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println(DateTime.now().toDateTime(DateTimeZone.UTC));
	}

}

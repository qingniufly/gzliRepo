package com.simon.netty.time.pojo;

import java.util.Date;

/**
 * @Author : simon
 * @version : Jul 25, 2014 8:57:44 PM
 *
 **/
public class UnixTime {

	private long value;

	public UnixTime() {
		this(System.currentTimeMillis() / 1000L + 2208988800L);
	}

	public UnixTime(long value) {
		this.value = value;
	}

	public long value() {
		return value;
	}

	@Override
	public String toString() {
		return new Date((value() - 2208988800L) * 1000L).toString();
	}

	public static void main(String[] args) {

		System.out.println(new UnixTime());
	}

}

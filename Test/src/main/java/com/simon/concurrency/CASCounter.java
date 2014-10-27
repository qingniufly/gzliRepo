package com.simon.concurrency;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class CASCounter implements Counter {

	private volatile long counter = 0;
	private long offset = 0;
	private Unsafe unsafe;
	
	public CASCounter() throws Exception {
		Field f = CASCounter.class.getDeclaredField("counter");
		unsafe = UnsafeUtil.getUnsafe();
		offset = unsafe.objectFieldOffset(f);
	}
	
	@Override
	public void increase() {
		long before = counter;
		while(!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
			before = counter;
		}
	}

	@Override
	public long getCount() {
		return counter;
	}

}

package com.simon.jvm;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * VM args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * @author simon
 * @date Dec 23, 2014
 */
public class DirectMemoryOOM {

	private static final int _1M = 1024 * 1024;

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		int i = 0;
		while (true) {
			unsafe.allocateMemory(_1M);
			System.out.println(i++);
		}
	}

}

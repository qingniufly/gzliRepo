package com.simon.concurrency;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import sun.misc.Unsafe;

public class UnsafeUtil {

	public static Unsafe getUnsafe() {
		Unsafe unsafe = null;
		// unsafe = Unsafe.getUnsafe();
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unsafe;
	}

	public static long sizeof(Object obj) {
		long size = 0;
		Unsafe unsafe = getUnsafe();
		Set<Field> fieldSet = new HashSet<Field>();
		Class<?> clz = obj.getClass();
		while (clz != Object.class) {
			for (Field f : clz.getDeclaredFields()) {
				if ((f.getModifiers() & Modifier.STATIC) == 0) {
					fieldSet.add(f);
				}
			}
			clz = clz.getSuperclass();
		}

		for (Field f : fieldSet) {
			long offset = unsafe.objectFieldOffset(f);
			size = Math.max(size, offset);
		}
		return (size / 8 + 1) * 8;
	}

}

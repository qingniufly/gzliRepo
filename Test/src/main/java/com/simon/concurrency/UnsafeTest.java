package com.simon.concurrency;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeTest {

	public static void main(String[] args) throws Exception {
		Unsafe unsafe = null;
		// unsafe = Unsafe.getUnsafe();
		unsafe = UnsafeUtil.getUnsafe();
		A o1 = new A();
		System.out.println(o1.getA());
		A o2 = A.class.newInstance();
		System.out.println(o2.getA());
		A o3 = (A)unsafe.allocateInstance(A.class);
		System.out.println(o3.getA());
		
		SafeGuard guard = new SafeGuard();
		System.out.println(guard.getAccess());
		Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
		unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
		System.out.println(guard.getAccess());
		
		System.out.println(UnsafeUtil.sizeof(guard));
		
		// 修改string
		String password = "l00k@myHor$e";
		String fake = new String(password.replaceAll(".", "?"));
		System.out.println(password);
		System.out.println(fake);
	}

	static class A {
		private long a;

		public A() {
			this.a = 1;
		}

		public long getA() {
			return a;
		}
	}

}

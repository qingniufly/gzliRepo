package com.simon.test;

public class InheritTest {

	public class Parent {
		int i = 10;

		public void sayHi() {
			System.out.println("Parent say : Hi ~" + getI());
		}

		public int getI() {
			return this.i;
		}

		public void setI(int i) {
			this.i = i;
		}

	}

	public class Child extends Parent {
		int i = 20;
		public void sayHi() {
			System.out.println("Child say : Hi ~" + getI());
		}
	}

	public void test() {
		Parent p = new Child();
		Child c = (Child) p;
		System.out.println(p.i + c.i);
		p.sayHi();
		c.sayHi();
		c.setI(1000);
		System.out.println(p.getI());
		c.sayHi();
	}

	public static void main(String[] args) {
		new InheritTest().test();
	}


}

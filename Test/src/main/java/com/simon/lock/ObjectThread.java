package com.simon.lock;

public class ObjectThread implements Runnable {

	private LockTest lock;
	private int n = 0;

	public ObjectThread(LockTest lock, int n) {
		this.lock = lock;
		this.n = n;
	}

	@Override
	public void run() {
		// 无锁方法
		// lock.noSynMethod(this.getId(),this);
		// 对象锁方法1，采用synchronized synInMethod的方式
		lock.synInMethod();
		// 对象锁方法2，采用synchronized(this)的方式
		 lock.synOnMethod();
		// 私有锁方法，采用synchronized(object)的方式
//		 lock.synMethodWithObj();
		// 类锁方法，采用static synchronized increment的方式
//		LockTest.increment();

	}
	
	public static void main(String[] args) {
		System.out.println("start time = " + System.currentTimeMillis()+"ms");
		LockTest lock = new LockTest();
		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(new ObjectThread(lock, i));
			t.start();
		}
	}

}

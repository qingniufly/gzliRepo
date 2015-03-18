package com.simon.synchronize.wait;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WaitNotifyTest extends Thread {

	final CountDownLatch latch;
	
	boolean stop = false;

	ConcurrentLinkedQueue<Long> q = new ConcurrentLinkedQueue<Long>();

	public WaitNotifyTest(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public synchronized void run() {
		try {
			latch.await();
			while (!stop) {
				if (q.size() == 0) {
					wait();
				} else {
					Long v = q.poll();
					System.out.printf("Consume value[%d]\r\n", v);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void addValue(Long v) {
		q.add(v);
		notifyAll();
	}
	
	public synchronized void finish() {
		this.stop = true;
		notifyAll();
	}
	
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		WaitNotifyTest t = new WaitNotifyTest(latch);
		t.addValue(1L);
		t.addValue(2L);
		t.addValue(3L);
		t.start();
		latch.countDown();
		try {
			TimeUnit.SECONDS.sleep(1);
			t.addValue(11L);
			TimeUnit.SECONDS.sleep(2);
			t.addValue(12L);
			TimeUnit.SECONDS.sleep(3);
			t.addValue(13L);
			TimeUnit.SECONDS.sleep(4);
			t.finish();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

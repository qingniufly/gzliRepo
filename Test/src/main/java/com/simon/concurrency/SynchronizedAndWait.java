package com.simon.concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SynchronizedAndWait {

	private static final Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

	// 获得SynchronizedAndWait的monitor1
	public synchronized Integer getNextInt() {
		Integer retVal = null;
		System.out.println(Thread.currentThread() + " is getting ");
		while (retVal == null) {
			// 获得queue的monitor2
			synchronized (queue) {
				try {
					// 释放monitor2，并在monitor2上等待notify
					// 此时并未释放monitor1
					queue.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
				retVal = queue.poll();
			}
		}
		return retVal;
	}

	// 获得SynchronizedAndWait的monitor1
	public synchronized void putInt(Integer i) {
		// 获得queue的monitor2
		synchronized (queue) {
			System.out.println(Thread.currentThread() + " putting : " + i);
			queue.add(i);
			// 向monitor2发送notify,并未立即释放monitor2,直到退出此synchronized block
			queue.notify();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final SynchronizedAndWait test = new SynchronizedAndWait();
		Thread putT = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					test.putInt(i);
				}
			}
		});

		Thread getT = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					Integer ni = test.getNextInt();
					System.out.println("Next int is : " + ni);
				}
			}
		});
		getT.start();
		getT.join();
		putT.start();
		putT.join();
		
		// get线程获得了SynchronizedAndWait的monitor1,之后在queue的monitor2上进行等待，此时并未释放monitor1
		// put线程执行put时，由于不能获取monitor1 lock，故而死等，而get线程也始终不能得到monitor2的notify
	}

}

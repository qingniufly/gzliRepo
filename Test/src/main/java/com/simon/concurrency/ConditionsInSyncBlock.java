package com.simon.concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConditionsInSyncBlock {

	private static final Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

	// 获得SynchronizedAndWait的monitor1
	public Integer getNextInt() {
		Integer retVal = null;
		// 可能有多个get线程在此等待
		synchronized (queue) {
			while (queue.isEmpty()) {
				try {
					System.out.println(Thread.currentThread() + " getter is waitting");
					queue.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// 当queue的monitor得到通知时，多个线程被唤醒，可消费的数目却未必等于被唤醒getter线程数
		synchronized (queue) {
			retVal = queue.poll();
			if (retVal == null) {
				System.out.println(Thread.currentThread() + " getter got nothing");
				throw new IllegalStateException();
			}
			System.out.println(Thread.currentThread() + " getter got :" + retVal);
			return retVal;
		}
	}

	// 获得SynchronizedAndWait的monitor1
	public synchronized void putInt(Integer i) {
		// 获得queue的monitor2
		synchronized (queue) {
			System.out.println(Thread.currentThread() + " putting : " + i);
			queue.add(i);
			// 向monitor2发送notify,并未立即释放monitor2,直到退出此synchronized block
			queue.notify();
//			queue.notifyAll();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final ConditionsInSyncBlock test = new ConditionsInSyncBlock();
		Thread putT = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 2; i++) {
					test.putInt(i);
				}
			}
		});

		Thread[] getters = new Thread[10];
		for (int i = 0; i < 10; i++) {
			Thread getT = new Thread(new Runnable() {
				@Override
				public void run() {
					Integer ni = test.getNextInt();
					System.out.println("Next int is : " + ni);
				}
			});
			getters[i] = getT;
			getT.start();
		}
		putT.start();
		putT.join();
		for(Thread t : getters) {
			t.join();
		}

		// get线程获得了SynchronizedAndWait的monitor1,之后在queue的monitor2上进行等待，此时并未释放monitor1
		// put线程执行put时，由于不能获取monitor1 lock，故而死等，而get线程也始终不能得到monitor2的notify
	}

}

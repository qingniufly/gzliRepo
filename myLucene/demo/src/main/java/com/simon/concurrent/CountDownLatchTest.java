package com.simon.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @Author  : simon
 * @version : Aug 3, 2014 11:49:15 AM
 *
 **/
public class CountDownLatchTest {

	private static int N = 10;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);
		for (int i = 0; i < N; i++) {
			new Thread(new PrintWorker(i * 10 + 1, startSignal, doneSignal)).start();
		}
		System.out.println("Start Print...");
		startSignal.countDown();
		doneSignal.await();
		System.out.println("Done Print");

	}

	static class PrintWorker implements Runnable {

		int beginIndex;

		private CountDownLatch startSignal;

		private CountDownLatch doneSignal;

		public PrintWorker(int beginIndex, CountDownLatch startSignal, CountDownLatch doneSignal) {
			this.beginIndex = beginIndex;
			this.startSignal = startSignal;
			this.doneSignal = doneSignal;
		}

		@Override
		public void run() {
			try {
				startSignal.await();
				for (int i = beginIndex; i < beginIndex + 10; i++) {
					System.out.println(i);
					Thread.sleep(1 * 1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				doneSignal.countDown();
			}
		}

	}


}

package com.simon.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author : simon
 * @version : Aug 3, 2014 1:47:15 PM
 *
 **/
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		SemaphoreTest st = new SemaphoreTest();
		final BoundedHashSet<String> set = st.getSet();
		final Random rand = new Random();
		int n = 10;
		final BlockingQueue<String> queue = new ArrayBlockingQueue<>(n);

		for (int i = 0; i < n; i++) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						String s = "name-" + rand.nextInt(100);
						set.add(s);
						queue.offer(s);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

		while (true) {
			try {
				final String s = queue.poll(10, TimeUnit.SECONDS);
				if (null == s) {
					break;
				}
				executor.execute(new Runnable() {

					@Override
					public void run() {
						set.remove(s);
					}

				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();
	}

	public BoundedHashSet<String> getSet() {
		return new BoundedHashSet<>(2);
	}

	class BoundedHashSet<T> {
		private Set<T> set;

		private Semaphore semaphore;

		public BoundedHashSet(int bound) {
			this.set = Collections.synchronizedSet(new HashSet<T>());
			this.semaphore = new Semaphore(bound, true);
		}

		public void add(T o) throws InterruptedException {
			semaphore.acquire();
			if (!this.set.add(o) ) {
				semaphore.release();
			}
			System.out.printf("add:%s%n", o);
		}

		public void remove(T o) {
			if (set.remove(o)) {
				semaphore.release();
			}
			System.out.printf("release:%s%n", o);
		}

	}

}

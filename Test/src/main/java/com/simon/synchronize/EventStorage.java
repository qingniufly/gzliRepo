package com.simon.synchronize;

import java.util.Date;
import java.util.LinkedList;

public class EventStorage {

	private int maxSize;
	private LinkedList<Date> storage;

	public EventStorage() {
		this.maxSize = 10;
		this.storage = new LinkedList<>();
	}

	public synchronized void set() {
		while (storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		storage.offer(new Date());
		System.out.printf("Set : %d\n", storage.size());
		notifyAll();
	}

	public synchronized void get() {
		while (storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get : %d:%s\n", storage.size(), storage.poll());
	}

}

package com.simon.concurrency;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class CustomThreadFactory implements ThreadFactory {

	private int counter;
	private String name;
	private List<String> stats;

	public CustomThreadFactory(String name) {
		counter = 0;
		this.name = name;
		stats = new ArrayList<>();
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, name + "-Thread_" + counter);
		counter++;
		stats.add(String.format("created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
		return t;
	}

	public String getStats() {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = stats.iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			sb.append("\n");
		}
		return sb.toString();
	}

	public class Task implements Runnable {

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		CustomThreadFactory factory = new CustomThreadFactory("CustomThreadFactory");
		Task task = factory.new Task();
		Thread t;
		System.out.println("Starting the thread");
		t = factory.newThread(task);
		t.start();
		System.out.printf("Factory stats :\n%s\n", factory.getStats());
	}

}

package com.simon.concurrency;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;


/**
 * Java有一种特别的线程叫做守护线程。这种线程的优先级非常低， 通常在程序里没有其他线程运行时才会执行它。
 * 当守护线程是程序里唯一在运行的线程时，JVM会结束守护线程并终止程序。
 * 根据这些特点，守护线程通常用于在同一程序里给普通线程（也叫使用者线程）提供服务。
 * 它们通常无限循环的等待服务请求或执行线程任务。它们不能做重要的任务，
 * 因为我们不知道什么时候会被分配到CPU时间片，并且只要没有其他线程在运行，它们可能随时被终止。
 * JAVA中最典型的这种类型代表就是垃圾回收器。
 * 在这个指南中, 我们将学习如何创建一个守护线程，开发一个用2个线程的例子；
 * 我们的使用线程会写事件到queue, 守护线程会清除queue里10秒前创建的事件。
 * @author simon
 *
 */
public class DaemonThread {

	class Event {
		private Date date;
		private String event;
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getEvent() {
			return event;
		}
		public void setEvent(String event) {
			this.event = event;
		}
		@Override
		public String toString() {
			return "Event [date=" + date + ", event=" + event + "]";
		}
	}

	class WriterTask implements Runnable {

		private Deque<Event> queue;

		public WriterTask(Deque<Event> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while(true) {
				Event event = new Event();
				event.setDate(new Date());
				event.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));
				queue.addFirst(event);
//				System.out.printf("Writer: Size of the queue after add: %s\n", queue.size());
				try {
//					TimeUnit.SECONDS.sleep(1);
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class CleanTask extends Thread {
		private Deque<Event> queue;
		public CleanTask(Deque<Event> queue) {
			this.queue = queue;
			this.setDaemon(true);
		}

		@Override
		public void run() {
			while(true) {
				Date date = new Date();
				clean(date);
			}
		}

		private void clean(Date date) {
			if (queue.size() == 0) {
				return;
			}
			long difference;
			boolean deleted = false;
			do {
				Event e = queue.getLast();
				difference = date.getTime() - e.getDate().getTime();
				if (difference > (10 * 1000)) {
					System.out.printf("Cleaner: %s\n", queue.removeLast());
					deleted = true;
				}
			} while (difference > (10 * 1000));
			if (deleted) {
				System.out.printf("Cleaner: Size of the queue after clean: %d\n", queue.size());
			}
		}
	}

	public static void main(String[] args) {
		DaemonThread daemonT = new DaemonThread();
		Deque<Event> deque = new ArrayDeque<>();
		for (int i = 0; i < 3; i++) {
			WriterTask writer = daemonT.new WriterTask(deque);
			Thread t = new Thread(writer);
			t.start();
		}
		CleanTask cleaner = daemonT.new CleanTask(deque);
		cleaner.start();
	}

}

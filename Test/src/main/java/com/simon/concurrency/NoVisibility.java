package com.simon.concurrency;

public class NoVisibility {
	
	private static boolean ready;
	
	private static int number;
	
	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		Thread t = new ReaderThread();
		t.start();
		number = 42;
		ready = true;
	}

}

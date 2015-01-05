package com.simon.jvm;

/**
 * VM args: -Xss2M
 * @author simon
 * @date Dec 23, 2014
 */
public class StackOOM {

	private void endlessLoop() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + " running...");
		}
	}
	
	public void stackLeakByTooManyThreads() {
		while (true) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					endlessLoop();
				}
			});
			t.start();
		}
	}
	
	public static void main(String[] args) {
		StackOOM s = new StackOOM();
		s.stackLeakByTooManyThreads();
	}
	
}

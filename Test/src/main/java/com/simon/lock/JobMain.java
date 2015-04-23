package com.simon.lock;

public class JobMain {

	public static void main(String[] args) throws InterruptedException {
		PrintQueue queue = new PrintQueue();
		Thread threads[]  = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Job(queue), "Thread" + i);
			threads[i].start();
//			threads[i].join();
		}
//
//		for (int i = 0; i < 10; i++) {
//			threads[i].start();
//		}
	}

}

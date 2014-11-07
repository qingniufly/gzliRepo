package com.simon.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadStopExample {
	
	private static boolean stop;
	
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (!stop) {
					i++;
					System.out.println(i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		t.start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stop = true;
		
	}

}

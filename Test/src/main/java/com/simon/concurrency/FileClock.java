package com.simon.concurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.printf("%s\n", new Date());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.out.printf("The FileClock has been interrupted");
				return;
			}
		}
	}

	public static void main(String[] args) {
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}

}

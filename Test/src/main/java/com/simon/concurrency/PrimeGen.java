package com.simon.concurrency;

import java.util.concurrent.TimeUnit;

public class PrimeGen extends Thread {

	@Override
	public void run() {
		long number = 1;
		while (true) {
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime!\n", number);
			}
			if (isInterrupted()) {
				System.out.println("The Prime Generator has been Interrupted");
				return ;
			}
			try {
				TimeUnit.MILLISECONDS.sleep(10);
				number++;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return ;
			}
		}
	}

	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (long i = 2; i * i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Thread task = new PrimeGen();
		task.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		task.interrupt();
	}

}

package com.simon.synchronize;

import java.util.concurrent.TimeUnit;

public class Account {

	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public synchronized void addAmount(double amount) {
		double tmp = balance;
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp += amount;
		balance = tmp;
	}

	public synchronized void subtractAmount(double amount) {
		double tmp = balance;
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp -= amount;
		balance = tmp;
	}

}

package com.simon.concurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable {

	@Override
	public void run() {
		System.out.printf("Beginning NetworkConnections loading: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("NetworkConnections loading has finished:%s\n", new Date());
	}

	public static void main(String[] args) {
		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader, "DataSourceThread");
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");
		long start = System.currentTimeMillis();
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Configuration has been loaded: %s\n",
				new Date());
		System.out.println("Total used time is "
				+ (System.currentTimeMillis() - start) / 1000 + "s");
	}
}

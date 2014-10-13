package com.simon.synchronize;

public class EventMain {

	public static void main(String[] args) {
		EventStorage storage = new EventStorage();
		EventProducer producer = new EventProducer(storage);
		EventConsumer consumer = new EventConsumer(storage);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Both finished");
	}

}

package com.simon.concurrency;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class CalcMain {

	private static void writeThreadInfo(PrintWriter pw, Thread thread,
			State state) {
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main : ************************************\n");
		pw.flush();
	}

	public static void main(String[] args) {
		Thread threads[] = new Thread[10];
		State status[] = new State[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if ((i % 2) == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}
		try {
			FileWriter file = new FileWriter("/tmp/calcState.log");
			PrintWriter pw = new PrintWriter(file);
			for (int i = 0; i < 10; i++) {
				pw.println("Main : Status of Thread " + i + " : "
						+ threads[i].getState());
				status[i] = threads[i].getState();
			}
			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}
			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState() != status[i]) {
						writeThreadInfo(pw, threads[i], status[i]);
						status[i] = threads[i].getState();
					}
				}

				finish = true;
				for (int i = 0; i < 10; i++) {
					finish = finish
							&& (threads[i].getState() == State.TERMINATED);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/*
	 * 
		stupid
		Counter result:99759758
		Time elapsed(ms):343
		Counter result:9964128
		Time elapsed(ms):312
		
		
		sync
		Counter result:100000000
		Time elapsed(ms):5688
		Counter result:10000000
		Time elapsed(ms):608
		
		
		lock
		Counter result:100000000
		Time elapsed(ms):3627
		Counter result:10000000
		Time elapsed(ms):655
		
		
		atomic
		Counter result:100000000
		Time elapsed(ms):7271
		Counter result:10000000
		Time elapsed(ms):883
		
		
		cas
		Counter result:100000000
		Time elapsed(ms):9411
		Counter result:10000000
		Time elapsed(ms):963
	 */
}

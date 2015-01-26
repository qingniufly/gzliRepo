package com.simon.thread;

public class ThreadSleepYieldTest {
	
	static class Print4NumsTask extends Thread {
		
		private String name;
		
		public Print4NumsTask(String name) {
			this.name = name;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < 4; i++) {
				System.out.println(name + " : " + i);
//				Thread.yield();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Print4NumsTask t1 = new Print4NumsTask("t1");
		Print4NumsTask t2 = new Print4NumsTask("t2");
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
	}

}

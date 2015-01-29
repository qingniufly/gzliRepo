package com.simon.lmax.counter;

public class SingleThreadCounterTest implements Counter {
	
	private Counter counter;
	
	public void setCounter(Counter counter) {
		this.counter = counter;
	}
	
	public void increment() {
		long start = System.currentTimeMillis();
		counter.increment();
		TimeCostUtil.printCostInfo(start, System.currentTimeMillis());
	}
	
	public static void main(String[] args) {
		Counter[] counters = new Counter[] {new BasicCounter(),
				new VolatileCounter(), new AtomicCounter(), new LockCounter()};
		SingleThreadCounterTest tester = new SingleThreadCounterTest();
		for (Counter c : counters) {
			tester.setCounter(c);
			tester.increment();
		}
	}
	
}

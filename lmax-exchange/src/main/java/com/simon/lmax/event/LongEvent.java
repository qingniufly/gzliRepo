package com.simon.lmax.event;

import com.lmax.disruptor.EventFactory;

public class LongEvent {

	public static LEventFactory INSTANCE = new LEventFactory();
	
	private long value;
	
	public void set(long value) {
		this.value = value;
	}
	
	public long get() {
		return value;
	}
	
	static class LEventFactory implements EventFactory<LongEvent> {

		@Override
		public LongEvent newInstance() {
			return new LongEvent();
		}
		
	}
	
}

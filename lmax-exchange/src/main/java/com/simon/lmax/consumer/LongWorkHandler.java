package com.simon.lmax.consumer;

import com.lmax.disruptor.WorkHandler;
import com.simon.lmax.event.LongEvent;

public class LongWorkHandler implements WorkHandler<LongEvent> {

	private int idx;
	
	public LongWorkHandler(int idx) {
		this.idx = idx;
	}
	
	@Override
	public void onEvent(LongEvent event) throws Exception {
		System.out.println("Handler " + idx + " received : " + event.get());
	}

}

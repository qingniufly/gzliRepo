package com.simon.lmax.consumer;

import com.lmax.disruptor.EventHandler;
import com.simon.lmax.event.LongEvent;

public class LongEventHandler implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("① --> : " + event.get());
		event.set(event.get() + 1);
	}

}

package com.simon.lmax.publisher;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
import com.simon.lmax.event.LongEvent;

public class LongEventProducer {
	
	private RingBuffer<LongEvent> ringBuffer;
	
	public LongEventProducer() {
	}
	
	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer bbuff) {
		long seq = ringBuffer.next();
		try {
			LongEvent event = ringBuffer.get(seq);
			event.set(bbuff.getLong(0));
		} finally {
			ringBuffer.publish(seq);
		}
	}

}

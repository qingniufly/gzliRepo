package com.simon.lmax.publisher;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.simon.lmax.event.LongEvent;

public class LongEventProducerWithTranslator {

	private RingBuffer<LongEvent> ringBuffer;

	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
		@Override
		public void translateTo(LongEvent event, long sequence, ByteBuffer buff) {
			event.set(buff.getLong(0));
		}
	};
	
	public void onData(ByteBuffer buff) {
		ringBuffer.publishEvent(TRANSLATOR, buff);
	}

}

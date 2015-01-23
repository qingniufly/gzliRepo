package com.simon.lmax.main;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.simon.lmax.consumer.LongEventHandler;
import com.simon.lmax.event.LongEvent;
import com.simon.lmax.event.LongEventFactory;
import com.simon.lmax.publisher.LongEventProducerWithTranslator;

public class LongEventMain {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		Executor exec = Executors.newCachedThreadPool();
		EventFactory<LongEvent> factory = new LongEventFactory();
		final int bufferSize = 16;
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, exec);
		final EventHandler<LongEvent> handler = new LongEventHandler();
		final EventHandler<LongEvent> h2 = new EventHandler<LongEvent>() {
			@Override
			public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
				System.out.println("II -> : " + event.get());
			}
		};
//		disruptor.handleEventsWith(handler, h2);
//		disruptor.handleEventsWith(handler);
//		disruptor.after(handler).handleEventsWith(h2);
//		disruptor.
		disruptor.start();
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		long i = 0;
		while (i < 10) {
//			while (i < Long.MAX_VALUE) {
			byteBuffer.putLong(0, i++);
			producer.onData(byteBuffer);
//			Thread.sleep(1000);
		}
		disruptor.shutdown();
	}
	
}

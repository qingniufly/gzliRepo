package com.simon.lmax.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.FatalExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkerPool;
import com.simon.lmax.consumer.LongWorkHandler;
import com.simon.lmax.event.LongEvent;

public class One2TwoWorkProcessorsTest {

	public static void main(String[] args) {
		int bufferSize = 16;
		RingBuffer<LongEvent> rb = RingBuffer.createSingleProducer(LongEvent.INSTANCE, bufferSize);
		int handlerNum = 2;
		LongWorkHandler[] handlers = new LongWorkHandler[handlerNum];
		for (int i = 0; i < handlerNum; i++) {
			handlers[i] = new LongWorkHandler(i);
		}
		ExecutorService es = Executors.newCachedThreadPool();
		WorkerPool<LongEvent> pool = new WorkerPool<LongEvent>(rb, rb.newBarrier(), new FatalExceptionHandler(), handlers);
		rb.addGatingSequences(pool.getWorkerSequences());
		pool.start(es);
		
		int stopPoint = 20;
		while (true) {
			long seq = rb.next();
			LongEvent event = rb.get(seq);
			event.set(seq);
			rb.publish(seq);
			if (seq >= stopPoint) {
				break;
			}
		}
		
	}
	
}

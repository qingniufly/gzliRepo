package com.simon.lmax.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.simon.lmax.consumer.StepEventHandler;
import com.simon.lmax.consumer.StepEventHandler.OperationType;
import com.simon.lmax.event.LongEvent;

public class One2OneTest {
	
	public static void main(String[] args) throws InterruptedException {
		int bufferSize = 1024;
		RingBuffer<LongEvent> rb = RingBuffer.createSingleProducer(LongEvent.INSTANCE, bufferSize);
		SequenceBarrier barrier = rb.newBarrier();
		EventHandler<LongEvent> handler = new StepEventHandler("①", OperationType.ADD, 10);
		BatchEventProcessor<LongEvent> processor = new BatchEventProcessor<LongEvent>(rb, barrier, handler);
		ExecutorService exec = Executors.newSingleThreadExecutor();
		exec.execute(processor);
		long seq = rb.next();
		LongEvent event = rb.get(seq);
		event.set(100);
		rb.publish(seq);
		exec.awaitTermination(1, TimeUnit.SECONDS);
		System.out.println(event.get());
	}

}

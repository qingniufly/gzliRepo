package com.simon.lmax.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.simon.lmax.consumer.StepEventHandler;
import com.simon.lmax.consumer.StepEventHandler.OperationType;
import com.simon.lmax.event.LongEvent;

public class One2ThreePipelineTest {
	
	public static void main(String[] args) {
		int bufferSize = 512;
		ExecutorService exec = Executors.newCachedThreadPool();
		RingBuffer<LongEvent> rb = RingBuffer.createSingleProducer(LongEvent.INSTANCE, bufferSize);
		
		EventHandler<LongEvent> h1 = new StepEventHandler("①", OperationType.ADD, 5);
		SequenceBarrier sb1 = rb.newBarrier();
		BatchEventProcessor<LongEvent> p1 = new BatchEventProcessor<LongEvent>(rb, sb1, h1);
		exec.submit(p1);
		
		EventHandler<LongEvent> h2 = new StepEventHandler("②", OperationType.MUL, 3);
		SequenceBarrier sb2 = rb.newBarrier(p1.getSequence());
		BatchEventProcessor<LongEvent> p2 = new BatchEventProcessor<LongEvent>(rb, sb2, h2);
		exec.submit(p2);
		
		EventHandler<LongEvent> h3 = new StepEventHandler("③", OperationType.SUB, 20);
		SequenceBarrier sb3 = rb.newBarrier(p2.getSequence());
		BatchEventProcessor<LongEvent> p3 = new BatchEventProcessor<LongEvent>(rb, sb3, h3);
		exec.submit(p3);
		
		rb.addGatingSequences(p3.getSequence());
		
		while(true) {
			long seq = rb.next();
			if (seq >= 2 * bufferSize) {
				break;
			}
			LongEvent event = rb.get(seq);
			event.set(seq);
			rb.publish(seq);
		}
		
	}

}

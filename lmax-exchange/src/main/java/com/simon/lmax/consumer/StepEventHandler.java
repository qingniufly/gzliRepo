package com.simon.lmax.consumer;

import com.lmax.disruptor.EventHandler;
import com.simon.lmax.event.LongEvent;

public class StepEventHandler implements EventHandler<LongEvent> {

	private String step;
	private OperationType opType;
	private int delta;
	
	public StepEventHandler(String step, OperationType opType, int delta) {
		this.step = step;
		this.opType = opType;
		this.delta = delta;
	}
	
	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		StringBuffer sb = new StringBuffer(step);
		sb.append(" ").append(event.get()).append(" ").append(opType.desc()).append(" ").append(delta);
		sb.append(" -> ");
		long value = event.get();
		switch (opType) {
		case ADD:
			value += delta;
			break;
		case SUB:
			value -= delta;
			break;
		case MUL:
			value *= delta;
			break;
		default:
			break;
		}
//		event.set(value);
		sb.append(value);
		System.out.println(sb.toString());
	}
	
	public enum OperationType {
		ADD("+"), SUB("-"), MUL("*");
		
		private String desc;
		
		private OperationType(String desc) {
			this.desc = desc;
		}
		
		public String desc() {
			return desc;
		}
	}

}

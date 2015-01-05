package com.simon.jvm;

/**
 * VM args: -Xss160k
 * @author simon
 * @date Dec 23, 2014
 */
public class StackOverFlow {
	
	private int stackLen = 1;
	
	public void stackLeak() {
		stackLen++;
		stackLeak();
	}

	public static void main(String[] args) {
		StackOverFlow sof = new StackOverFlow();
		try {
			sof.stackLeak();
		} catch (Throwable t) {
			System.out.println("Stack length :" + sof.stackLen);
			throw t;
		}
	}
	
}

package com.simon.hystrix.hello;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class CommandObservableHelloTest {
	
	@Test
	public void test() {
		CommandObservableHello coHelo = new CommandObservableHello("Simon");
		String str;
		try {
			str = coHelo.toObservable().toBlocking().toFuture().get();
			System.out.println(str);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}

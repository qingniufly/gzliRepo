package com.simon.hystrix.hello;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class CommandHelloWorldTest {
	
	@Test
	public void testSynchronous() {
		assertEquals("hello, Simon", new CommandHelloWorld("Simon").execute());
		assertEquals("hello, Bob", new CommandHelloWorld("Bob").execute());
	}
	
	@Test
	public void testAsynchronous() throws InterruptedException, ExecutionException {
		Future<String> f = new CommandHelloWorld("Simon").queue();
		Future<String> fb = new CommandHelloWorld("Bob").queue();
		assertEquals("hello, Simon", f.get());
		assertEquals("hello, Bob", fb.get());
	}
	
	@Test
	public void testObservable() {
		Observable<String> os = new CommandHelloWorld("Simon").observe();
		Observable<String> ob = new CommandHelloWorld("Bob").observe();
		
		// blocking
		assertEquals("hello, Simon", os.toBlocking().single());
		assertEquals("hello, Bob", ob.toBlocking().single());
		
		// non-blocking
		// this is a verbose anoymous inner-class approach and doesn't do assertions;
		os.subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {
				// do nothing
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onNext(String t) {
				System.out.println("onNext: " + t);
			}
		});
		
		ob.subscribe(new Action1<String>() {
			
			@Override
			public void call(String t1) {
				System.out.println("call: " + t1);
			}
		});
	}

}

package com.simon.hystrix.hello;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import rx.Observable;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<String> {

	private final String name;
	
	public CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}
	
	@Override
	protected String run() throws Exception {
		return "hello, " + name;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String s = new CommandHelloWorld("Bob").execute();
		System.out.println(s);
		Future<String> f = new CommandHelloWorld("Simon").queue();
		System.out.println(f.get());
		Observable<String> o = new CommandHelloWorld("Sara").observe();
		System.out.println(o);
		
	}
	
}

package com.simon.hystrix.hello;

import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

public class CommandObservableHello extends HystrixObservableCommand<String> {

	private String name;
	
	public CommandObservableHello(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}
	
	@Override
	protected Observable<String> construct() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						observer.onNext("Hello" );
						observer.onNext(name + "!");
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		});
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CommandObservableHello coHelo = new CommandObservableHello("Simon");
//		String str = coHelo.toObservable().toBlocking().toFuture().get();
//		System.out.println(str);
		coHelo.toObservable().subscribe(new Observer<String>() {

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
	}

}

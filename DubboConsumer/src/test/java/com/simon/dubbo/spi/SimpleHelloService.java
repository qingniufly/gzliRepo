package com.simon.dubbo.spi;

public class SimpleHelloService implements IHelloService {

	@Override
	public void sayHello(String str) {
		System.out.println("Hello, " + str);
	}

}

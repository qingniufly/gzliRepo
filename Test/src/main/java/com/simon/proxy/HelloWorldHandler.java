package com.simon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloWorldHandler implements InvocationHandler {

	private Object obj;

	public HelloWorldHandler(Object obj) {
		super();
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		beforeInvoke(method);
		Object result = method.invoke(obj, args);
		afterInvoke(method);
		return result;
	}

	private void beforeInvoke(Method method) {
		System.out.println("Before invoke method[" + method.getName() + "]");
	}

	private void afterInvoke(Method method) {
		System.out.println("After invoke method[" + method.getName() + "]");
	}

	public static void main(String[] args) {
		IHelloWorld helloWorld = new HelloWorld();
		HelloWorldHandler handler = new HelloWorldHandler(helloWorld);
		IHelloWorld proxy = (IHelloWorld) Proxy.newProxyInstance(helloWorld.getClass().getClassLoader(), helloWorld.getClass().getInterfaces(), handler);
		proxy.sayHello();
	}

}

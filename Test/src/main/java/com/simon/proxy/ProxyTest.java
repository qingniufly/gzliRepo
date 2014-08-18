package com.simon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;

public class ProxyTest {

	@SuppressWarnings("rawtypes")
	public static List getList(final List list) {
		List result = (List) Proxy.newProxyInstance(Thread.currentThread().getClass().getClassLoader(), new Class[] { List.class }, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				if ("add".equalsIgnoreCase(method.getName())) {
					throw new UnsupportedOperationException();
				} else {
					return method.invoke(list, args);
				}
			}
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<String> linkedList = new LinkedList<String>();
		linkedList.add("a");
		linkedList.add("b");
		System.out.println("list size is " + linkedList.size());
		List<String> list = ProxyTest.getList(linkedList);
		list.add("123");
		System.out.println(list.contains("123"));
	}
}

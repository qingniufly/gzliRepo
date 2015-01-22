package com.simon.dubbo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class HelloServiceTest {
	
	public static void main(String[] args) {
		IHelloService hs = ExtensionLoader.getExtensionLoader(IHelloService.class).getAdaptiveExtension();
		hs.sayHello("Simon");
	}

}

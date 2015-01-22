package com.simon.dubbo.spi;

import com.alibaba.dubbo.common.extension.SPI;

@SPI("simple")
public interface IHelloService {
	
	public void sayHello(String str);

}

package com.simon.dubbo.spi;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

@Adaptive
public class AdaptiveHelloService implements IHelloService {

	private String extName;
	
	public void setExtName(String extName) {
		this.extName = extName;
	}
	
	@Override
	public void sayHello(String str) {
		IHelloService service = null;
		if (extName == null) {
			service = ExtensionLoader.getExtensionLoader(IHelloService.class).getDefaultExtension();
		} else {
			service = ExtensionLoader.getExtensionLoader(IHelloService.class).getExtension(extName);
		}
		System.out.println("Using adaptive hello-service, saying:");
		service.sayHello(str);
	}

}

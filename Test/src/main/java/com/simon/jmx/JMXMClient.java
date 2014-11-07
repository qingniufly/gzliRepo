package com.simon.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class JMXMClient {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName oname = new ObjectName("com.simon.jmx:type=Hello");
		Hello mbean = new Hello();
		mbs.registerMBean(mbean, oname);
		
		System.out.println("Waiting forever...");
		Thread.sleep(Long.MAX_VALUE);
		
	}
}

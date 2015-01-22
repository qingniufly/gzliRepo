package com.simon.system;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;

public class SystemInfoTest {

	public static double printSysLoadInfo() {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		try {
			Method method = OperatingSystemMXBean.class.getMethod("getSystemLoadAverage", new Class<?>[0]);
			double load = (Double)method.invoke(operatingSystemMXBean, new Object[0]);
			System.out.println("Current system load is : " + load);
			return load;
		} catch (Throwable t) {
			t.printStackTrace();
			return -1;
		}
		
	}
	
	public static void main(String[] args) {
		SystemInfoTest.printSysLoadInfo();
	}
	
}

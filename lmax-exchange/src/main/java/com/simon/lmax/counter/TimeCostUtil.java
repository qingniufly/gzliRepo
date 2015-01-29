package com.simon.lmax.counter;

public class TimeCostUtil {

	/**
	 * 计算并打印程序运行总时间
	 * @param start 程序开始时间，单位毫秒
	 * @param end 程序结束时间，单位毫秒
	 * @return 程序运行的总毫秒数
	 */
	public static long printCostInfo(long start, long end) {
		long costMs = end - start;
		System.out.println("Run cost : " + costMs);
		return costMs;
	}
	
}

package com.simon.concurrency;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SingletonFactorizer {
	
	private static final SingletonFactorizer instance = new SingletonFactorizer();
	
	private volatile ImmutableOneValueCache cache = new ImmutableOneValueCache(null, null);
	
	private SingletonFactorizer() {
		
	}

	public static SingletonFactorizer getInstance() {
		return instance;
	}
	
	public void calculateFactors(BigInteger i) {
		BigInteger[] factors = cache.getFactors(i);
		if (factors == null) {
			factors = factor(i);
			cache = new ImmutableOneValueCache(i, factors);
		}
		printInfo(cache);
	}

	private void printInfo(ImmutableOneValueCache cache2) {
		System.out.println(Thread.currentThread().getName() + " : " + cache2);
	}

	private BigInteger[] factor(BigInteger n) {
		long num = n.longValue();
		List<BigInteger> list = new ArrayList<>();
		for (int k = 2; k * k <= num;) {
			if (num % k == 0) {
				list.add(BigInteger.valueOf(k));
				num /= k;
			} else {
				k++;
			}
		}
		list.add(BigInteger.valueOf(num));
		return list.toArray(new BigInteger[list.size()]);
	}
	
	public static void main(String[] args) {
//		getInstance().factor(BigInteger.valueOf(14));
		final int[] nums = {4,8,6,14,9};
		for (int i = 0; i < 20; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < nums.length; j++) {
						getInstance().calculateFactors(BigInteger.valueOf(nums[j]));
					}
				}
			}).start();
		}
	}
	
}

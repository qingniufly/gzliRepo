package com.simon.concurrency;

import java.math.BigInteger;

import org.bouncycastle.util.Arrays;

public final class ImmutableOneValueCache {

	private final BigInteger lastNumber;
	private final BigInteger[] factors;
	
	public ImmutableOneValueCache(BigInteger i, BigInteger[] factors) {
		this.lastNumber = i;
		this.factors = factors;
	}
	
	public BigInteger[] getFactors(BigInteger i) {
		if (lastNumber == null || !lastNumber.equals(i)) {
			return null;
		}
		return Arrays.copyOf(factors, factors.length);
	}
	
	@Override
	public String toString() {
		return "ImmutableOneValueCache [lastNumber=" + lastNumber + ", factors=" + java.util.Arrays.toString(factors)
				+ "]";
	}
	
}

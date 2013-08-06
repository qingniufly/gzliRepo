package com.simon.designpattern.strategy;

public class PrimeJudge implements IJudgeNum {

	@Override
	public boolean judge(int num) {
		if (num < 0) {
			num = 0 - num;
		}
		if (num == 1 || num == 0) {
			return false;
		}
		for (int i = 2; Math.pow(i, 2) < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}

package com.simon.designpattern.strategy;

public class PositiveJudge implements IJudgeNum {

	@Override
	public boolean judge(int num) {
		if (num > 0) {
			return true;
		}
		return false;
	}

}

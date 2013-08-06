package com.simon.designpattern.strategy;

public class OddJudge implements IJudgeNum {

	@Override
	public boolean judge(int num) {
		if ((num & 1) != 0) {
			return true;
		}
		return false;
	}

}

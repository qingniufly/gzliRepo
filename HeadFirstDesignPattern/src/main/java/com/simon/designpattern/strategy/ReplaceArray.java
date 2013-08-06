package com.simon.designpattern.strategy;

public class ReplaceArray {

	IJudgeNum judgeNum;

	public ReplaceArray(IJudgeNum judgeNum) {
		this.judgeNum = judgeNum;
	}

	public void replaceArr(int[] arr) {
		if (arr.length == 0 || arr.length == 1) {
			return;
		}
		int head = 0, tail = arr.length - 1;
		while (head < tail) {
			while (judgeNum.judge(arr[head])) {
				head++;
			}
			while (!judgeNum.judge(arr[tail])) {
				tail--;
			}
			if (head < tail) {
				int tmp = arr[head];
				arr[head] = arr[tail];
				arr[tail] = tmp;
			}
		}
	}

	public static void main(String[] args) {
//		int[] arr = new int[] {1, 3, 6, 7, 2, 14, 9, 8};
		int[] arr = new int[] {1, 3, 7, 2, 14, 8};
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				System.out.println(arr[i]);
			} else {
				System.out.print(arr[i] + ", ");
			}
		}
		ReplaceArray ra = new ReplaceArray(new OddJudge());
		ra.replaceArr(arr);
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				System.out.println(arr[i]);
			} else {
				System.out.print(arr[i] + ", ");
			}
		}
		ra = new ReplaceArray(new PrimeJudge());
		ra.replaceArr(arr);
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				System.out.println(arr[i]);
			} else {
				System.out.print(arr[i] + ", ");
			}
		}
	}

}

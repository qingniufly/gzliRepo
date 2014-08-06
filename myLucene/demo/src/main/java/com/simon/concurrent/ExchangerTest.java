package com.simon.concurrent;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

/**
 * @Author : simon
 * @version : Aug 3, 2014 2:45:18 PM
 *
 **/
public class ExchangerTest {

	public static void main(String[] args) {

		final Exchanger<ArrayList<Integer>> exchanger = new Exchanger<ArrayList<Integer>>();
		final ArrayList<Integer> buff1 = new ArrayList<Integer>(10);
		final ArrayList<Integer> buff2 = new ArrayList<Integer>(10);

		new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayList<Integer> buff = buff1;
				try {
					while (true) {
						if (buff.size() >= 10) {
							buff = exchanger.exchange(buff);// 开始跟另外一个线程交互数据
							System.out.println("exchange buff1");
							System.out.println("After exchange buff1 size is " + buff.size());
							Thread.sleep(3 * 1000);
//							buff.clear();
						}
						buff.add((int) (Math.random() * 100));
						System.out.println("Sleeping......");
						Thread.sleep((long) (1 * 1000));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayList<Integer> buff = buff2;
				while (true) {
					try {
						if (buff.size() == 0) {
							System.out.println("Buff2 init empty");
						}
						for (Integer i : buff) {
							System.out.println(i);
						}
						buff.clear();
						Thread.sleep(1000);
						buff = exchanger.exchange(buff);// 开始跟另外一个线程交换数据
						System.out.println("exchange buff2");
						System.out.println("After exchange buff2 size is " + buff.size());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}

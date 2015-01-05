package com.simon.dubbo.api;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.alibaba.dubbo.common.serialize.support.kryo.KryoFactory;
import com.esotericsoftware.kryo.Kryo;

public class KryoFactoryTest {
	
	public static void main(String[] args) throws InterruptedException {
		final Set<Kryo> set = new HashSet<Kryo>();
		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Kryo k = KryoFactory.getDefaultFactory().getKryo();
					System.out.println(k);
					set.add(k);
				}
			}).start();
		}
		TimeUnit.SECONDS.sleep(3);
		System.out.println(set.size());
	}

}

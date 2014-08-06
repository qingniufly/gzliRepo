package com.simon.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author  : simon
 * @version : Jun 20, 2014 10:39:44 PM
 *
 **/
public class CuratorFrameworkTest {

	public static void main(String[] args) {
		try {
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
			CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").retryPolicy(retryPolicy).build();
			client.start();
			byte[] data = client.getData().forPath("/zk_test");
			System.out.println(new String(data));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

package com.simon.zk.test.zkclient;

import com.github.zkclient.ZkClient;

public class ZKClientTest {

	public static void main(String[] args) {
		String cnnString = "127.0.0.1:2181";
		ZkClient client = new ZkClient(cnnString, 5 * 60 * 1000, 3 * 1000);
		if (client.exists("/zkclient")) {
			client.deleteRecursive("/zkclient");
		}
		client.createPersistent("/zkclient/test/perst/p1", true);
		client.createPersistent("/zkclient/test/perst/p2", "zkclient persistent hello world".getBytes());
		client.createPersistentSequential("/zkclient/test/perst/p3", new byte[0]);
		
		client.createEphemeral("/zkclient/test/tmp");
		client.createEphemeral("/zkclient/test/tmp1", "zkclient hello world".getBytes());
		client.createEphemeralSequential("/zkclient/test/tmp2", new byte[0]);
		
		
		int n = client.countChildren("/zkclient/test/perst");
		System.out.println("Children num for path: /zkclient/test/perst is: " + n);
		for (String p : client.getChildren("/zkclient/test/perst")) {
			System.out.println(p);
		}
		
		byte[] tmpData = client.readData("/zkclient/test/tmp1");
		byte[] pData = client.readData("/zkclient/test/perst/p2");
		System.out.println(new String(tmpData));
		System.out.println(new String(pData));
		
		client.close();
		
		
	}
	
}

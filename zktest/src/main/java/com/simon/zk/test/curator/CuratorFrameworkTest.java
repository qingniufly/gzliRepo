package com.simon.zk.test.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.WatchPathable;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorFrameworkTest {
	
	public static CuratorFramework client;
	
	public static RetryPolicy retryPolicy;
	
	public static final String CONNSTRING = "127.0.0.1:2181";
	
	static {
		retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.builder().connectString(CONNSTRING).retryPolicy(retryPolicy).build();
		client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
			
			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {
				switch (newState) {
				case CONNECTED:
					System.out.println("zookeeper connected!");
					break;
				case LOST:
					System.out.println("zookeeper connection lost!");
					break;
				case READ_ONLY:
					System.out.println("zookeeper read-only connected!");
					break;
				case RECONNECTED:
					System.out.println("zookeeper re-connected!");
					break;
				case SUSPENDED:
					System.out.println("zookeeper connection is suspended!");
					break;
				default:
					break;
				}
				
			}
		});
		client.start();
	}
	
	public static void createNode(String path, CreateMode mode, byte[] data) {
		chkClient();
		try {
			client.create().creatingParentsIfNeeded().withMode(mode).forPath(path, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createPersistentNode(String path, byte[] data) {
		createNode(path, CreateMode.PERSISTENT, data);
	}
	
	public static void createPersistentNode(String path) {
		createPersistentNode(path, new byte[0]);
	}
	
	public static Stat chkNode(String path, CuratorWatcher watcher) {
		chkClient();
		try {
			if (watcher != null) {
				return client.checkExists().usingWatcher(watcher).forPath(path);
			} else {
				return client.checkExists().forPath(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void deleteNode(String path) {
		chkClient();
		try {
			if (chkNode(path, null) != null) {
				client.delete().deletingChildrenIfNeeded().forPath(path);
			} else {
				System.out.println("Path not exists:" + path) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static byte[] getData(String path, Stat stat, CuratorWatcher watcher) {
		chkClient();
		try {
			Pathable<byte[]> pathable = client.getData();
			if (stat != null) {
				pathable = ((GetDataBuilder)pathable).storingStatIn(stat);
			}
			if (watcher != null) {
				pathable = ((WatchPathable<byte[]>)pathable).usingWatcher(watcher);
			}
			return pathable.forPath(path);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static byte[] getData(String path) {
		Stat stat = chkNode(path, null);
		if (stat != null) {
			return getData(path, null, null);
		} else {
			System.out.println("Path not exist:" + path);
			return null;
		}
	}
	
	public static void closeCnn() {
		chkClient();
		client.close();
	}
	
	private static void chkClient() {
		if (client == null) {
			throw new RuntimeException("Client not inited!");
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		String withoutDataPath = "/curatorTest/parent/child/hello";
		String withDataPath = "/curatorTest/parent/c1/hello";
		String tmpDataPath = "/curatorTest/parent/tmp/hello";
		deleteNode(withoutDataPath);
		deleteNode(withDataPath);
		createPersistentNode(withoutDataPath);
		createPersistentNode(withDataPath, "Hello, World".getBytes());
		byte[] data = getData(withDataPath);
		if (data != null) {
			System.out.println("Data for path:" + withoutDataPath +  " is:" + new String(data));
		}
		createNode(tmpDataPath, CreateMode.EPHEMERAL, "Ephemeral hello world!".getBytes());
		data = getData(tmpDataPath);
		if (data != null) {
			System.out.println("Data for path:" + tmpDataPath +  " is:" + new String(data));
		}
		closeCnn();
	}
	
}

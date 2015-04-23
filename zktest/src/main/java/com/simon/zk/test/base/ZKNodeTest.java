package com.simon.zk.test.base;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKNodeTest {

	private static int DEFAULT_SESSION_TIMEOUT = 3000;

	private ZooKeeper zk;

	private ZKNodeTest(Builder builder) {
		try {
			zk = new ZooKeeper(builder.getConnString(), builder.getSessionTimeout(), null);
		} catch (IOException e) {
			System.out.println(e.toString());
			zk = null;
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public void createNode(String path, CreateMode mode) {
		checkZK();
		try {
			zk.create(path, new byte[0], Ids.OPEN_ACL_UNSAFE, mode);
		} catch (InterruptedException | KeeperException e) {
			e.printStackTrace();
			throw new RuntimeException("zk exception");
		}
	}

	public void createPersistentNode(String path) {
		createNode(path, CreateMode.PERSISTENT);
	}

	public void createEphemeralNode(String path) {
		createNode(path, CreateMode.EPHEMERAL);
	}

	public Stat existNode(String path) {
		checkZK();
		try {
			Stat stat = zk.exists(path, false);
			return stat;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("zk exception");
		}
	}

	public void deleteNode(String path, int version) {
		checkZK();
		try {
			zk.delete(path, version);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("zk exception");
		}
	}
	
	public List<String> getChildrenFor(String path) {
		checkZK();
		try {
			return zk.getChildren(path, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("zk exception");
		}
	}
	
	public void close() {
		checkZK();
		try {
			zk.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("zk exception");
		}
	}

	public void checkZK() {
		if (zk == null) {
			throw new RuntimeException("zk not init!");
		}
	}

	public static class Builder {

		private int sessionTimeout = DEFAULT_SESSION_TIMEOUT;

		private String connString;

		public ZKNodeTest build() {
			return new ZKNodeTest(this);
		}

		public Builder connString(String connString) {
			this.connString = connString;
			return this;
		}

		public Builder sessionTimeout(int sessionTimeout) {
			this.sessionTimeout = sessionTimeout;
			return this;
		}

		public int getSessionTimeout() {
			return sessionTimeout;
		}

		public String getConnString() {
			return connString;
		}

	}

	public static void main(String[] args) {
		// simon's ip 10.3.41.92
		ZKNodeTest test = ZKNodeTest.builder().connString("127.0.0.1:2181").build();
		String path = "/zktest";
		String tmpPath = "/zktest/hello-tmp";
		String pstPath = "/zktest/hello";
		Stat stat = test.existNode(tmpPath);
		if (stat != null) {
			test.deleteNode(tmpPath, stat.getVersion());
		} else {
			System.out.println("Ephemeral node is deleted after session closed/expired!");
		}
		stat = test.existNode(pstPath);
		if (stat != null) {
			test.deleteNode(pstPath, stat.getVersion());
		}
		stat = test.existNode(path);
		if (stat != null) {
			test.deleteNode(path, stat.getVersion());
		}
		test.createNode("/zktest", CreateMode.PERSISTENT);
		test.createEphemeralNode("/zktest/hello-tmp");
		test.createPersistentNode("/zktest/hello");
		test.createNode("/zktest/hello", CreateMode.EPHEMERAL_SEQUENTIAL);
		test.createNode("/zktest/hello", CreateMode.PERSISTENT_SEQUENTIAL);
		test.close();
		test = ZKNodeTest.builder().connString("127.0.0.1:2181").build();
		List<String> childs = test.getChildrenFor(path);
		System.out.println("Children num for " + path + " : " + childs.size());
		for (String s : childs) {
			System.out.println(s);
		}
//		test.close();
	}

}

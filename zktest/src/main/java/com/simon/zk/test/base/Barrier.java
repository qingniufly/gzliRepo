package com.simon.zk.test.base;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class Barrier extends SyncPrimitive {

	int size;
	
	String name;
	
	public Barrier(String address, String root, int size) {
		super(address);
		this.root = root;
		this.size = size;
		
		// create Barrier node;
		if (zk != null) {
			try {
				Stat s = zk.exists(root, false);
				if (s == null) {
					zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}
			} catch (KeeperException e) {
				System.out.println("KeeperException when  instantiating queue :" + e.toString());
			} catch (InterruptedException e) {
				System.out.println("Interrupted exception : " + e.toString());
				e.printStackTrace();
			}
		}
		
		try {
			name = new String(InetAddress.getLocalHost().getCanonicalHostName());
		} catch (UnknownHostException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Join barrier
	 * 
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	boolean enter() throws KeeperException, InterruptedException {
		name = zk.create(root + "/" + name, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		while(true) {
			synchronized (mutex) {
				System.out.println("Try entering...");
				List<String> list = zk.getChildren(root, true);
				if (list.size() < size) {
					mutex.wait();
				} else {
					return true;
				}
			}
		}
	}
	
	boolean leave() throws InterruptedException, KeeperException {
		zk.delete(name, 0);
		while (true) {
			synchronized (mutex) {
				List<String> list = zk.getChildren(root, true);
				if (list.size() > 0) {
					System.out.println("Wait for leave...");
					mutex.wait();
				} else {
					return true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Barrier b = new Barrier("127.0.0.1:2181", "/b1", 5);
		try {
			System.out.println("Entering barrier...");
			boolean flag = b.enter();
			if (!flag) {
				System.out.println("Error when entering barrier");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Leaving barrier...");
			b.leave();
			System.out.println("Left barrier");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

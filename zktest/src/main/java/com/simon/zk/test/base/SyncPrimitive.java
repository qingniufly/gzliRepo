package com.simon.zk.test.base;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class SyncPrimitive implements Watcher {

	static ZooKeeper zk = null;
	static Integer mutex;
	
	String root;
	
	public SyncPrimitive(String address) {
		if (zk == null) {
			try {
				System.out.println("Starting ZK:");
				zk = new ZooKeeper(address, 3000, this);
				mutex = new Integer(-1);
				System.out.println("Finished starting zk: " + zk);
			} catch (IOException e) {
				System.out.println(e.toString());
				zk = null;
			}
		}
		// else mutex = new Integer(-1);
	}
	
	@Override
	public synchronized void process(WatchedEvent event) {
		synchronized (mutex) {
			System.out.println("Process: " + event.getType());
			mutex.notifyAll();
		}

	}

}

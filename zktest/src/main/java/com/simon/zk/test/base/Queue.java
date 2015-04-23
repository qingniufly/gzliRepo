package com.simon.zk.test.base;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class Queue extends SyncPrimitive {

	private static int DEFAULT_QUEUE_SIZE = Integer.MAX_VALUE;

	private int queueSize;

	public Queue(String address, String name) {
		this(address, name, DEFAULT_QUEUE_SIZE);
	}

	public Queue(String address, String name, int queueSize) {
		super(address);
		this.root = name;
		this.queueSize = queueSize;
		// Create ZK node name
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
	}

	/**
	 * Add element to the queue
	 * 
	 * @param i
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	boolean produce(int i) throws KeeperException, InterruptedException {
		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(i);
		byte[] value = b.array();
		while (true) {
			synchronized (mutex) {
				List<String> list = zk.getChildren(root, true);
				if (list.size() >= queueSize) {
					System.out.println("Queue full! Going to wait");
					mutex.wait();
				} else {
					zk.create(root + "/element", value, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
					return true;
				}
			}
		}
	}

	int consume() throws KeeperException, InterruptedException {
		int retValue = -1;
		Stat stat = null;
		while (true) {
			synchronized (mutex) {
				List<String> list = zk.getChildren(root, true);
				if (list.size() == 0) {
					System.out.println("Going to wait");
					mutex.wait();
				} else {
					String minStr = list.get(0).substring(7);
					Integer min = new Integer(list.get(0).substring(7));
					for (String s : list) {
						String tempStr = s.substring(7);
						if (min > new Integer(tempStr).intValue()) {
							min = new Integer(tempStr).intValue();
							minStr = tempStr;
						}
					}
					String str = root + "/element" + minStr;
					System.out.println("Consuming path " + str);
					byte[] data = zk.getData(str, false, stat);
					zk.delete(str, 0);
					ByteBuffer b = ByteBuffer.wrap(data);
					retValue = b.getInt();
					return retValue;
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Queue q = new Queue("127.0.0.1:2181", "/app1", 10);
		System.out.println("Producer");

		ExecutorService pes = Executors.newCachedThreadPool();

		for (int i = 1; i <= 50; i++) {
			final Random r = new Random();
			final int ti = r.nextInt(i);
			pes.submit(new Runnable() {

				@Override
				public void run() {
					try {
						q.produce(ti);
					} catch (KeeperException | InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

		ExecutorService ces = Executors.newCachedThreadPool();

		System.out.println("Consumer");
		for (int i = 0; i < 20; i++) {
			ces.submit(new Runnable() {

				@Override
				public void run() {
					try {
						int r = q.consume();
						System.out.println("Item: " + r);
					} catch (KeeperException | InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		}

		// TimeUnit.SECONDS.sleep(10);

		// for (int i = 0; i < max; i++) {
		// try {
		// int r = q.consume();
		// System.out.println("Item: " + r);
		// } catch (KeeperException e) {
		// i--;
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }

	}

}

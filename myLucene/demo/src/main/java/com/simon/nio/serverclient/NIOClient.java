package com.simon.nio.serverclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author : simon
 * @version : May 20, 2014 10:48:13 AM
 *
 **/
public class NIOClient implements Runnable {

	private SocketChannel socketChannel;

	private ByteBuffer temp = ByteBuffer.allocate(1024);

	private Selector selector;

	private Integer count = 0;

	private Integer idleCount = 0;

	public NIOClient(String host, int port) {
		try {
			this.selector = Selector.open();
			socketChannel = SocketChannel.open();
			InetSocketAddress address = new InetSocketAddress(host, port);
			Boolean isConnected = socketChannel.connect(address);
			socketChannel.configureBlocking(false);
			SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
			System.out.println("Client started");
			System.out.println("Client is listening [" + host + ":" + port + "]");
			if (isConnected) {
				sendFirstMsg();
			} else {
				key.interestOps(SelectionKey.OP_CONNECT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendFirstMsg() throws IOException {
		String msg = "Hello java nio, [" + count + "]";
		socketChannel.write(ByteBuffer.wrap(msg.getBytes(Charset.forName("UTF-8"))));
	}

	@Override
	public void run() {
		while (true) {
			try {
				int num = selector.select(1000);
				if (num == 0) {
					idleCount++;
					if (idleCount > 10) {
						try {
							sendFirstMsg();
						} catch (ClosedChannelException e) {
							e.printStackTrace();
							this.socketChannel.close();
							return;
						}
					}
				} else {
					idleCount = 0;
				}
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					it.remove();
					dispatchKey(key);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void dispatchKey(SelectionKey key) {
		try {
			SocketChannel sc = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (sc.isConnectionPending()) {
					sc.finishConnect();
				}
				sendFirstMsg();
			} else if (key.isReadable()) {
				int readNum = sc.read(temp);
				if (readNum < 0) {
					sc.close();
					return ;
				}
				temp.flip();
				String msg = Charset.forName("UTF-8").decode(temp).toString();
				System.out.println("Client received [" + msg + "] from server [" + sc.getRemoteAddress() +"]");

				Thread.sleep(1000);
				msg = msg.replace("[" + count++ + "]", "[" + count + "]");
				sc.write(Charset.forName("UTF-8").encode(msg));
				temp.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		NIOClient client = new NIOClient("127.0.0.1", 9999);
		new Thread(client).start();
	}

}

package com.simon.nio.serverclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author  : simon
 * @version : May 20, 2014 10:47:56 AM
 *
 **/
public class NIOServer {

	private ServerLoop loop;

	public NIOServer(int port) {
		try {
			loop = new ServerLoop();
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.socket().bind(new InetSocketAddress("localhost", port));
			serverChannel.register(loop.getSelector(), SelectionKey.OP_ACCEPT);
			new Thread(loop).start();
			System.out.println("Server started");
			System.out.println("Server is listening on " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ServerLoop implements Runnable {

		private Selector selector;

		private ByteBuffer temp = ByteBuffer.allocate(1024);

		public Selector getSelector() {
			return selector;
		}

		public ServerLoop() {
			try {
				this.selector = Selector.open();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			while (true) {
				try {
					selector.select();
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> it = keys.iterator();
					while (it.hasNext()) {
						SelectionKey sltKey = it.next();
						it.remove();
						dispatchKey(sltKey);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void dispatchKey(SelectionKey key) {
			if (key.isAcceptable()) {
				try {
					ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
					SocketChannel socketChannel = serverChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(loop.getSelector(), SelectionKey.OP_READ);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (key.isReadable()) {
				try {
					SocketChannel socketChannel = (SocketChannel) key.channel();
					int readNum = socketChannel.read(temp);
					if (readNum < 0) {
						// 客户端已经断开连接
						key.cancel();
						socketChannel.close();
						return ;
					}
					temp.flip();
					String msg = Charset.forName("UTF-8").decode(temp).toString();
					System.out.println("Server received [" + msg + "] from client IP [" + socketChannel.getRemoteAddress() + "]");

					Thread.sleep(1000);

					// echo back.
					socketChannel.write(Charset.forName("UTF-8").encode(msg));
					temp.clear();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}


	public static void main(String[] args) {
		new NIOServer(9999);
	}

}

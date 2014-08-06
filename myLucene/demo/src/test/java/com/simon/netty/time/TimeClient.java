package com.simon.netty.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author  : simon
 * @version : Jul 25, 2014 8:16:43 PM
 *
 **/
public class TimeClient {

	public static void main(String[] args) {
		Bootstrap client = new Bootstrap();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			client.group(workerGroup);
			client.channel(NioSocketChannel.class);
			client.option(ChannelOption.SO_KEEPALIVE, true);
			client.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeClientHandler());
				}
			});
			ChannelFuture f = client.connect("localhost", 9011).sync();
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}

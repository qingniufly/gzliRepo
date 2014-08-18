package com.simon.netty.time.pojo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * @Author  : simon
 * @version : Jul 25, 2014 9:04:24 PM
 *
 **/
public class UnixTimeClient {

	public static void main(String[] args) {
		Bootstrap client = new Bootstrap();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			final SslContext sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
			client.group(workerGroup);
			client.channel(NioSocketChannel.class);
			client.option(ChannelOption.SO_KEEPALIVE, true);
			client.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(sslCtx.newHandler(ch.alloc())).addLast(new UnixTimeDecoder(), new UnixTimeClientHandler());
				}
			});
			ChannelFuture f = client.connect("localhost", 9110).sync();
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}

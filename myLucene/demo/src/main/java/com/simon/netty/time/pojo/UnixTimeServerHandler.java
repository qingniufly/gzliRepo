package com.simon.netty.time.pojo;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author  : simon
 * @version : Jul 25, 2014 9:09:02 PM
 *
 **/
public class UnixTimeServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		UnixTime time = new UnixTime();
		System.out.println(time);
		ChannelFuture f = ctx.writeAndFlush(time);
		f.addListener(ChannelFutureListener.CLOSE);
	}

}

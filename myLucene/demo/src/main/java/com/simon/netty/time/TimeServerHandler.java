package com.simon.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author : simon
 * @version : Jul 25, 2014 7:37:29 PM
 *
 **/
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		final ByteBuf time = ctx.alloc().buffer(4);
//		time.writeInt((int)(System.currentTimeMillis() / 1000L));
		time.writeInt((int)(System.currentTimeMillis() / 1000L + 2208988800L));
		final ChannelFuture future = ctx.writeAndFlush(time);
//		future.addListener(new ChannelFutureListener() {
//			@Override
//			public void operationComplete(ChannelFuture f) throws Exception {
//				assert f == future;
//				ctx.close();
//			}
//		});
		future.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}

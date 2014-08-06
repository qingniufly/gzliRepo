package com.simon.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Author  : simon
 * @version : Jul 25, 2014 8:08:21 PM
 *
 **/
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	private ByteBuf buff = null;

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		buff = ctx.alloc().buffer(4);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		buff.release();
		ctx = null;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf m = (ByteBuf) msg;
		buff.writeBytes(m);
		m.release();
		if (buff.readableBytes() >= 4) {
			long timemills = (buff.readUnsignedInt() - 2208988800L) * 1000L;
			System.out.println(new Date(timemills));
			ctx.close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}

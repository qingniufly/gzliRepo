package com.simon.netty.time.pojo;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @Author  : simon
 * @version : Jul 25, 2014 9:01:26 PM
 *
 **/
public class UnixTimeDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 4) {
			return ;
		}
		out.add(new UnixTime(in.readLong()));
	}

}

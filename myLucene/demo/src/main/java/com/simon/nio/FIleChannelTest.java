package com.simon.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @Author  : simon
 * @version : May 19, 2014 11:49:47 AM
 *
 **/
public class FIleChannelTest {

	public static void main(String[] args) {
		try {
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();
			RandomAccessFile file = new RandomAccessFile("/tmp/nio.txt", "rw");
			FileChannel fchannel = file.getChannel();
			ByteBuffer buff = ByteBuffer.allocate(48);
			CharBuffer charBuff = CharBuffer.allocate(48);
			int number = fchannel.read(buff);
			while (number != -1) {
				System.out.println("Read " + number);
				buff.flip();
				decoder.decode(buff, charBuff, true);
				charBuff.flip();
				System.out.println(charBuff);
				buff.clear();
				charBuff.clear();
				number = fchannel.read(buff);
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

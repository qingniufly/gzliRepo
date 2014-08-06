package com.simon.test;

import java.io.UnsupportedEncodingException;

/**
 * @Author  : simon
 * @version : May 14, 2014 7:41:23 PM
 *
 **/
public class CharUtil {

	public static boolean isChinese(final char ch) {
		try {
			if (String.valueOf(ch).getBytes("GBK").length > 1) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(CharUtil.isChinese('w'));
	}

}

package com.simon.test;

import java.io.UnsupportedEncodingException;

/**
 * @Author  : simon
 * @version : May 14, 2014 7:46:41 PM
 *
 **/
public class StringUtil {

	public static String getFirstNBytes(String str, int n) {
		try {
			if (str == null || n > str.getBytes("GBK").length) {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (int i = 0; count < n - 1; i++) {
			char chI = str.charAt(i);
			if (CharUtil.isChinese(chI)) {
				n--;
			}
			sb.append(chI);
			count++;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.getFirstNBytes("1", 2));
	}

}

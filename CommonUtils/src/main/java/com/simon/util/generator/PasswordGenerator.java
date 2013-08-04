package com.simon.util.generator;
/**
 * author: Simon Lee
 * Date  : Jul 13, 2013
 */

import java.util.Random;

public class PasswordGenerator {

	// 密码字符来源可以是数字、字母、下划线
	private static final char[] allowChars = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', '!', '@', '#', '$', '%', '^', '&', '*', '~',
			'|' };

	// NUM 生成len相应位数的数字密码
	// NUM_CHAR 生成相应长度的数字、字母混合密码
	// NUM_CHAR_SPEC 生成对应长度的字母、数字、特殊字符密码
	public static String genPassword(PasswordLevel level, Integer len) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < len; i++) {
			if (level == PasswordLevel.NUM) {
				sb.append(allowChars[random.nextInt(10)]);
			} else if (level == PasswordLevel.NUM_CHAR) {
				sb.append(allowChars[random.nextInt(10 + 52)]);
			} else if (level == PasswordLevel.NUM_CHAR_SPEC) {
				sb.append(allowChars[random.nextInt(10 + 52 + 10)]);
			} else {
				return null;
			}
		}
		return sb.toString();
	}

	public enum PasswordLevel {
		NUM, NUM_CHAR, NUM_CHAR_SPEC
	}

	public static void main(String[] args) {
		String pwd = PasswordGenerator.genPassword(PasswordLevel.NUM, 6);
		System.out.println(pwd);
		pwd = PasswordGenerator.genPassword(PasswordLevel.NUM_CHAR, 8);
		System.out.println(pwd);
		pwd = PasswordGenerator.genPassword(PasswordLevel.NUM_CHAR_SPEC, 8);
		System.out.println(pwd);
	}

}

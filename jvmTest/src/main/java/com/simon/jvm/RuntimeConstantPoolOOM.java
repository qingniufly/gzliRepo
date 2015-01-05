package com.simon.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author simon
 * @date Dec 23, 2014
 */
public class RuntimeConstantPoolOOM {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int i = Integer.MIN_VALUE;
		while (i < Integer.MAX_VALUE) {
			list.add(String.valueOf(i++).intern());
		}
	}

}

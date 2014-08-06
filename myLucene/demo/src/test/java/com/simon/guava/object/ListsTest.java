package com.simon.guava.object;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @Author  : simon
 * @version : Jun 20, 2014 2:41:16 PM
 *
 **/
public class ListsTest {

	@Test
	public void testPartition() {
		List<String> list = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"
				, "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
		List<List<String>> pList = Lists.partition(list, 10);
		System.out.println(pList.size());
		for (List<String> l : pList) {
			for (String s : l) {
				System.out.print(s);
			}
			System.out.println();
		}

		for (String s : Lists.reverse(list)) {
			System.out.print(s);
		}
	}

}

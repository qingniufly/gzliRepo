package com.simon.java.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapSort {

	public static <K extends Object, V extends Object> List<Map.Entry<K, V>> sort(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new MapComparator<K, V>());
		return list;
	}

	public static class MapComparator<K, V>  implements Comparator<Map.Entry<K, V>> {

		@SuppressWarnings("unchecked")
		@Override
		public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
			return ((Comparable<V>) (o1.getValue())).compareTo(o2.getValue());
		}

	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("1", 2);
		map.put("2", 3);
		map.put("3", 9);
		map.put("4", -1);
		List<Map.Entry<String, Integer>> list = MapSort.sort(map);
		for(Map.Entry entry : list) {
			System.out.println("[" + entry.getKey() + "=" + entry.getValue() + "]");
		}
	}

}

package com.simon.java.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class EmployeeIdComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		int len1 = o1.getId().length();
		int len2 = o2.getId().length();
		int len = Math.min(len1, len2);
		int i = 0;
		for (; i < len; i++) {
			char c1 = o1.getId().charAt(i);
			char c2 = o2.getId().charAt(i);
			if (c1 > c2) {
				return 1;
			} else if (c1 < c2) {
				return -1;
			}
		}
		return len1 - len2;
	}

	public static void main(String[] args) {
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		Employee e3 = new Employee();
		e1.setId("100");
		e1.setName("e1");
		e1.setSalary(4500);
		e2.setId("200");
		e2.setName("e2");
		e2.setSalary(4000);
		e3.setId("300");
		e3.setName("e3");
		e3.setSalary(6000);
		List<Employee> list = new LinkedList<>();
		list.add(e3);
		list.add(e1);
		list.add(e2);
		System.out.println("Before sort:");
		for (Employee e : list) {
			System.out.println(e);
		}
		Collections.sort(list, new EmployeeIdComparator());
		System.out.println("After sort:");
		for (Employee e : list) {
			System.out.println(e);
		}
	}

}

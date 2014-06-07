package com.simon.java.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee implements Comparable<Employee> {

	private String id;

	private String name;

	private Integer salary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}


	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public int compareTo(Employee e) {
		return this.getSalary() - e.getSalary();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary
				+ "]";
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
		List<Employee> list = new ArrayList<>();
		list.add(e1);
		list.add(e2);
		list.add(e3);
		Collections.sort(list);
		for (Employee e : list) {
			System.out.println(e);
		}
	}

}

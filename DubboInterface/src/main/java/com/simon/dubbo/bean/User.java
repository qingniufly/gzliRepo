package com.simon.dubbo.bean;

import java.io.Serializable;


public class User implements Serializable {
	
	private static final long serialVersionUID = -4669514741652563879L;

	private Long id;
	
	private String name;
	
	private int age;
	
	private String birthDate;

	public User(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", birthDate=" + birthDate + "]";
	}
	
}

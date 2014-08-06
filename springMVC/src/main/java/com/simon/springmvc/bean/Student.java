package com.simon.springmvc.bean;
/**
 * @Author  : simon
 * @version : Aug 1, 2014 12:26:11 PM
 *
 **/
public class Student {
	private String id;

	private String name;

	private String gender;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + "]";
	}

}

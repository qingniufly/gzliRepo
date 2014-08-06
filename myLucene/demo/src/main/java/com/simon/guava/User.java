package com.simon.guava;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * @Author : simon
 * @version : Jun 20, 2014 11:13:29 AM
 *
 **/
public class User implements Comparable<User> {

	private String userId;

	private String username;

	private String password;

	private String realName;

	private DateTime birthDate;

	private Integer age;

	private DateTime registerDate;

	public DateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public DateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(DateTime registerDate) {
		this.registerDate = registerDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String toString() {
		return Objects.toStringHelper(this).add("userId", userId).add("username", username).add("realName", realName).add("age", age).toString();
	}

	@Override
	public int compareTo(User o) {
		checkNotNull(o);
		return ComparisonChain.start().compare(age, o.age).compare(userId, o.userId).result();
	}

	@Override
	public boolean equals(Object o) {
		return Objects.equal(this, o);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(userId, username, password, realName, birthDate, age, registerDate);
	}

}

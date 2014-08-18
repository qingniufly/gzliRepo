package com.simon.object;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.joda.time.DateTime;

/**
 * @Author  : simon
 * @version : Aug 12, 2014 10:21:30 AM
 *
 **/
public class Student {

	private String id;

	private String name;

	private DateTime birthDate;

	private String gender;

	public Student(String id, String name, DateTime birthDate, String gender) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
	}

	public Student(Student s) {
		this(s.id, s.name, s.birthDate, s.gender);
	}

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

	public DateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Student other = (Student) obj;
		if (Objects.equals(this.id, other.id)
				&& Objects.equals(this.gender, other.gender)
				&& Objects.equals(this.birthDate, this.birthDate)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int h = 31;
		int result = 1;
		result = h * result + (id == null ? 0 : id.hashCode());
		result = h * result + (gender == null ? 0 : gender.hashCode());
		result = h * result + (birthDate == null ? 0 : birthDate.hashCode());
		return result;
	}

	public static void main(String[] args) {
		DateTime now = new DateTime("1987-08-27");
		Student s1 = new Student("1", "s1", now, "0");
		Student s2 = new Student("1", "s2", now, "1");
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		Map<Student, Integer> map = new HashMap<>();
		map.put(s1, 100);
		map.put(s2, 200);
		System.out.println(map.size());

		System.out.println(map.get(s1));

		Set<Student> set = new HashSet<>();
		set.add(s1);
		set.add(s2);
		System.out.println("Set size is " + set.size());
	}

}

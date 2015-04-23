package com.simon.elastic.type;

import org.elasticsearch.common.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "zen", type = "staff", indexStoreType = "memory",
			shards = 5, replicas = 1, refreshInterval = "-1")
public class Staff {

	@Id
	private String id;
	
	private String name;
	
	private String dept;
	
	private String title;
	
	private int age;
	
	@Field(format = DateFormat.basic_date, type = FieldType.Date)
	private DateTime birthdate;
	
	@Field()
	private String intro;
	
	@Field(format = DateFormat.basic_date, type = FieldType.Date)
	private DateTime joindate;

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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public DateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateTime birthdate) {
		this.birthdate = birthdate;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public DateTime getJoindate() {
		return joindate;
	}

	public void setJoindate(DateTime joindate) {
		this.joindate = joindate;
	}
	
}

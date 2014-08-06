package com.simon.test;

public interface PersonDao {

	public Person fetchPerson(Integer personID);

	public void update(Person person);

}

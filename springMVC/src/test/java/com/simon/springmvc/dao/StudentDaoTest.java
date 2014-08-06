package com.simon.springmvc.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simon.springmvc.bean.Student;

/**
 * @Author  : simon
 * @version : Aug 1, 2014 1:00:59 PM
 *
 **/
@ContextConfiguration(locations={"classpath:spring/spring-tx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentDaoTest {

	@Resource
	private StudentDao studentDao;

	@Test
	public void testGet() {
		Student s = studentDao.get("1").get(0);
		System.out.println(s);
	}

}

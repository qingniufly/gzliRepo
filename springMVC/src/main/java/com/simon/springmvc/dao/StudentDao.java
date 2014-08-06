package com.simon.springmvc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simon.springmvc.bean.Student;
import com.simon.springmvc.mapper.StudentMapper;

/**
 * @Author  : simon
 * @version : Aug 1, 2014 12:36:05 PM
 *
 **/
@Repository("studentDaoTarget")
public class StudentDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<Student> get(String id) {
		String sql = "select * from Student where id = " + id;
		List<Student> result = jdbcTemplate.query(sql, new StudentMapper());
		return result;
	}

}

package com.simon.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.simon.springmvc.bean.Student;

/**
 * @Author  : simon
 * @version : Aug 1, 2014 12:29:46 PM
 *
 **/
public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student s = new Student();
		s.setId(rs.getString("id"));
		s.setName(rs.getString("name"));
		s.setGender(rs.getString("gender"));
		return s;
	}

}

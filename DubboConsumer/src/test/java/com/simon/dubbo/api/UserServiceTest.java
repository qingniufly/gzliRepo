package com.simon.dubbo.api;

import javax.annotation.Resource;

import org.junit.Test;

import com.simon.dubbo.bean.User;

public class UserServiceTest extends BaseSpringUnitTest {

	@Resource
	private IUserService userService;
	
	@Test
	public void testGet() {
		User user = new User("simon");
//		user.setAge(27);
//		user.setId(111l);
		user.setName("simon");
		user.setBirthDate("1987-10-19");
		userService.register(user);
		
	}
	
}

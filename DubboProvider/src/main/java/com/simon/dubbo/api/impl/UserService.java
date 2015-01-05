package com.simon.dubbo.api.impl;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.simon.dubbo.api.IUserService;
import com.simon.dubbo.bean.User;

@Service("userService")
public class UserService implements IUserService {

	private AtomicLong idNum = new AtomicLong();
	
	@Override
	public void register(User user) {
		System.out.printf("Saving the user %s ...\nFinished!\n", user);
		System.out.println(idNum.incrementAndGet());
//		return idNum.incrementAndGet();
	}

	@Override
	public User getUser(Long id, HttpServletRequest request) {
		System.out.printf("Get user[%s] ", String.valueOf(id));
		User u = new User("test-" + id);
		u.setId(id);
		return u;
	}

}

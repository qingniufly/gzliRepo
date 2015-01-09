package com.simon.dubbo.consumer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.simon.dubbo.api.IUserService;
import com.simon.dubbo.bean.User;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Reference
	private IUserService userService;
	
	@RequestMapping("registerUser")
	public String register(Model model) {
		User user = new User("simon");
//		user.setAge(27);
//		user.setId(111l);
		user.setName("simon");
		user.setBirthDate("1987-10-19");
		userService.register(user);
		model.addAttribute("user", user);
		return "/page/user";
	}
	

}

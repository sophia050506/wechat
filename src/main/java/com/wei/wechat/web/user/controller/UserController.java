package com.wei.wechat.web.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wei.wechat.core.bean.User;
import com.wei.wechat.web.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	UserService userService;
	
	@RequestMapping("/test")
	public String showUser(Model model){
		User user = userService.getUserById(1);
		model.addAttribute("user", user);
		model.addAttribute("username", user.getUserName());
		return "/user/userTest";
		//return "/userTest";
		
	}
}

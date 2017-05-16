package com.wei.wechat.web.user.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wei.wechat.core.bean.User;
import com.wei.wechat.core.mapping.UserMapper;

@Service
public class UserService {
	
	@Resource
	UserMapper userMapper;
	
	public User getUserById(int id){
		return userMapper.findById(id);
	}

}

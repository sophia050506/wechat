package com.wei.wechat.core.mapping;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wei.wechat.core.bean.User;

public interface UserMapper {

	 void save(User user);  
     boolean update(User user);  
     boolean delete(int id);  
     User findById(int id);  
     List<User> findAll(); 
}

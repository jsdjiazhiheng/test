package com.mvc.service;

import com.mvc.model.User;

import core.service.BaseService;

public interface UserService extends BaseService<User> {
	
	public User login(String userName, String passWord);
	
	public Integer saveUser(User user);
	
	public Integer removeUsers(User user);
}

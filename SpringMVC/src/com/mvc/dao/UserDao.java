package com.mvc.dao;

import com.mvc.model.User;

import core.dao.BaseDao;

public interface UserDao extends BaseDao<User>{
	
	public User loadUserByUserName(String userName);
	
	public User loadUserByUserCode(Integer userCode);
}

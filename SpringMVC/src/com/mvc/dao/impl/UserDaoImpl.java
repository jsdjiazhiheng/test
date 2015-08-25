package com.mvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.mvc.dao.UserDao;
import com.mvc.model.User;

import core.dao.impl.BaseDaoImpl;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	public User loadUserByUserName(String userName) {
		String hql = "from User where userName = ?";
		return (User) getCurrentSession()
					.createQuery(hql)
					.setParameter(0, userName)
					.uniqueResult();
	}
	
	public User loadUserByUserCode(Integer userCode) {
		String hql = "from User where userCode = ?";
		return (User) getCurrentSession()
					.createQuery(hql)
					.setParameter(0, userCode)
					.uniqueResult();
	}
}

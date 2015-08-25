package com.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mvc.dao.UserDao;
import com.mvc.model.User;
import com.mvc.service.UserService;

import core.exceptions.LogicalException;
import core.service.impl.BaseServiceImpl;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public User login(String userName, String passWord) {
		Assert.hasText(userName, "用户名不能为空");
		Assert.hasText(passWord, "密码不能为空");
		User user = userDao.loadUserByUserName(userName);
		if(null == user){
			throw new LogicalException("用户名错误，请查证后登录！");
		}
		if(user.getStatus() == User.USER_STATUS_1){
			throw new LogicalException("该用户已被禁用，请联系管理员启用！");
		}
		if(null!=user.getPassword()&&!"".equals(user.getPassword())){
			if(!passWord.equals(user.getPassword())){
				throw new LogicalException("密码错误，请查证后登录！");
			}
		}else{
			if(!passWord.equals(user.getInitPass())){
				throw new LogicalException("密码错误，请查证后登录！");
			}
		}
		return user;
	}
	
	public Integer saveUser(User user) {
		//验证
		validate(user);
		User u = userDao.loadUserByUserName(user.getUserName());
		if(u!=null){
			throw new LogicalException("用户名不能重复！");
		}
		u = userDao.loadUserByUserCode(user.getUserCode());
		if(u!=null){
			throw new LogicalException("用户编码必须唯一！");
		}
		return userDao.saveEntity(user);
	}
	
	private void validate(User user){
		Assert.notNull(user.getUserName(), "用户名不能为空");
		Assert.notNull(user.getInitPass(), "初始化密码不能为空");
		Assert.notNull(user.getUserCode(), "用户编码不能为空");
		Assert.notNull(user.getStatus(), "状态不能为空");
	}
	
	public void setUserDao(UserDao baseDao) {
		super.setBaseDao(baseDao);
	}
	
	public UserDao getUserDao() {
		return (UserDao)super.getBaseDao();
	}

	public Integer removeUsers(User user) {
		Assert.notNull(user.getILC(),"请选择一条记录进行删除");
		try{
			String ids = user.getILC().toString().replace("[", "").replace("]", "");
			String hql = "delete from User where 1=1 and sUuid in ("+ids+")";
			getBaseDao().romoveEntityByHql(hql);
			return 1;
		}catch (Exception e) {
			return 0;
		}
	}
	
}

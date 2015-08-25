package com.mvc.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.model.User;
import com.mvc.service.UserService;

import core.controller.BaseController;
import core.util.Json;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	
	@RequestMapping(params="login.html",method=RequestMethod.POST)
	@ResponseBody
	public Json login(String userName, String passWord,HttpSession session) {
		User user = userService.login(userName, passWord);
		if(null != user){
			session.setAttribute(SESSION_USER, user);
			System.err.println("登录成功" + line + separator);
			json.setSuccess(true);
			json.setMessage("登录成功");
		}else{
			json.setMessage("登录失败");
		}
		return json;
	}
	
	@RequestMapping(params="user-main.html")
	public String listUser(){
		return "user/user-main";
	}
	
	@RequestMapping(params="userData")
	@ResponseBody
	public Map<String, Object> listUser(Model model){
		List<User> list = userService.listEntity();
		
		map.put("records", list);
		
		System.err.println("totalCount : "+ list.size() +", records :" + gson.toJson(list));
		
		return map;
	}
	
	@RequestMapping(params="method=saveUser",method=RequestMethod.POST)
	@ResponseBody
	public Json saveUser(@Validated User user,BindingResult br,Integer status,Integer isdefult) {
		user.setCreateTime(dateFormatter.format(new Date()));
		if(br.hasErrors()){
			List<ObjectError> listError = br.getAllErrors();
			String message = listError.get(0).getDefaultMessage();
			json.setMessage(message);
			return json;
		}
		if(userService.saveEntity(user)>0){
			json.setMessage("保存成功");
			json.setSuccess(true);
		}else{
			json.setMessage("保存失败");
		}
		return json;
	}
	
	@RequestMapping(params="method=updateUser")
	@ResponseBody
	public Json updateUser(User user) {
		userService.updateEntity(user);
		json.setMessage("修改成功");
		json.setSuccess(true);
		return json;
	}
	
	@RequestMapping(params="method=removeUser")
	@ResponseBody
	public Json removeUser(User user) {
		if(userService.removeUsers(user)>0){
			json.setMessage("删除成功");
			json.setSuccess(true);
		}else{
			json.setMessage("删除失败");
		}
		return json;
	}
}

package com.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.model.User;

import core.controller.BaseController;

@Controller
@RequestMapping("/mapping")
public class MappingController extends BaseController{
	
	private static final long serialVersionUID = 1L;
	
	@RequestMapping("/main.html")
	public String redirectToMain(HttpSession session){
		if(session.getAttribute(SESSION_USER) == null){
			return "redirect:/base/index";
		}
		User user = (User)session.getAttribute(SESSION_USER);
		System.err.println("登录用户" + line + user.toString());
		return "main";
	}
	
}

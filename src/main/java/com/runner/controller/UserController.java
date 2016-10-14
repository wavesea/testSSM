package com.runner.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runner.pojo.User;
import com.runner.service.IUserService;

/**
 * 用户工具类
 * 
 * @author LiHaibo
 * 
 * @fileName UserController.java
 * @date 2016-10-14 上午8:49:31
 * @Version 1.0
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUserById(userId);
		if (user == null) {
			return null;
		} else {
			model.addAttribute("user", user);
			return "showEmpety";
		}
	}

}

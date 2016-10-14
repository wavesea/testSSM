package com.runner.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runner.pojo.User;
import com.runner.service.IUserService;

/**
 * 图书控制类
 * 
 * @author LiHaibo
 * 
 * @fileName BookController.java
 * @date 2016-10-14 上午8:49:50
 * @Version 1.0
 * 
 */
@Controller
@RequestMapping("/book")
public class BookController {
	@Resource
	private IUserService userService;

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request, Model model) {
		String userId = request.getParameter("id");
		if ("".equals(userId) || userId == null) {
			List<User> userList = userService.getUser();
			model.addAttribute("userList", userList);
			return "showUser";
			// return "test/showUser";
		} else {
			List<User> user = userService.getListUser(Integer.parseInt(userId));
			model.addAttribute("userList", user);
			return "showUser";
			// return "test/showUser";
		}
	}

}

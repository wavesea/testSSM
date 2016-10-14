package com.runner.service;

import java.util.List;

import com.runner.pojo.User;

/**
 * service接口层
 * 
 * @author LiHaibo
 * 
 * @fileName IUserService.java
 * @date 2016-10-14 上午8:52:03
 * @Version 1.0
 * 
 */
public interface IUserService {
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId);

	/**
	 * @param user
	 */
	public void saveUser(User user);

	/**
	 * @return
	 */
	public List<User> getUser();

	/**
	 * @param userId
	 * @return
	 */
	public List<User> getListUser(int userId);
}

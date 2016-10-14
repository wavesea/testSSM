package com.runner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runner.dao.IUserDao;
import com.runner.pojo.User;
import com.runner.service.IUserService;

/**
 * service实现层
 * 
 * @author LiHaibo
 * 
 * @fileName UserServiceImpl.java
 * @date 2016-10-14 上午8:52:24
 * @Version 1.0
 * 
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userDao.selectById(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runner.service.IUserService#saveUser(com.runner.pojo.User)
	 */
	@Override
	public void saveUser(User user) {

		userDao.insert(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runner.service.IUserService#getUser()
	 */
	@Override
	public List<User> getUser() {
		return userDao.getUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runner.service.IUserService#getListUser(int)
	 */
	@Override
	public List<User> getListUser(int userId) {
		// TODO Auto-generated method stub
		return userDao.getListUser(userId);
	}

}

package com.runner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.runner.pojo.User;

/**
 * dao接口实现层
 * 
 * @author LiHaibo
 * 
 * @fileName IUserDao.java
 * @date 2016-10-14 上午8:50:11
 * @Version 1.0
 * 
 */
public interface IUserDao {

	int deleteByPrimaryKey(@Param("id") Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectById(@Param("id") Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	/**
	 * @return
	 */
	List<User> getUser();

	/**
	 * @param userId
	 * @return
	 */
	List<User> getListUser(int userId);
}
package com.runner.testmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.runner.dao.IUserDao;
import com.runner.pojo.User;
import com.runner.service.IUserService;

/**
 * junit测试
 * 
 * @author LiHaibo
 * 
 * @fileName TestMyBatis.java
 * @date 2016-10-14 上午8:54:51
 * @Version 1.0
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	// private ApplicationContext ac = null;
	@Resource
	private IUserService userService;

	@Autowired
	private IUserDao userDao;

	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }

	@Test
	public void test1() {
		User user = userDao.selectById(1);
		if (user == null) {
			System.out.println("数据库没有此数据！");
		} else {
			System.out.println(user.getUserName());
			logger.info("值：" + user.getUserName());
			logger.info(JSON.toJSONString(user));
		}

		// User user = new User(2, "AAAAAA", "bbbbb", 30);
		// userService.saveUser(user);
		// logger.info("插入数据成功！");

	}
}

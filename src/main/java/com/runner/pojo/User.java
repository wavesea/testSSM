package com.runner.pojo;

/**
 * 实体类对象
 * 
 * @author LiHaibo
 * 
 * @fileName User.java
 * @date 2016-10-14 上午8:51:43
 * @Version 1.0
 * 
 */
public class User {
	private Integer id;

	private String userName;

	private String password;

	private Integer age;

	/**
	 * @param id
	 * @param userName
	 * @param password
	 * @param age
	 */
	public User() {
	}

	public User(Integer id, String userName, String password, Integer age) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
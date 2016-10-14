/**
 * @author LiHaibo
 * @project testMybatis
 * @package com.runner.testmybatis
 * @name MOItem.java 
 * @date 2016-10-13 上午9:33:59
 * @Version 1.0
 *
 */
package com.runner.testmybatis;

/**
 * MOitem实现类
 * 
 * @author LiHaibo
 * 
 * @fileName MOItem.java
 * @args
 * 
 */
public class MOItem {

	private Long smID;
	private String content;
	private String mobile;
	private String moTime;

	/**
	 * @return the smID
	 */
	public Long getSmID() {
		return smID;
	}

	/**
	 * @param smID
	 *            the smID to set
	 */
	public void setSmID(Long smID) {
		this.smID = smID;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the moTime
	 */
	public String getMoTime() {
		return moTime;
	}

	/**
	 * @param moTime
	 *            the moTime to set
	 */
	public void setMoTime(String moTime) {
		this.moTime = moTime;
	}

}

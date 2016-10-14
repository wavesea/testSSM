/**
 * @author LiHaibo
 * @project testMybatis
 * @package com.runner.testmybatis
 * @name RPTItem.java 
 * @date 2016-10-13 上午9:38:03
 * @Version 1.0
 *
 */
package com.runner.testmybatis;

/**
 * RPTItem 实现类
 * 
 * @author LiHaibo
 * 
 * @fileName RPTItem.java
 * @args
 * 
 */
public class RPTItem {

	private Long smID;
	private Integer code;
	private String mobile;
	private String desc;
	private String rptTime;

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
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the rptTime
	 */
	public String getRptTime() {
		return rptTime;
	}

	/**
	 * @param rptTime
	 *            the rptTime to set
	 */
	public void setRptTime(String rptTime) {
		this.rptTime = rptTime;
	}

}

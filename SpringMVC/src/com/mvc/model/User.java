package com.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import core.model.BaseEntity;


/**
 * 用户
 * @author jiazh
 *
 */

@Entity
@Table(name="user")
public class User extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;//用户名
	private String initPass; //账户初始化密码
	private Integer initStatus;//是否初始化账户
	private String password;//密码
	private Integer userCode;//用户编号
	private String createTime;//创建时间
	private Integer status;//状态 1禁用,2启用
	
	@Column(unique = true)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(unique = true)
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "status", columnDefinition = "bigint default 1")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		if(status == null){
			this.status = 1;
		}else{
			this.status = status;
		}
	}
	public String getInitPass() {
		return initPass;
	}
	public void setInitPass(String initPass) {
		this.initPass = initPass;
	}
	@Column(name = "initStatus", columnDefinition = "bigint default 2")
	public Integer getInitStatus() {
		return initStatus;
	}
	public void setInitStatus(Integer initStatus) {
		if(initStatus == null){
			this.initStatus = 2;
		}else{
			this.initStatus = initStatus;
		}
	}
	
	/**
	 * 已禁用
	 */
	public static Integer USER_STATUS_1 = 1;
	/**
	 * 已启用
	 */
	public static Integer USER_STATUS_2 = 2;

	/**
	 * 初始化账户
	 */
	public static Integer USER_INITSTATUS_1 = 1;
	
	/**
	 * 不初始化账户
	 */
	public static Integer USER_INITSTATUS_2 = 2;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("用户名 : " + userName + line);
		sb.append("初始化密码 : " + initPass + line);
		sb.append("用户编号 : " + userCode + line);
		sb.append("创建时间 : " + createTime + line);
		if(status == USER_STATUS_1){
			sb.append("状态 : " + "已禁用" + line);
		}else{
			sb.append("状态 : " + "已启用" + line);
		}
		if(initStatus == USER_INITSTATUS_1){
			sb.append("是否初始化账户 : " + "初始化账户" + line);
		}else{
			sb.append("是否初始化账户 : " + "不初始化账户" + line);
		}
		sb.append("---------------------------------");
		return sb.toString();
	}
}

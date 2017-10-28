package com.lakala.test.jmeter.cheat.model;



import java.io.Serializable;
import java.util.Date;

public class CollectData implements Serializable{
	private static final long serialVersionUID = 1L;
	//登录名
	private String loginName;	
	//时间戳
	private String readTime;
	//数据
	private String mode;
	//密钥
	private String friends;	
	//数据类型 
	private String type;	
	//平台
	private String platform;
	//版本
	private String appVersion;
	//OS版本
	private String osVersion;	
	//客户端渠道
	private String subChannelID;
	//设备型号
	private String deviceModel;	
	//设备ID
	private String deviceId;
	//服务收到时间
	private Date collectTime;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getReadTime() {
		return readTime;
	}
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFriends() {
		return friends;
	}
	public void setFriends(String friends) {
		this.friends = friends;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getSubChannelID() {
		return subChannelID;
	}
	public void setSubChannelID(String subChannelID) {
		this.subChannelID = subChannelID;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

}

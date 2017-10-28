package com.lakala.cheatservice.models;

import java.io.Serializable;
import java.util.Date;

public class CollectData implements Serializable{
	private static final long serialVersionUID = 1L;
	//��¼��
	private String loginName;	
	//ʱ���
	private String readTime;
	//����
	private String mode;
	//��Կ
	private String friends;	
	//�������� 
	private String type;	
	//ƽ̨
	private String platform;
	//�汾
	private String appVersion;
	//OS�汾
	private String osVersion;	
	//�ͻ�������
	private String subChannelID;
	//�豸�ͺ�
	private String deviceModel;	
	//�豸ID
	private String deviceId;
	//�����յ�ʱ��
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

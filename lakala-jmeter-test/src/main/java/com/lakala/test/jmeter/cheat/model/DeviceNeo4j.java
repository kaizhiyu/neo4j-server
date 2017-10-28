package com.lakala.test.jmeter.cheat.model;

import java.io.Serializable;

public class DeviceNeo4j implements Serializable {
	
	private String id;
	//黑明单类型
	private String type;
	//设备组
	private String group;
	//设备id
	private String deviceId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	

}

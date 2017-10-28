package com.lakala.neo4j.importdata.models;


import java.io.Serializable;


public class IMEIDeviceE  implements Serializable{
	
	public IMEIDeviceE(){
		
	}
	
	public IMEIDeviceE(String phone,String deviceid){
		this.source=phone;
		this.target=deviceid;
	}
	

	//对应的手机号码
	private String source;
	
	//对应的设备号
	private String target;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	
}

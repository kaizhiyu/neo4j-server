package com.lakala.test.jmeter.cheat.model;

import java.io.Serializable;


public class MobileV extends BaseModel implements Serializable {
	
	public MobileV(){
		
	}
	
	public MobileV(String id,String mobile,String group,String type,String modeltype){
		super.setId(id);
		this.mobile=mobile;
		this.type=type;
		this.modeltype=modeltype;
		super.setGroup(group);
	}

	//对应的手机号码
	private String mobile;

	//黑名单类型
	private String type;
	
	//通讯录还是通话记录,通讯录通话记录进件用逗号隔开
	private String modeltype;


	
	
	public String getModeltype() {
		return modeltype;
	}

	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

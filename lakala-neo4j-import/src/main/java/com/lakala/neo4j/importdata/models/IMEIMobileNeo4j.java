package com.lakala.neo4j.importdata.models;

import java.io.Serializable;
public class IMEIMobileNeo4j  implements Serializable {

	//实体id
	private String id;
	//实体类型
	private String group;
	
	//手机号实体对应的实体手机号码
	private String mobile;
	
	//通话记录还是通讯录两种都存在用逗号隔开
	private String modeltype;
	

	//如果group为0的时候type是存在的
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getModeltype() {
		return modeltype;
	}

	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}

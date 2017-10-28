package com.lakala.neo4j.importdata.models;

import java.io.Serializable;

public class IMEIModelNeo4j implements Serializable {

	
	private String id;
	
	private String modelname;
	//实体组
	private String group;
	//存储查询内容
	private String content;
	//存储查询内容
	private String type="";
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
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
		if(this.type.equals("1")){
			this.group = "0";
		}else {
			this.group=group;
		}
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

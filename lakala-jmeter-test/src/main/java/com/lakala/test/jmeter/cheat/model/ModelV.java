package com.lakala.test.jmeter.cheat.model;

import java.io.Serializable;

public class ModelV extends BaseModel implements Serializable {
	public ModelV(){
		super();
	}
	
	public ModelV(String id,String modelname,String content,String group){
		super.setId(id);
		this.modelname=modelname;
		this.content=content;
		super.setGroup(group);
	}
	private String modelname;
	
	//存储的查询字段
	private String content;


	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}

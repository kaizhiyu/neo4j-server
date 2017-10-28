package com.lakala.neo4j.importdata.models;

import java.io.Serializable;


public class IMEIModelV implements Serializable {
	public IMEIModelV(){
		
	}
	
	public IMEIModelV(String id,String modelname,String content,String group){
		
		this.modelname=modelname;
		this.content=content;
		this.name=content;
		this.category=modelname;
	}
	private String modelname;
	
	//存储的查询字段
	private String content;
	
	private String name;
	
	private String type;
	
	private int symbolSize=20;
	
	private String category;
	
	private String value;
	
	private String draggable="true";
	/**
	 * "symbolSize": 40,
              "category": "2010",
              "draggable": "true",
              "value": 1
	 * @return
	 */



	public int getSymbolSize() {
		return symbolSize;
	}
	
	public void SetSymbolSize(int symbolSize) {
		this.symbolSize = symbolSize;
	}

	public String getDraggable() {
		return draggable;
	}

	public String getCategory() {
		return category;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
		this.category=modelname;

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.name=content;
		this.symbolSize=20;
		this.draggable="true";
	}

	
}

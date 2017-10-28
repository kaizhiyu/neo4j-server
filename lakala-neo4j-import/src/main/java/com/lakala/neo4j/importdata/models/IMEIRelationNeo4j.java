package com.lakala.neo4j.importdata.models;


import java.io.Serializable;

public class IMEIRelationNeo4j   implements Serializable {
	public IMEIRelationNeo4j(){}
	public IMEIRelationNeo4j(String id,String startId,String endId,String type){
		this.id=id;
		this.startId=startId;
		this.endId=endId;
		this.type=type;
		
	}
	//neo4jid
	private String id;
	//关系类型
	private String type;
	//开始id
	private String startId;
	//结束id
	private String endId;
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
	public String getStartId() {
		return startId;
	}
	public void setStartId(String startId) {
		this.startId = startId;
	}
	public String getEndId() {
		return endId;
	}
	public void setEndId(String endId) {
		this.endId = endId;
	}
	
	
	

}

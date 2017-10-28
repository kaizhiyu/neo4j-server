package com.lakala.neo4j.importdata.models;



import java.io.Serializable;

public class IMEIRelationE implements Serializable{
	
	public IMEIRelationE(String id,String startId,String endId,String relationType){
		this.id=id;
		this.source=startId;
		this.target=endId;
		this.relationType=relationType;
	}
	
	private String id;
	//开始id
	private String source;
	//结束id
	private String target;
	//关系类型
	private String relationType;
	
	private int value=300;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}


}

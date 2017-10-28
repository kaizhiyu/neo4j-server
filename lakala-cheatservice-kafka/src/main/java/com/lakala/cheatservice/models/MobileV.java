package com.lakala.cheatservice.models;


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

	//��Ӧ���ֻ�����
	private String mobile;

	//����������
	private String type;
	
	//ͨѶ¼����ͨ����¼,ͨѶ¼ͨ����¼�����ö��Ÿ���
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

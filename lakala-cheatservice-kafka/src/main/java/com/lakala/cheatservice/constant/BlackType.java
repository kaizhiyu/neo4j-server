package com.lakala.cheatservice.constant;

public enum BlackType {
	
	/**IP��ַ*/
	BLACK("Black","1"),

	/**IP��ַ*/
	GRAY("Gray","2"),
	/**IP��ַ*/
	WHITE("White","3");
	
	private BlackType(String modelName, String entityGroup) {
		this.modelName = modelName;
		this.entityGroup = entityGroup;
	}
	
	/**
	 * ʵ������
	 */
	private String modelName;

	/**
	 * ʵ�����չʾ
	 */
	private String entityGroup;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getEntityGroup() {
		return entityGroup;
	}

	public void setEntityGroup(String entityGroup) {
		this.entityGroup = entityGroup;
	}
	
	


}

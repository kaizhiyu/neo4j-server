package com.lakala.cheatservice.constant;

public enum BlackType {
	
	/**IP地址*/
	BLACK("Black","1"),

	/**IP地址*/
	GRAY("Gray","2"),
	/**IP地址*/
	WHITE("White","3");
	
	private BlackType(String modelName, String entityGroup) {
		this.modelName = modelName;
		this.entityGroup = entityGroup;
	}
	
	/**
	 * 实体名称
	 */
	private String modelName;

	/**
	 * 实体分组展示
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

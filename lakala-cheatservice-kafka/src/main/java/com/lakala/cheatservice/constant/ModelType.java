package com.lakala.cheatservice.constant;

public enum ModelType {

	/**进件信息实体*/
	APPLYINFO("ApplyInfo","1"),
	/**手机号实体*/
	MOBILE("Mobile","2"),
	/**设备指纹实体*/
	DEVICE("Device","3"),
	/**银行卡实体*/
	BANKCARD("BankCard","4"),
	/**身份证实体*/
	IDENTIFICATION("Identification","5"),
	/**邮件实体*/
	EMAIL("Email","6"),
	/**终端*/
	TERMINAL("Terminal","7"),
	/**宿舍地址实体*/
	HOUSEADDRESS("HouseAddress","8"),
	/**手机IMEI实体*/
	MOBILEIMEI("MobileIMEI","9"),
	/**LBS实体*/
	LBS("LBS","10"),
	/**单位名称*/
	COMPANY("Company","11"),
	/**固定电话*/
	FIXEDPHONE("FixedPhone","12"),
	/**公司地址实体*/
	COMPANYADDRESS("CompanyAddress","13"),
	/**IP地址*/
	IPV4("IPV4","14"),
	/**公司电话*/
	COMPANYTEL("CompanyTel","15");

	
	//名单内容类型，1-手机号；2-银行卡；3-身份证；4-设备指纹；5-邮箱；6-终端号；
	//7-住址；8-手机IMEI；9-LBS；10-单名；11-固话；12-单址
	
	private ModelType(String modelName, String entityGroup) {
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

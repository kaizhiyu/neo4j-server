/*******************************************************************************
 * Project Key : CONTACTLIST 
 * Create on 2015年10月22日 下午2:27:18
 * Copyright (c) 2004 - 2015. 拉卡拉支付有限公司版权所有. 京ICP备12007612号
 * 注意：本内容仅限于拉卡拉支付有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.lakala.test.jmeter.cheat.constant;

/**
 * 
 * <P>枚举</P>
 * @author tianhuaxing 2016年12月22日 下午5:39:32
 * @since 1.0.0.000
 */
public enum DataTypeEnum {
	LOANMOBILE_LIST("loanapply", "neo4j_mobile_relation", "loanapply"),
	CONTACT_LIST("contactlist", "neo4j_mobile_relation", "contact_"), 
	CALL_HISTORY("callhistory","neo4j_mobile_relation","call_"),
	APPLY_INFO("applyinfo","neo4j_applyinfo_relation","applyinfo_"),
	APPLYINFO_ORDERNO("applyinfo_orderno","neo4j_applyinfo_relation","orderno_"),
	BLACKINFO("blacklist","neo4j_blacklist","");

	/**
	 * 数据类型
	 */
	private String dataType;

	/**
	 * mongodb集合名
	 */
	private String collectName;
	
	/**
	 * 数据类型名
	 */
	private String columnName;
	
	private DataTypeEnum(String dataType, String collectName, String columnName) {
		this.dataType = dataType;
		this.collectName = collectName;
		this.columnName= columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public String getCollectName() {
		return collectName;
	}
	

	public String getColumnName() {
		return columnName;
	}

	public static DataTypeEnum getDataType(String dataType) {		
		for (int i=0;i<DataTypeEnum.values().length;i++ ) {
			if (DataTypeEnum.values()[i].getDataType().equals(dataType)) {
				return DataTypeEnum.values()[i];
			}
		}
		return null;
	}
	
	public boolean equals(String dataType) {
		return this.getDataType().equals(dataType);
	}
}

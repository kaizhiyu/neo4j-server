package com.lakala.cheatservice.constant;

public enum RelationType {
	/**自己的手机号*/
	LOANCONTACT("进件关联","loancontact"),//只是关联进件手机号与手机号之间的关系
	/**进件自己的手机号码*/
	APPLYMYMOBILE("自己手机","applymymobile"),//进件自己的手机号码实体
	/**进件推荐人的手机号码*/
	RECOMMEND("推荐人","recommend"),//进件推荐人的手机号码实体
	/**自己的手机号*/
	LOANAPPLY("联系人","loanapply"),//进件与关联人的关系
	/**亲属联系人*/
	RELATIVEMOBILE("亲属联系人","relativemobile"),//亲属联系人
	/**紧急联系人*/
	EMERGENCYMOBILE("紧急联系人","emergencymobile"),//订单中的紧急联系人
	/**家庭电话*/
	HOMETEL("家庭电话","hometel"),//订单中的家庭电话
	/**登录手机号*/
	LOGINMOBILE("登录手机号","loginmobile"),//订单中用户登录手机号
	/**合作渠道手机号*/
	CHANNELMOBILE("合作渠道手机号","channelmobile"),//订单中的合作渠道手机号
	/**合作合作商户号码*/
	MERCHANTMOBILE("合作商户号","merchantmobile"),//订单中的合作商户号码
	/**设备指纹*/
	DEVICE("设备","device"),
	/**银行卡*/
	BANKCARD("银行卡","bankcard"),
	/**银行卡*/
	CREDITCARD("信用卡","creditcard"),
	/**身份证*/
	IDENTIFICATION("身份证","identification"),
	/**邮件*/
	EMAIL("邮箱","email"),
	/**终端*/
	TERMINAL("终端","terminal"),
	/**IMEI*/
	MOBILEIMEI("IMEI","mobileimei"),
	/**LBS*/
	LBS("LBS","LBS"),
	/**单位名称*/
	COMPANY("公司","company"),
	/**大单位*/
	MAJORCOMPANY("公司","majorcompany"),
	/**IP地址*/
	IPV4("IPV4","ipv4"),
	/**公司地址*/
	COMPANYADDRESS("公司地址","companyaddress"),
	/**公司电话*/
	COMPANYTEL("公司电话","companytel"),
	/**通讯录*/
	CONTACT("通讯录","contact"),
	/**通话记录*/
	CALL("通话记录","call");
	
	private RelationType(String relationName, String relationType) {
		this.relationName = relationName;
		this.relationType = relationType;
	}
	
	private String relationName;
	
	private String relationType;

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
}

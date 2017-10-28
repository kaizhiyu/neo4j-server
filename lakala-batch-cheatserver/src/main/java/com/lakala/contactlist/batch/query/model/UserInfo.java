package com.lakala.contactlist.batch.query.model;

/**
 * 要查询的用户的基本信息
 * <P>对功能点的描述</P>
 * @author root 2016年8月3日 下午6:46:35
 * @since 1.0.0.000
 */
public class UserInfo {
    private String name;
    private String mobile;
    private String yys;
    private String idCard;
    private String idType = "idCard";
    
    private String idNoCheckResultDesc;
    private String idNoCheckResultCode;
    private String idTypeCheckResultDesc;
    private String idTypeCheckResultCode;
    private String nameCheckResultDesc;
    private String nameCheckResultCode;
    private String goinNetTimeLengthDesc;
    private String goinNetTimeLengthLevel;
    
    
    
	public UserInfo() {
	}

	public UserInfo(String name, String mobile, String yys, String idCard) {
		this.name = name;
		this.mobile = mobile;
		this.yys = yys;
		this.idCard = idCard;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getYys() {
		return yys;
	}
	public void setYys(String yys) {
		this.yys = yys;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNoCheckResultDesc() {
		return idNoCheckResultDesc;
	}

	public void setIdNoCheckResultDesc(String idNoCheckResultDesc) {
		this.idNoCheckResultDesc = idNoCheckResultDesc;
	}

	public String getIdNoCheckResultCode() {
		return idNoCheckResultCode;
	}

	public void setIdNoCheckResultCode(String idNoCheckResultCode) {
		this.idNoCheckResultCode = idNoCheckResultCode;
	}

	public String getIdTypeCheckResultDesc() {
		return idTypeCheckResultDesc;
	}

	public void setIdTypeCheckResultDesc(String idTypeCheckResultDesc) {
		this.idTypeCheckResultDesc = idTypeCheckResultDesc;
	}

	public String getIdTypeCheckResultCode() {
		return idTypeCheckResultCode;
	}

	public void setIdTypeCheckResultCode(String idTypeCheckResultCode) {
		this.idTypeCheckResultCode = idTypeCheckResultCode;
	}

	public String getNameCheckResultDesc() {
		return nameCheckResultDesc;
	}

	public void setNameCheckResultDesc(String nameCheckResultDesc) {
		this.nameCheckResultDesc = nameCheckResultDesc;
	}

	public String getNameCheckResultCode() {
		return nameCheckResultCode;
	}

	public void setNameCheckResultCode(String nameCheckResultCode) {
		this.nameCheckResultCode = nameCheckResultCode;
	}

	public String getGoinNetTimeLengthDesc() {
		return goinNetTimeLengthDesc;
	}

	public void setGoinNetTimeLengthDesc(String goinNetTimeLengthDesc) {
		this.goinNetTimeLengthDesc = goinNetTimeLengthDesc;
	}

	public String getGoinNetTimeLengthLevel() {
		return goinNetTimeLengthLevel;
	}

	public void setGoinNetTimeLengthLevel(String goinNetTimeLengthLevel) {
		this.goinNetTimeLengthLevel = goinNetTimeLengthLevel;
	}
	
	
}

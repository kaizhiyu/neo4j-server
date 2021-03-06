package com.lakala.test.jmeter.cheat.model;

import java.io.Serializable;


public class RiskBlacklist implements Serializable{
    private Long id; //主键

    private Short type;//名单类型，1-黑名单；2-灰名单；3-白名单

    private Short contentType;//名单内容类型，1-手机号；2-银行卡；3-身份证；4-设备指纹；5-邮箱；6-终端号；7-住址；8-手机IMEI；9-LBS;10-单名；11-固话；12-单址

    private String content;//内容

    private String validTime;//有效时间

    private String listDesc;//名单描述

    private Short useRange;//使用范围，1-所有业务；2-信贷；3-理财；4-支付

    private String createrTime;//创建时间

    private String modifyTime;//更新时间

    private String creatorId;//创建人ID

    private String creatorName;//创建人姓名
    
    private String modifierId;//修改人ID

    private String modifierName;//修改人姓名

    private Short origin;//数据来源，0-人工创建；1-同盾；2-邦盛；3-信贷；4-理财；5-支付

    private String riskType;//风险类型：0-涉嫌中介-白中介、1-涉嫌中介-黑中介、2-提供虚假资料（个人行为）、3-提供虚假资料（个人行为）、4-已离职（个人行为）、5-盗用他人资料、6-资料被他人盗用、7-第三方要求加黑、8-伪造证件或照片，9-贷款用途不良，10-疑似中介代办
    
    private String applyId;//合同号或申请ID
    
    private Short  listType;//名单类型，0-个人；1-单位
    
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.ID
     *
     * @param id the value for rcs_blacklist.ID
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.TYPE
     *
     * @return the value of rcs_blacklist.TYPE
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public Short getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.TYPE
     *
     * @param type the value for rcs_blacklist.TYPE
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setType(Short type) {
        this.type = type;
    }
    
    
    public String getTypeValue() {
    	Short type = this.getType();
    	//1-黑名单；2-灰名单；3-白名单
    	if(type==1){
    		return "黑名单";
    	}else if(type ==2){
    		return "灰名单";
    	}else if(type == 3){
    		return "白名单";
    	}
    	return "";
    }
    
    public void setType(String type) {
    	//1-黑名单；2-灰名单；3-白名单
    	if("黑名单".equals(type)){
    		this.type = new Short("1");
    	}else if("灰名单".equals(type)){
    		this.type = new Short("2");
    	}else if("白名单".equals(type)){
    		this.type = new Short("3");
    	}
    }

    public Short getContentType() {
        return contentType;
    }

    public void setContentType(Short contentType) {
        this.contentType = contentType;
    }
    
    public String getContentTypeValue() {
    	Short val = this.getContentType();
    	if(val!=null){
    		 int valInt = val.intValue();
    		 if(valInt == 1){
    	    		return "手机号";
    	    	}else if(valInt == 2){
    	    		return "银行卡";
    	    	}else if(valInt == 3){
    	    		return "身份证";
    	    	}else if(valInt == 4){
    	    		return "设备指纹";
    	    	}
    	    	else if(valInt == 5){
    	    		return "邮箱";
    	    	}
    	    	else if(valInt == 6){
    	    		return "终端号";
    	    	}
    	    	else if(valInt == 7){
    	    		return "住址";
    	    	}
    	    	else if(valInt == 8){
    	    		return "手机IMEI";
    	    	}
    	    	else if(valInt == 9){
    	    		return "LBS";
    	    	}
    	    	else if(valInt == 10){
    	    		return "单名";
    	    	}
    	    	else if(valInt == 11){
    	    		return "固话";
    	    	}
    	    	else if (valInt == 12){
    	    		return "单址";
    	    	}
    	}
    	return "";
    }
    

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.CONTENT
     *
     * @return the value of rcs_blacklist.CONTENT
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.CONTENT
     *
     * @param content the value for rcs_blacklist.CONTENT
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.LIST_DESC
     *
     * @return the value of rcs_blacklist.LIST_DESC
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public String getListDesc() {
        return listDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.LIST_DESC
     *
     * @param listDesc the value for rcs_blacklist.LIST_DESC
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setListDesc(String listDesc) {
        this.listDesc = listDesc == null ? null : listDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.USE_RANGE
     *
     * @return the value of rcs_blacklist.USE_RANGE
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public Short getUseRange() {
        return useRange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.USE_RANGE
     *
     * @param useRange the value for rcs_blacklist.USE_RANGE
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setUseRange(Short useRange) {
        this.useRange = useRange;
    }
    
    public String getUseRangeValue(){
    	Short val = this.getUseRange();
    	if(val!=null){
    		int valInt = val.intValue();
    		if(valInt == 1){
        		return "所有业务";
        	}else if(valInt == 2){
        		return "信贷";
        	}else if(valInt == 3){
        		return "理财";
        	}else if(valInt == 4){
        		return "支付";
        	}
    	}
    	return "";
    }
    
    
    //使用范围，1-所有业务；2-信贷；3-理财；4-支付
    public void setUseRangeValue(String useRange) {
    	if("所有业务".equals(useRange)){
    		this.useRange = new Short("1");
    	}else if("信贷".equals(useRange)){
    		this.useRange = new Short("2");
    	}else if("理财".equals(useRange)){
    		this.useRange = new Short("3");
    	}else if("支付".equals(useRange)){
    		this.useRange = new Short("4");
    	}else{
    		this.useRange = null;
    	}
    }

   

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.CREATOR_ID
     *
     * @return the value of rcs_blacklist.CREATOR_ID
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.CREATOR_ID
     *
     * @param creatorId the value for rcs_blacklist.CREATOR_ID
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.CREATOR_NAME
     *
     * @return the value of rcs_blacklist.CREATOR_NAME
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.CREATOR_NAME
     *
     * @param creatorName the value for rcs_blacklist.CREATOR_NAME
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.MODIFIER_ID
     *
     * @return the value of rcs_blacklist.MODIFIER_ID
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public String getModifierId() {
        return modifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.MODIFIER_ID
     *
     * @param modifierId the value for rcs_blacklist.MODIFIER_ID
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setModifierId(String modifierId) {
        this.modifierId = modifierId == null ? null : modifierId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.MODIFIER_NAME
     *
     * @return the value of rcs_blacklist.MODIFIER_NAME
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.MODIFIER_NAME
     *
     * @param modifierName the value for rcs_blacklist.MODIFIER_NAME
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rcs_blacklist.ORIGIN
     *
     * @return the value of rcs_blacklist.ORIGIN
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public Short getOrigin() {
        return origin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rcs_blacklist.ORIGIN
     *
     * @param origin the value for rcs_blacklist.ORIGIN
     *
     * @mbggenerated Wed Sep 14 10:58:10 CST 2016
     */
    public void setOrigin(Short origin) {
        this.origin = origin;
    }
    
    //数据来源，0-人工创建；1-同盾；2-邦盛；3-信贷；4-理财；5-支付
    public void setOrigin(String origin) {
    	if("人工创建".equals(origin)){
    		this.origin = new Short("0");
    	}else if("同盾".equals(origin)){
    		this.origin = new Short("1");
    	}else if("邦盛".equals(origin)){
    		this.origin = new Short("2");
    	}else if("信贷".equals(origin)){
    		this.origin = new Short("3");
    	}else if("理财".equals(origin)){
    		this.origin = new Short("4");
    	}else if("支付".equals(origin)){
    		this.origin = new Short("5");
    	}
    }

    public String getOriginValue(){
    	Short val = this.getOrigin();
    	if(val!=null){
    		int valInt = val.intValue();
    		if(valInt == 0){
        		return "人工创建";
        	}else if(valInt == 1){
        		return "同盾";
        	}else if(valInt == 2){
        		return "邦盛";
        	}else if(valInt == 3){
        		return "信贷";
        	}else if(valInt == 4){
        		return "理财";
        	}else if(valInt == 5){
        		return "支付";
        	}else{
        		return "";
        	}
    	}
    	return "";
    }
    
    
    
    
    
	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(String createrTime) {
		this.createrTime = createrTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}



	public String getRiskType() {
		return riskType;
	}

	//风险类型：00-涉嫌中介-包装资料、01-涉嫌中介-未包装资料、02-要求加黑、03-伪造证件或照片、04-恶意拖欠、05-虚假单位资料、06-身份冒用、07-贷款用途不良、08-其他
	public void setRiskType(String riskType) {
		if("涉嫌中介-白中介".equals(riskType)){
			this.riskType = "0";
		}else if("涉嫌中介-黑中介".equals(riskType)){
			this.riskType = "1";
		}else if("恶意拖欠".equals(riskType)){
			this.riskType = "2";
		}else if("提供虚假资料（个人行为）".equals(riskType)){
			this.riskType = "3";
		}else if("已离职（个人行为）".equals(riskType)){
			this.riskType = "4";
		}else if("盗用他人资料".equals(riskType)){
			this.riskType = "5";
		}else if("资料被他人盗用".equals(riskType)){
			this.riskType = "6";
		}else if("第三方要求加黑".equals(riskType)){
			this.riskType = "7";
		}else if("伪造证件或照片".equals(riskType)){
			this.riskType = "8";
		}else if("贷款用途不良".equals(riskType)){
			this.riskType = "9";
		}else if("疑似中介代办".equals(riskType)){
			this.riskType = "10";
		}else{
			this.riskType = riskType;
		}
	}
	
	
	public String getRiskTypeValue(){
		String val = this.getRiskType();
		if("0".equals(val)){
    		return "涉嫌中介-白中介";
    	}else if("1".equals(val)){
    		return "涉嫌中介-黑中介";
    	}else if("2".equals(val)){
    		return "恶意拖欠";
    	}else if("3".equals(val)){
    		return "提供虚假资料（个人行为）";
    	}else if("4".equals(val)){
    		return "已离职（个人行为）";
    	}else if("5".equals(val)){
    		return "盗用他人资料";
    	}else if("6".equals(val)){
    		return "资料被他人盗用";
    	}else if("7".equals(val)){
    		return "第三方要求加黑";
    	}else if("8".equals(val)){
    		return "伪造证件或照片";
    	}else if("9".equals(val)){
    		return "贷款用途不良";
    	}else if("10".equals(val)){
    		return "疑似中介代办";
    	}else{
    		return "";
    	}
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public Short getListType() {
		return listType;
	}

	public void setListType(Short listType) {
		this.listType = listType;
	}
	
	public String getListTypeValue(){
		Short val = this.getListType();
		if(val!=null){
			int valInt = val.intValue();
			if(valInt == 0){
	    		return "个人";
	    	}else if(valInt == 1){
	    		return "单位";
	    	}
		}
		return "";
	}
	
	
	
	public void setContentType(String contentType) {
    	//名单内容类型，1-手机号；2-银行卡；3-身份证；4-IP；5-设备指纹
    	if("手机号".equals(contentType)){
    		this.contentType = new Short("1");
    	}else if("银行卡".equals(contentType)){
    		this.contentType = new Short("2");
    	}else if("身份证".equals(contentType)){
    		this.contentType = new Short("3");
    	}else if("设备指纹".equals(contentType)){
    		this.contentType = new Short("4");
    	}else if("邮箱".equals(contentType)){
    		this.contentType = new Short("5");
    	}else if("终端号".equals(contentType)){
    		this.contentType = new Short("6");
    	}else if("住址".equals(contentType)){
    		this.contentType = new Short("7");
    	}else if("手机IMEI".equals(contentType)){
    		this.contentType = new Short("8");
    	}else if("LBS".equals(contentType)){
    		this.contentType = new Short("9");
    	}else if("单名".equals(contentType)){
    		this.contentType = new Short("10");
    	}else if("固话".equals(contentType)){
    		this.contentType = new Short("11");
    	}else if("单址".equals(contentType)){
    		this.contentType = new Short("12");
    	}
    }

	
	//名单类型，0-个人；1-单位
	public void setListType(String listType) {
		if("个人".equals(listType)){
    		this.listType = new Short("0");
    	}else if("单位".equals(listType)){
    		this.listType = new Short("1");
    	}
	}
}
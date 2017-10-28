package com.lakala.neo4j.importdata.models;

import java.io.Serializable;




public class IMEIApplyInfoV implements Serializable {
	public IMEIApplyInfoV(){}
	public IMEIApplyInfoV(String username,String mobile,int group,String certno,String deviceId,
			String creditcard,String orderno,String userid,String applyid,String contactmobile,
			String email,String debitcart,String emergencymobile,String partneraccount,String modelname) {
		
		this.mobile = mobile;
		this.username = username;
		this.certno = certno;
		this.deviceid = deviceId;
		this.creditcard=creditcard;
		this.orderno=orderno;
		this.name=orderno;
		this.userid=userid;
		this.applyid=applyid;
		this.contactmobile=contactmobile;//联系人
		this.email=email;
		this.debitcart=debitcart;//借记卡
		this.emergencymobile=emergencymobile;
		this.partneraccount=partneraccount;
		this.category=modelname;
	}
	
	
	private int symbolSize=40;
	
	private String category="ApplyInfo";
	
	private int value=20;
	
	private String draggable;
	
	private String modelname="ApplyInfo";
	
	private String hometel;
	
	private String usercoremobile;

	private String channelmobile;
	
	private String partnercontantmobile;
	
	private String merchantmobile;
	
	private String debitcard;
	
	private String ipv4;
	
	private String lbs;
	
	private String companyname;
	
	private String companyaddress;
	
	private String companytel;
	
	
	private String deviceId;
	
	private String collectTime;
	
	private Long nodeId;
	
	
	private String mobile;
	
	
	private String username;
	
	
	private String orderno;
	
	private String name;
	
	
	private String email;
	
	
	private String debitcart;
	
	
	private String emergencymobile;
	
	
	private String contactmobile;
	
	
	private String applyid;
	
	
	private String userid;
	
	
	private String creditcard;
	
	
	private String certno;
	
	
	private String deviceid;
	
	
	private String partneraccount;
	
	

	
	
	public int getSymbolSize() {
		return symbolSize;
	}
	public void setSymbolSize(int symbolSize) {
		this.symbolSize = symbolSize;
//		this.symbolSize = 40;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = 10;
	}
	public String getCategory() {
		return category;
	}
	public String getDraggable() {
		return draggable;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
		this.category=modelname;
	}
	public String getHometel() {
		return hometel;
	}
	public void setHometel(String hometel) {
		this.hometel = hometel;
	}
	public String getUsercoremobile() {
		return usercoremobile;
	}
	public void setUsercoremobile(String usercoremobile) {
		this.usercoremobile = usercoremobile;
	}
	public String getChannelmobile() {
		return channelmobile;
	}
	public void setChannelmobile(String channelmobile) {
		this.channelmobile = channelmobile;
	}
	public String getPartnercontantmobile() {
		return partnercontantmobile;
	}
	public void setPartnercontantmobile(String partnercontantmobile) {
		this.partnercontantmobile = partnercontantmobile;
	}
	public String getMerchantmobile() {
		return merchantmobile;
	}
	public void setMerchantmobile(String merchantmobile) {
		this.merchantmobile = merchantmobile;
	}
	public String getDebitcard() {
		return debitcard;
	}
	public void setDebitcard(String debitcard) {
		this.debitcard = debitcard;
	}
	public String getIpv4() {
		return ipv4;
	}
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}
	public String getLbs() {
		return lbs;
	}
	public void setLbs(String lbs) {
		this.lbs = lbs;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanyaddress() {
		return companyaddress;
	}
	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}
	public String getCompanytel() {
		return companytel;
	}
	public void setCompanytel(String companytel) {
		this.companytel = companytel;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
		this.name=orderno;
		this.draggable="true";
	}
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDebitcart() {
		return debitcart;
	}
	public void setDebitcart(String debitcart) {
		this.debitcart = debitcart;
	}
	public String getEmergencymobile() {
		return emergencymobile;
	}
	public void setEmergencymobile(String emergencymobile) {
		this.emergencymobile = emergencymobile;
	}
	public String getContactmobile() {
		return contactmobile;
	}
	public void setContactmobile(String contactmobile) {
		this.contactmobile = contactmobile;
	}
	public String getApplyid() {
		return applyid;
	}
	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCreditcard() {
		return creditcard;
	}
	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}
	public String getCertno() {
		return certno;
	}
	public void setCertno(String certno) {
		this.certno = certno;
	}
	public String getPartneraccount() {
		return partneraccount;
	}
	public void setPartneraccount(String partneraccount) {
		this.partneraccount = partneraccount;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
}

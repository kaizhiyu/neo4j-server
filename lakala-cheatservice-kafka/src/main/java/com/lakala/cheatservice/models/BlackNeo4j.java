package com.lakala.cheatservice.models;
import java.io.Serializable;

public class BlackNeo4j  implements Serializable{
	
	//黑名单的内容
	private String content;
	
	//名单类型，1-黑名单；2-灰名单；3-白名单
	private String type;
	
	//名单内容类型，1-手机号；2-银行卡；3-身份证；4-设备指纹；5-邮箱；6-终端号；7-住址；8-手机IMEI；9-LBS；10-单名；11-固话；12-单址
	private String contenttype;
	
	//黑名单group
	private String group;
	
	//黑名单收集时间
	private String collecttime;
	
	//黑名单类型:0-个人；1-单位
	private String list_type;
	
	//使用范围，1-所有业务；2-信贷；3-理财；4-支付
	private String userange;

	//有效时间
	private String validtime;
	
	//风险类型：00-涉嫌中介-包装资料、01-涉嫌中介-未包装资料；02-要求加黑；03-伪造证件或照片；04-恶意拖欠；05-虚假资料；06-身份冒用；07-贷款用途不良；08-其他；09-外部黑名单
	private String risktype;
	
	//合同和申请id
	private String applyid;
	
	//数据来源，0-人工创建；1-同盾；2-邦盛；3-信贷；4-理财；5-支付
	private String origin;
	
	//创建时间
	private  String createtime;
	
	//修改时间
	private String modifytime;
	
	//创建人id
	private String creatorid;
	
	//创建人姓名
	private String creatorname;
	
	//修改人id
	private String modifierid;
	
	//修改人姓名
	private String modifiername;
	
	
	//描述
	private String list_desc;
	
	public String getList_desc() {
		return list_desc;
	}

	public void setList_desc(String list_desc) {
		this.list_desc = list_desc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}

	public String getList_type() {
		return list_type;
	}

	public void setList_type(String list_type) {
		this.list_type = list_type;
	}

	public String getUserange() {
		return userange;
	}

	public void setUserange(String userange) {
		this.userange = userange;
	}

	public String getValidtime() {
		return validtime;
	}

	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}

	public String getRisktype() {
		return risktype;
	}

	public void setRisktype(String risktype) {
		this.risktype = risktype;
	}

	public String getApplyid() {
		return applyid;
	}

	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public String getModifierid() {
		return modifierid;
	}

	public void setModifierid(String modifierid) {
		this.modifierid = modifierid;
	}

	public String getModifiername() {
		return modifiername;
	}

	public void setModifiername(String modifiername) {
		this.modifiername = modifiername;
	}
	
}

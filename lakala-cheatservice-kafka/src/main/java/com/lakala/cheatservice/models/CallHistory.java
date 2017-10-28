package com.lakala.cheatservice.models;


import java.io.Serializable;

public class CallHistory implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * nickname
     */
	private String nickname;
	/**
	 * 机主电话
	 */
	private String phone;
	/**
	 * 通话类型
	 * 1：来电  2：去电  3：未接
	 */
	private int type;
	/**
	 * 来电号码
	 */
    private String caller_phone;
    /**
     * 通话时间
     */
    private String date;
    /**
     * 通话时长
     */
    private String duration;
    /**
     *通话开始小时（0-24小时）
     */
    private String hour24;
    
	public String getHour24() {
		return hour24;
	}
	public void setHour24(String hour24) {
		this.hour24 = hour24;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCaller_phone() {
		return caller_phone;
	}
	public void setCaller_phone(String caller_phone) {
		this.caller_phone = caller_phone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
    
}

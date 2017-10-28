package com.lakala.test.jmeter.cheat.model;

import java.io.Serializable;
import java.util.Date;

public class LBSPosition implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省 
	 */
	private String province;
    /**
     * 市
     */
	private String city;
	/**
	 * 区
	 */
	private String  area;
	/**
	 * 街道
	 */
	private String street;
	
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 维度
	 */
	private String latitude;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 时间
	 */
	private Date date;
	
	/**
	 * collectime
	 */
	private Date collectTime;
	
	
	
	
	
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "LBSPosition [country=" + country + ", province=" + province
				+ ", city=" + city + ", area=" + area + ", street=" + street
				+ ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", date=" + date + "]";
	}
	
	
}

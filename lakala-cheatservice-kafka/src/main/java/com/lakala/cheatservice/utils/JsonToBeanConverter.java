package com.lakala.cheatservice.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.lakala.cheatservice.models.Contact;
import com.lakala.cheatservice.models.LBSPosition;

public class JsonToBeanConverter {
	public static LBSPosition jsonToLBS4Android(String lbs){
		try {
			JSONObject jsonObject = JSON.parseObject(lbs);
			LBSPosition lbsPosition = new LBSPosition();
			lbsPosition.setProvince(jsonObject.getString("city"));
			lbsPosition.setCity(jsonObject.getString("city"));
			lbsPosition.setArea(jsonObject.getString("area"));
			lbsPosition.setStreet(jsonObject.getString("street"));
			lbsPosition.setAddress(jsonObject.getString("address"));
			lbsPosition.setLatitude(jsonObject.getString("Latitude"));
			lbsPosition.setLongitude(jsonObject.getString("Longitude"));
			return lbsPosition;
		} catch (JSONException e) {
			return null;
		}
		
	}
	
	public static LBSPosition jsonToLBS4Android2(String lbs){
		try {
			JSONObject jsonObject = JSON.parseObject(lbs);
			LBSPosition lbsPosition = new LBSPosition();
			lbsPosition.setProvince(jsonObject.getString("city"));
			lbsPosition.setCity(jsonObject.getString("city"));
			lbsPosition.setArea(jsonObject.getString("area"));
			lbsPosition.setStreet(jsonObject.getString("street"));
			lbsPosition.setAddress(jsonObject.getString("address"));
			lbsPosition.setLatitude(jsonObject.getString("Latitude"));
			lbsPosition.setLongitude(jsonObject.getString("Longitude"));
			return lbsPosition;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public static  LBSPosition jsonToLBS4IOS(String lbs){
		lbs = lbs.replace("{","");
		lbs = lbs.replace("}","");
		String [] lbsArray = lbs.trim().split(";");
		LBSPosition lbsPosition = new LBSPosition();
		for (int i = 0; i < lbsArray.length; i++) {
			String [] splits = lbsArray[i].split("=");
			if(splits.length>=2){
				String key = splits[0].trim().replace("\"","");
				String value = splits[1].trim().replace("\"","");
				if("Country".equals(key)){
					lbsPosition.setCountry(value);
				}else if("City".equals(key)){
					lbsPosition.setProvince(value);
				}else if("State".equals(key)){
					lbsPosition.setCity(value);
				}else if("SubLocality".equals(key)){
					lbsPosition.setArea(value);
				}else if("Street".equals(key)){
					lbsPosition.setStreet(value);
				}else if("Name".equals(key)){
					lbsPosition.setAddress(value);
				}else if("latitude".equals(key)){
					lbsPosition.setLatitude(value);
				}else if("longitude".equals(key)){
					lbsPosition.setLongitude(value);
				}
			}
		}
		return lbsPosition;
	}
	
	
	public static  LBSPosition jsonToLBS4IOS2(String lbs){
		try {
			
		
		lbs = lbs.replace("{","");
		lbs = lbs.replace("}","");
		String [] lbsArray = lbs.trim().split(";");
		LBSPosition lbsPosition = new LBSPosition();
		for (int i = 0; i < lbsArray.length; i++) {
			String [] splits = lbsArray[i].split("=");
			if(splits.length>=2){
				String key = splits[0].trim().replace("\"","");
				String value = splits[1].trim().replace("\"","");
				if("Country".equals(key)){
					lbsPosition.setCountry(value);
				}else if("City".equals(key)){
					lbsPosition.setProvince(value);
				}else if("State".equals(key)){
					lbsPosition.setCity(value);
				}else if("SubLocality".equals(key)){
					lbsPosition.setArea(value);
				}else if("Street".equals(key)){
					lbsPosition.setStreet(value);
				}else if("Name".equals(key)){
					lbsPosition.setAddress(value);
				}else if("latitude".equals(key)){
					lbsPosition.setLatitude(value);
				}else if("longitude".equals(key)){
					lbsPosition.setLongitude(value);
				}
			}
		}
			return lbsPosition;
		} 	catch (Exception e) {
			return null;
		}
	}
	
	public static List<Contact> jsonToContact4Android(String text){
		try {
			JSONArray jsonArray =JSONArray.parseArray(text);
			List<Contact> list = new ArrayList<Contact>();
			Contact contact=null;
			for (Object object : jsonArray) {
				JSONObject obj = (JSONObject)object;
				contact = new Contact();
				contact.setName(StringUtils.isBlank(obj.getString("DISPLAY_NAME"))?"-":StrUtils.replaceBlank(obj.getString("DISPLAY_NAME").replaceAll(",","").replaceAll("\"|\\.|#|\\(|\\)|'","")));
				JSONArray jsons = obj.getJSONArray("Phone");
				List<String> phones = new ArrayList<String>();
				if(null != jsons) {
					for (Object object2 : jsons) {
						JSONObject jsonObj = (JSONObject)object2;
						 for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
							 String phone = entry.getValue().toString().replaceAll("-", "").replaceAll("\\u00A0","").replaceAll(" ","").replaceAll("\\(", "").replaceAll("\\)","").replaceAll("\\*","").replaceAll("\\\\", "").replaceAll(";","").replaceAll("#", "");
							 if(phone.startsWith("+86")){
								 phone=phone.substring(3).replaceAll(" ", "");
							 }
							 phone=phone.replaceAll("\\+","").replaceAll("\"|\\.|#|\\(|\\)|'","");
							 if(StringUtils.isBlank(phone)||StrUtils.isChinese(phone)||StrUtils.isContainsStr(phone)){
								 continue;
							 }
							 phones.add(StrUtils.replaceBlank(phone.replaceAll(",","")));
					       }
					}
				}
			
				contact.setPhones(phones);
				list.add(contact);
			}
			return list;
		
		} catch (Exception e) {
			return null;
		}
	}
	
	public static  List<Contact> jsonToContact4IOS(String text){
		try {
			
	
		JSONArray jsonArray =JSONArray.parseArray(text);
		List<Contact> list = new ArrayList<Contact>();
		Contact contact=null;
		for (Object object : jsonArray) {
			JSONObject obj = (JSONObject)object;
			contact = new Contact();
			contact.setName((StringUtils.isBlank(obj.getString("LastName"))?"":StrUtils.replaceBlank(obj.getString("LastName").replaceAll(",","").replaceAll("\"|\\.|#|\\(|\\)|'","")))+(StringUtils.isBlank(obj.getString("FirstName"))?"":StrUtils.replaceBlank(obj.getString("FirstName").replaceAll(",","").replaceAll("\"|\\.|#|\\(|\\)|'",""))));
			contact.setNickName(StringUtils.isBlank(obj.getString("Nickname"))?"-":StrUtils.replaceBlank(obj.getString("Nickname").replaceAll(",", "").replaceAll("\"|\\.|#|\\(|\\)|'","")));
			JSONArray jsons = obj.getJSONArray("Phone");
			List<String> phones = new ArrayList<String>();
			if(null != jsons) {
				for (Object object2 : jsons) {
					JSONObject jsonObj = (JSONObject)object2;
					 for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
						 String phone = entry.getValue().toString().replaceAll("-", "").replaceAll("\\u00A0","").replaceAll(" ","").replaceAll("\\(", "").replaceAll("\\)","").replaceAll("\\*","").replaceAll("\\\\", "").replaceAll(";","").replaceAll("#", "");
						 if(phone.startsWith("+86")){
							 phone=phone.substring(3).replaceAll(" ", "");
						 }
						 phone=phone.replaceAll("\\+","").replaceAll("\"|\\.|#|\\(|\\)|'","");
						 if(StringUtils.isBlank(phone)||StrUtils.isChinese(phone)||StrUtils.isContainsStr(phone)){
							 continue;
						 }
						 phones.add(StrUtils.replaceBlank(phone.replaceAll(",","")));
				       }
				}
			}
			
			contact.setPhones(phones);
			list.add(contact);
		}
		return list;
		
		} catch (Exception e) {
			return null;
		}
	}

}
package com.lakala.cheatservice.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lakala.cheatservice.models.CallHistory;

public class MyUtil {
	public static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static  SimpleDateFormat sdf2 = new SimpleDateFormat("hh");
	
	public static  List<CallHistory>  jsonToBeanList(String json){
		try {
		JSONArray jsonArray =JSONArray.parseArray(json);
		List<CallHistory> list = new ArrayList<CallHistory>();
		CallHistory cho=null;
		for (Object object : jsonArray) {
			JSONObject obj = (JSONObject)object;
			cho = (CallHistory)JSONObject.toJavaObject(obj,CallHistory.class);
		    String sdate=StringUtils.isEmpty(cho.getDate())? "0":cho.getDate();
		    Date  dt=new Date(Long.parseLong(sdate));
		    String dateStr = sdf.format(dt);
		    String hourStr = sdf2.format(dt);
		    cho.setDate(dateStr);
		    cho.setHour24(hourStr);
		    list.add(cho);
		}
		return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

  
}
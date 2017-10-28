package com.lakala.contactlist.batch.query.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFunction {
	 public static void main(String[] args) {
	        String str = "Mon Dec 31 00:00:00 CST 2012";
	        Date date = parse(str, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
	        System.out.printf("%tF %<tT%n", date);
	    }
	 
	    public static Date parse(String str, String pattern, Locale locale) {
	        if(str == null || pattern == null) {
	            return null;
	        }
	        try {
	            return new SimpleDateFormat(pattern, locale).parse(str);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	    public static String format(Date date, String pattern, Locale locale) {
	        if(date == null || pattern == null) {
	            return null;
	        }
	        return new SimpleDateFormat(pattern, locale).format(date);
	    }
	}

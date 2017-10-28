/*******************************************************************************
 * Project Key : BIGDATACENTER 
 * Create on 2016骞�鏈�7鏃�涓嬪崍5:51:50
 * Copyright (c) 2004 - 2016. 鎷夊崱鎷夋敮浠樻湁闄愬叕鍙哥増鏉冩墍鏈� 浜琁CP澶�2007612鍙�
 * 娉ㄦ剰锛氭湰鍐呭浠呴檺浜庢媺鍗℃媺鏀粯鏈夐檺鍏徃鍐呴儴浼犻槄锛岀姝㈠娉勪互鍙婄敤浜庡叾浠栫殑鍟嗕笟鐩殑
 ******************************************************************************/
package com.lakala.cheatservice.models;


/**  
 * <P>域接口</P>
 * @author niezhili 2016年9月27日 下午5:51:50
 * @since 1.0.0.000 
 */
public interface Field {
	
	/**
	 * 返回域名
	 * @author niezhili 2016年11月8日 下午2:43:54
	 * @since 1.0.0.000
	 * @return 域名
	 */
	String getName();
	
	void setName(String name);
	
	
	void setKey(String key);
	
	String getKey();
	
	String getValue(); 

	void setValue(String value); 
	
	String getAlias();

	void setAlias(String alias); 

	String getIsPk(); 

	void setIsPk(String isPk);
	
	String toSigleExpression(String value);
}

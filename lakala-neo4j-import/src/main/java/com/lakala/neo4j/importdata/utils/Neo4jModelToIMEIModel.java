package com.lakala.neo4j.importdata.utils;

import com.lakala.neo4j.importdata.constant.RelationType;
import com.lakala.neo4j.importdata.models.IMEIApplyInfoV;
import com.lakala.neo4j.importdata.models.IMEIDeviceNeo4j;
import com.lakala.neo4j.importdata.models.IMEIDeviceV;
import com.lakala.neo4j.importdata.models.IMEILoanApplyNeo4j;
import com.lakala.neo4j.importdata.models.IMEIMobileNeo4j;
import com.lakala.neo4j.importdata.models.IMEIMobileV;
import com.lakala.neo4j.importdata.models.IMEIModelNeo4j;
import com.lakala.neo4j.importdata.models.IMEIModelV;
import com.lakala.neo4j.importdata.models.IMEIRelationE;
import com.lakala.neo4j.importdata.models.IMEIRelationNeo4j;



public class Neo4jModelToIMEIModel {
	
	public static IMEIMobileV MobileNeo4jToMobileV(IMEIMobileNeo4j mobileNeo4j){
		IMEIMobileV mobileV=new IMEIMobileV(mobileNeo4j.getId(),mobileNeo4j.getMobile(),mobileNeo4j.getGroup(),mobileNeo4j.getType(),mobileNeo4j.getModeltype());
		return mobileV;	
		
	}
	
	public static IMEIDeviceV DeviceNeo4jToDeviceV(IMEIDeviceNeo4j modelNeo4j){
		
		return new IMEIDeviceV(modelNeo4j.getId(),modelNeo4j.getDeviceId(),modelNeo4j.getGroup(),modelNeo4j.getType());	
	}
	
	/**
	 * 将neo4j中除了进件模型转换成对应的界面模型
	 * @param modelNeo4j
	 * @return
	 */
	public static IMEIModelV ModelNeo4jToModelV(IMEIModelNeo4j modelNeo4j){
		
		return new IMEIModelV(modelNeo4j.getId(),modelNeo4j.getModelname(),modelNeo4j.getContent(),modelNeo4j.getGroup());	
	}
	
	public static IMEIApplyInfoV ApplyNeo4jToApplyV(IMEILoanApplyNeo4j loanApplyNeo4j){
		IMEIApplyInfoV applyInfoV=new IMEIApplyInfoV();
//		applyInfoV.setId(loanApplyNeo4j.getId());
		applyInfoV.setModelname(loanApplyNeo4j.getMdoelname());
		applyInfoV.setMobile(loanApplyNeo4j.getMobile());
		applyInfoV.setHometel(loanApplyNeo4j.getHometel());
		applyInfoV.setContactmobile(loanApplyNeo4j.getContactmobile());
		applyInfoV.setEmergencymobile(loanApplyNeo4j.getEmergencymobile());
		applyInfoV.setCertno(loanApplyNeo4j.getCertno());
		applyInfoV.setCreditcard(loanApplyNeo4j.getCreditcard());
		applyInfoV.setDebitcard(loanApplyNeo4j.getDebitcard());
		applyInfoV.setEmail(loanApplyNeo4j.getEmail());
		applyInfoV.setDeviceid(loanApplyNeo4j.getDeviceId());
//		applyInfoV.setGroup(loanApplyNeo4j.getGroup());
		applyInfoV.setUserid(loanApplyNeo4j.getUserid());
		applyInfoV.setUsername(loanApplyNeo4j.getUsername());
		applyInfoV.setOrderno(loanApplyNeo4j.getOrderno());
		applyInfoV.setCompanytel(loanApplyNeo4j.getCompanytel());
		applyInfoV.setCompanyaddress(loanApplyNeo4j.getCompanyaddress());
		applyInfoV.setCompanyname(loanApplyNeo4j.getCompanyname());
		applyInfoV.setLbs(loanApplyNeo4j.getLbs());
		applyInfoV.setIpv4(loanApplyNeo4j.getIpv4());
		applyInfoV.setCollectTime(loanApplyNeo4j.getCollectTime());
		applyInfoV.setChannelmobile(loanApplyNeo4j.getChannelmobile());
		applyInfoV.setUsercoremobile(loanApplyNeo4j.getUsercoremobile());
		applyInfoV.setPartnercontantmobile(loanApplyNeo4j.getPartnercontantmobile());
		applyInfoV.setMerchantmobile(loanApplyNeo4j.getMerchantmobile());
		applyInfoV.setPartneraccount(loanApplyNeo4j.getPartneraccount());
		return applyInfoV;	
		
	}
	
	public static IMEIRelationE RelationNeo4jToRelationE(IMEIRelationNeo4j relationNeo4j){
		RelationType relationType=getEnumFromString(RelationType.class,relationNeo4j.getType());//RelationType.valueOf(relationNeo4j.getType());
		return new IMEIRelationE(relationNeo4j.getId(),relationNeo4j.getStartId(),relationNeo4j.getEndId(),relationType.getRelationName()) ;	
	}
	
	public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string)  
	{  
	    if( c != null && string != null )  
	    {  
	        try  
	        {  
	            return Enum.valueOf(c, string.trim().toUpperCase());  
	        }  
	        catch(IllegalArgumentException ex)  
	        {  
	        }  
	    }  
	    return null;  
	}  
	
}

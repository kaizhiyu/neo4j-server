package com.lakala.test.jmeter.cheat.logic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.lakala.test.jmeter.cheat.constant.ConstantsConfig;
import com.lakala.test.jmeter.cheat.constant.ModelType;
import com.lakala.test.jmeter.cheat.constant.RelationType;
import com.lakala.test.jmeter.cheat.model.CallHistory;
import com.lakala.test.jmeter.cheat.model.CollectData;
import com.lakala.test.jmeter.cheat.model.Contact;
import com.lakala.test.jmeter.cheat.model.MobileV;
import com.lakala.test.jmeter.cheat.model.ModelNeo4j;
import com.lakala.test.jmeter.cheat.repository.Neo4jUtilServiceImpl;
import com.lakala.test.jmeter.cheat.utils.RadomGenerateData;


public class MobileRelationServiceImpl{
	
	final static Logger logger = LoggerFactory.getLogger(MobileRelationServiceImpl.class);

	private String isInsert="";
	public MobileRelationServiceImpl(String isInsert){

		this.isInsert=isInsert;
		
	}
	

	/**
	 * 1.解析消息判断是通话记录还是通讯录消息
	 * 2,保存到neo4j
	 */
	public void saveMobileToNeo4j() {
		logger.info("进入neo4j数据写入,接口配置");
		try {
			
			CollectData collectData = new CollectData();
		    
			collectData.setLoginName(RadomGenerateData.getPhone());//生成手机号码
			collectData.setPlatform(RadomGenerateData.getPlatform());//生成系统类型
			
			collectData.setType(RadomGenerateData.getType());//生成关系类型
//			collectData.setAppVersion(appVersion);
//			collectData = JSON.parseObject( "         ", CollectData.class);

			if ("contactlist".equals(collectData.getType())) {
				if (StringUtils.isNotEmpty(collectData.getLoginName())) {
//					List<Contact> list = null;
					List<ModelNeo4j> contactMobileList = new ArrayList<ModelNeo4j>();

					for (int i = 0; i < 350; i++) {
						ModelNeo4j model=new ModelNeo4j();
						model.setContent(RadomGenerateData.getPhone());
						model.setModelname("Mobile");
						model.setGroup("2");
						model.setType("1");
						contactMobileList.add(model);
					}
//					if(isInsert.equals("1")){
						create(collectData.getLoginName(),contactMobileList,RelationType.CONTACT.getRelationType(),ModelType.MOBILE.getEntityGroup());
//					}
//					else {
//						update(collectData.getLoginName(),contactMobileList,RelationType.CONTACT.getRelationType(),ModelType.MOBILE.getEntityGroup());
//						
//					}
				}
			} else if ("callhistory".equals(collectData.getType())) {
				
				List<ModelNeo4j> contactMobileList = new ArrayList<ModelNeo4j>();

				for (int i = 0; i < 350; i++) {
					ModelNeo4j model=new ModelNeo4j();
					model.setContent(RadomGenerateData.getPhone());
					model.setModelname("Mobile");
					model.setGroup("2");
					contactMobileList.add(model);
				}
				create(collectData.getLoginName(),contactMobileList,RelationType.CALL.getRelationType(),ModelType.MOBILE.getEntityGroup());

			}

		} catch (Exception e) {
			logger.error("【通讯录或者通话记录入库hbase和neo4j】异常内容{}",e.getMessage());
			System.out.println("【通讯录或者通话记录入库hbase和neo4j】异常内容"+e.getMessage());
		}
	}


	
	private void create(String mobile,List<ModelNeo4j> mobileList,String relationType,String group) {
		   String sqlStr=JSON.toJSONString(mobileList);
		   StringBuffer executeSql=new StringBuffer();
		   MobileV mobileV=new MobileV();
		   mobileV.setMobile(mobile);
		   mobileV.setGroup(group);
		   //直接创建关系
		   if (!StringUtils.isEmpty(sqlStr)) {
			   executeSql.append("MERGE (o:Mobile {content:'"+mobile+"'}) SET o.modelname = 'Mobile',o.content='"+mobile+"', o.group = '2' ");
			   executeSql.append("\n");
			   executeSql.append("with "+sqlStr.replace("{\"", "{").replace("\":", ":").replace(",\"", ",")+" as batch ");
			   executeSql.append("\n");
			   executeSql.append("UNWIND batch as row ");
			   executeSql.append("MATCH (m:Mobile {content:'"+mobile+"'}) ");
			   executeSql.append("MERGE (c:Mobile {content:row.content}) SET c.modelname = row.modelname,c.content=row.content, c.group = row.group  ");
			   executeSql.append("MERGE (m)-[:"+relationType+"]->(c) ");
			   if(!executeSql.toString().equals("")){
//				   System.out.println(executeSql.toString());
				   Neo4jUtilServiceImpl.executeInsert(executeSql.toString());
			   }
		}
		   
	}
	
/**
 

	private void create(String mobile,Set<String> set,String relationType,String group) {
		   String  sql="";
//		   Neo4jUtilServiceImpl neo4jUtilServiceImpl=new Neo4jUtilServiceImpl();
		   MobileV mobileV=new MobileV();
		   mobileV.setMobile(mobile);
		   mobileV.setGroup(group);
		   //直接创建关系
		   StringBuilder sbSql=new StringBuilder();
//		   List<String> sqlList=new ArrayList<String>();
//		   List<String> sqlList=new ArrayList<String>();
		   if (!CollectionUtils.isEmpty(set)) {
//			   Neo4jUtilServiceImpl.executeInsert("create (p:"+ModelType.MOBILE.getModelName()+" { content:'"+mobile+"'}) set p.modelname='"+ModelType.MOBILE.getModelName()+"',p.content='"+mobile+"',p.group='"+group+"' ");
			   int i=0;
			   for (String phone : set) {
				   i++;
				   if(StringUtils.isNotBlank(phone)){
					   if(!phone.trim().equals(mobile.trim()))
					   {
						   sql="create (p"+i+":"+ModelType.MOBILE.getModelName()+" { content:'"+mobile+"'}) set p"+i+".modelname='"+ModelType.MOBILE.getModelName()+"',p"+i+".content='"+mobile+"',p"+i+".group='"+group+"' create (m"+i+":"+ModelType.MOBILE.getModelName()+" {content:'"+phone+"'}) set m"+i+".modelname='"+ModelType.MOBILE.getModelName()+"',m"+i+".content='"+phone+"',m"+i+".group='"+group+"' create (p"+i+")-[r"+i+":"+relationType+"]->(m"+i+")";     
//						   sqlList.add(sql);
						   sbSql.append(sql); //单个sql组合成string写入
						   sbSql.append("\n");
//						   sqlList.add(sql);
						   if(i==250){
//							   Neo4jUtilServiceImpl.batchInsert(sqlList);
//							   sqlList.clear();
							   Neo4jUtilServiceImpl.executeInsert(sbSql.toString());
//							   Neo4jUtilServiceImpl.executeInserttx(sbSql.toString());
							   sbSql=new StringBuilder(); 
							   i=0;
						   }
					   }
				   }
			}
			   if(!sbSql.toString().equals("")){
//				   Neo4jUtilServiceImpl.batchInsert(sqlList);
				   Neo4jUtilServiceImpl.executeInsert(sbSql.toString());
//				   Neo4jUtilServiceImpl.executeInserttx(sbSql.toString());
			   }
//			   Neo4jUtilServiceImpl.batchInsert(sqlList);
		}
		   
	}
	
	


	private void update(String mobile,Set<String> set,String relationType,String group) {
		   String  sql="";
		   
		   MobileV mobileV=new MobileV();
		   mobileV.setMobile(mobile);
		   mobileV.setGroup(group);
		   //直接创建关系
		   StringBuilder sbSql=new StringBuilder();
//		   List<String> sqlList=new ArrayList<String>();
//		   List<String> sqlList=new ArrayList<String>();
		   if (!CollectionUtils.isEmpty(set)) {
//			   Neo4jUtilServiceImpl.executeInsert("merge (p:"+ModelType.MOBILE.getModelName()+" { content:'"+mobile+"'}) set p.modelname='"+ModelType.MOBILE.getModelName()+"',p.content='"+mobile+"',p.group='"+group+"' ");
			   int i=0;
			   for (String phone : set) {
				   i++;
				   if(StringUtils.isNotBlank(phone)){
					   if(!phone.trim().equals(mobile.trim()))
					   {
						   sql="merge (p"+i+":"+ModelType.MOBILE.getModelName()+" { content:'"+mobile+"'}) set p"+i+".modelname='"+ModelType.MOBILE.getModelName()+"',p"+i+".content='"+mobile+"',p"+i+".group='"+group+"' merge (m"+i+":"+ModelType.MOBILE.getModelName()+" {content:'"+phone+"'}) set m"+i+".modelname='"+ModelType.MOBILE.getModelName()+"',m"+i+".content='"+phone+"',m"+i+".group='"+group+"' merge (p"+i+")-[r"+i+":"+relationType+"]->(m"+i+")";     
//						   sqlList.add(sql);
						   sbSql.append(sql); //单个sql组合成string写入
						   sbSql.append("\n");
						   
//						   sqlList.add(sql);
						   if(i==250){
							   
//							   Neo4jUtilServiceImpl.batchInsert(sqlList);
//							   sqlList.clear();
							   Neo4jUtilServiceImpl.executeInsert(sbSql.toString());
//							   Neo4jUtilServiceImpl.executeInserttx(sbSql.toString());
//							   SqlHelper.executeUpdate(sbSql.toString(), null);
							   sbSql=new StringBuilder(); 
							   i=0;
						   }
					   }
				   }
			}
			   if(!sbSql.toString().equals("")){
//				   Neo4jUtilServiceImpl.batchInsert(sqlList);
				   Neo4jUtilServiceImpl.executeInsert(sbSql.toString());
//				   Neo4jUtilServiceImpl.executeInserttx(sbSql.toString());
			   }
		}
		   
	}
	*/
}

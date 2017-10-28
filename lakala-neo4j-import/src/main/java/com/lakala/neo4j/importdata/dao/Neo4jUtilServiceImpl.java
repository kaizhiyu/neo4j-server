/*******************************************************************************
 * Project Key : BIGDATACENTER 
 * Create on 2016年12月25日 下午1:00:55
 * Copyright (c) 2004 - 2016. 拉卡拉支付有限公司版权所有. 京ICP备12007612号
 * 注意：本内容仅限于拉卡拉支付有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.lakala.neo4j.importdata.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;





import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.lakala.neo4j.importdata.constant.ModelType;
import com.lakala.neo4j.importdata.constant.RelationType;
import com.lakala.neo4j.importdata.models.IMEIDataSourceStringCollection;


/**
 * <P>
 * 对功能点的描述
 * </P>
 * 
 * @author guozhijie 2017年04月19日 上午10:00:55
 * @since 1.0.0.000
 */

public class Neo4jUtilServiceImpl{
	
	private final static String applyrelationstr=RelationType.APPLYMYMOBILE.getRelationType()+"|"+RelationType.LOANAPPLY.getRelationType()+"|"+RelationType.DEVICE.getRelationType()+"|"
			+RelationType.BANKCARD.getRelationType()+"|"+RelationType.CREDITCARD.getRelationType()+"|"+RelationType.IDENTIFICATION.getRelationType()+"|"
			+RelationType.EMAIL.getRelationType()+"|"+RelationType.TERMINAL.getRelationType()+"|"+RelationType.MOBILEIMEI.getRelationType()+"|"
			+RelationType.LBS.getRelationType()+"|"+RelationType.COMPANY.getRelationType()+"|"+RelationType.IPV4.getRelationType()+"|"
			+RelationType.COMPANYADDRESS.getRelationType()+"|"+RelationType.COMPANYTEL.getRelationType()+"|"+RelationType.EMERGENCYCONTACT.getRelationType()+"|"
			+RelationType.RECOMMEND.getRelationType();
	
	
	private static  String url="bolt://10.16.65.16:7687";

	private static  String username="neo4j";

	private static  String password="123456";
	

	public void executeInsert(String sql) {
		Driver driver=null;
		Session session=null;
		try {
			long t1=System.currentTimeMillis();
				
			driver = GraphDatabase.driver(url,
					AuthTokens.basic(username,password));
			session = driver.session();
			
			session.run(sql);
//			System.out.println("executeInsert cost seconds time:"+(System.currentTimeMillis()- t1)/1000);
			System.out.println("executeInsert cost milliseconds time:"+(System.currentTimeMillis()- t1)+"ms");
		} catch (Exception e) {
			System.err.println("【执行neo4j的sql报错】Error msg: "+e.getMessage()+"  Error:"+ e);
		}
		finally{
			if(session!=null){
				session.close();
			}
			if(driver!=null){
				driver.close();
			}
		}
	}


	
	public void batchInsert(List<String> sqlList) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;

		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			for(String sql:sqlList){
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			
		}catch(Exception ex){
			System.err.println("batchInsert Error:"+ex.getMessage());
			
		}
		finally {
			try{
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("batchInsert connnetion release fail! Error:"+ex.getMessage());
			}
		}
	}


	public  static  String executeQuery(String sql) {
		Driver driver=null;
		Session session=null;
		try {
			driver = GraphDatabase.driver(url,
					AuthTokens.basic(username,password));
			session = driver.session();
			StatementResult result = session.run(sql);
			if(result.hasNext()){
				Record record = result.next();
				return record.get("name")==null?null:record.get("name").toString();
			}
		} catch (Exception e) {
			System.err.println("【查询neo4j报错】:"+e.getMessage());
		}
		return null;
	}
	
	
	
	public static IMEIDataSourceStringCollection QueryApplyByApplyLevel0(String orderno) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		List<String> modelList=new ArrayList<String>();
		List<String> relationList=new ArrayList<String>();
		IMEIDataSourceStringCollection sourceStringCollection=new IMEIDataSourceStringCollection();
		//applymymobile|loanapply|device|bankcard|creditcard|identification|email|terminal|mobileimei|LBS|company|ipv4|companyaddress|companytel|emergencymobile|recommend

		
		//System.out.println(relationstr.toString());
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			String sqlString ="match (n:ApplyInfo {orderno:'"+orderno+"'})-[m:"+applyrelationstr+"] -(p) return n,n.lbs,m,p";
			System.out.println("QueryApplyByApplyLevel0:"+sqlString);
			rs = stmt.executeQuery(sqlString);
			 while(rs.next())
			 {
				 
				 if(rs.getString("n.lbs")!=null){
					 modelList.add(rs.getString("n").replace(rs.getString("n.lbs"), ""));
				 }else {
					 modelList.add(rs.getString("n"));
				}
				 relationList.add(rs.getString("m"));
				 modelList.add(rs.getString("p"));
				 sourceStringCollection.modelList=modelList;
				 sourceStringCollection.relationList=relationList;

			 }
		}catch(Exception ex){
			System.err.println("QueryApplyByApplyLevel0 Error:"+ex.getMessage());
			
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueryApplyByApplyLevel0 connnetion release fail! Error:"+ex.getMessage());
			}
		}
		
		return sourceStringCollection;
	}
	
	

	
	public static  IMEIDataSourceStringCollection QueryApplyByApplyLevel1(String orderno) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		List<String> modelList=new ArrayList<String>();
		List<String> relationList=new ArrayList<String>();
		IMEIDataSourceStringCollection sourceStringCollection=new IMEIDataSourceStringCollection();
		//applymymobile|loanapply|device|bankcard|creditcard|identification|email|terminal|IMEI|LBS|company|ipv4|companyaddress|companytel|emergencymobile|recommend

		
		//System.out.println(relationstr.toString());
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			String sqlString ="match (n:ApplyInfo {orderno:'"+orderno+"'})-[m:"+applyrelationstr+"] -(p)-[r:"+applyrelationstr+"]-(q:ApplyInfo) return n,n.lbs,m,p,r,q,q.lbs";
			System.out.println("QueryApplyByApplyLevel1:"+sqlString);
			rs = stmt.executeQuery(sqlString);
			 while(rs.next())
			 {
				 if(rs.getString("n.lbs")!=null){
					 modelList.add(rs.getString("n").replace(rs.getString("n.lbs"), ""));
				 }else {
					 modelList.add(rs.getString("n"));
				}
				 relationList.add(rs.getString("m"));
				 modelList.add(rs.getString("p"));
				 relationList.add(rs.getString("r"));
				 if(rs.getString("q.lbs")!=null){
					 modelList.add(rs.getString("q").replace(rs.getString("q.lbs"), ""));
				 }else {
					 modelList.add(rs.getString("q"));
				}
				 sourceStringCollection.modelList=modelList;
				 sourceStringCollection.relationList=relationList;

			 }
		}catch(Exception ex){
			System.err.println("QueryApplyByApplyLevel1 Error:"+ex.getMessage());
			
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueryApplyByApplyLevel1 connnetion release fail! Error:"+ex.getMessage());
			}
		}
		
		return sourceStringCollection;
	}
	
	
	
	public static  IMEIDataSourceStringCollection QueryApplyByApplyLevel2(String orderno) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		List<String> modelList=new ArrayList<String>();
		List<String> relationList=new ArrayList<String>();
		IMEIDataSourceStringCollection sourceStringCollection=new IMEIDataSourceStringCollection();
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			String sqlString="match (n:ApplyInfo {orderno:'"+orderno+"'})-[m:"+applyrelationstr+"] -(p)-[r:contact|call|device]-(a:Mobile {type:'1'})-[l:"+applyrelationstr+"] -(q:ApplyInfo) return n,n.lbs,p,a,q,q.lbs,m,r,l";
			System.out.println("QueryApplyByApplyLevel2:"+sqlString);
			rs = stmt.executeQuery(sqlString);
			 while(rs.next())
			 {
//				 modelList.add(rs.getString("n"));
				 if(rs.getString("n.lbs")!=null){
					 modelList.add(rs.getString("n").replace(rs.getString("n.lbs"), ""));
				 }else {
					 modelList.add(rs.getString("n"));
				}
				 modelList.add(rs.getString("p"));
				 modelList.add(rs.getString("a"));
//				 modelList.add(rs.getString("q"));
				 if(rs.getString("q.lbs")!=null){
					 modelList.add(rs.getString("q").replace(rs.getString("q.lbs"), ""));
				 }else {
					 modelList.add(rs.getString("q"));
				}
				 relationList.add(rs.getString("m"));
				 relationList.add(rs.getString("r"));
				 relationList.add(rs.getString("l"));
				 sourceStringCollection.modelList=modelList;
				 sourceStringCollection.relationList=relationList;

			 }
		}catch(Exception ex){
			System.err.println("QueryApplyByApplyLevel2 Error:"+ex.getMessage());
			
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueryApplyByApplyLevel2 connnetion release fail! Error:"+ex.getMessage());
			}
		}
		
		return sourceStringCollection;
	}
	
	
	
	public static  IMEIDataSourceStringCollection QueryCallRelation(String mobile) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		List<String> modelList=new ArrayList<String>();
		List<String> relationList=new ArrayList<String>();
		IMEIDataSourceStringCollection sourceStringCollection=new IMEIDataSourceStringCollection();
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			rs = stmt.executeQuery("match (n:Mobile {content:'"+mobile+"'})-[r:contact] -(p) return n,r,p");
			 while(rs.next())
			 {
				 modelList.add(rs.getString("n"));
				 modelList.add(rs.getString("p"));
				 relationList.add(rs.getString("r"));
				 sourceStringCollection.modelList=modelList;
				 sourceStringCollection.relationList=relationList;
			 }
		}catch(Exception ex){
			System.err.println("QueryCallRelation Error:"+ex.getMessage());
			
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueryCallRelation connnetion release fail! Error:"+ex.getMessage());
			}
		}
		
		return sourceStringCollection;
	}
	
	
	
	public static  IMEIDataSourceStringCollection QueryContactRelation(String mobile) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		List<String> modelList=new ArrayList<String>();
		List<String> relationList=new ArrayList<String>();
		IMEIDataSourceStringCollection sourceStringCollection=new IMEIDataSourceStringCollection();
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			rs = stmt.executeQuery("match (n:Mobile {mobile:'"+mobile+"'})-[:contact] -(p) return n,r,p");
			 while(rs.next())
			 {
				 modelList.add(rs.getString("n"));
				 modelList.add(rs.getString("p"));
				 relationList.add(rs.getString("r"));
				 sourceStringCollection.modelList=modelList;
				 sourceStringCollection.relationList=relationList;
			 }
		}catch(Exception ex){
			System.err.println("QueryContactRelation Error:"+ex.getMessage());
			
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueryContactRelation connnetion release fail! Error:"+ex.getMessage());
			}
		}
		
		return sourceStringCollection;
	}


	
	public static  IMEIDataSourceStringCollection QueryContactCallRelation(String mobile) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		List<String> modelList=new ArrayList<String>();
		List<String> relationList=new ArrayList<String>();
		IMEIDataSourceStringCollection sourceStringCollection=new IMEIDataSourceStringCollection();
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			rs = stmt.executeQuery("match (n:Mobile {mobile:'"+mobile+"'})-[r:contact|call*1..5] -(p) return n,r,p");
			 while(rs.next())
			 {
				 modelList.add(rs.getString("n"));
				 relationList.add(rs.getString("r"));
				 modelList.add(rs.getString("p"));
				 sourceStringCollection.modelList=modelList;
				 sourceStringCollection.relationList=relationList;
			 }
		}catch(Exception ex){
			System.err.println("QueryContactCallRelation Error:"+ex.getMessage());
			
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueryContactCallRelation connnetion release fail! Error:"+ex.getMessage());
			}
		}
		return sourceStringCollection;
	}
	
	

	
	public static  IMEIDataSourceStringCollection QueyModelByOrderno(String orderno) {
		
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		List<String> modelList=new ArrayList<String>();
		List<String> relationList=new ArrayList<String>();
		IMEIDataSourceStringCollection sourceStringCollection=new IMEIDataSourceStringCollection();
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			String sqlString="match (n:"+ModelType.APPLYINFO.getModelName()+" {orderno:'"+orderno+"'})-[r:"+applyrelationstr+"] -(p) return n,r,p";
			rs = stmt.executeQuery(sqlString);
			 while(rs.next())
			 {
				 
				 relationList.add(rs.getString("r"));
				 modelList.add(rs.getString("p"));
				 sourceStringCollection.selectNode=rs.getString("n");
				 sourceStringCollection.modelList=modelList;
				 sourceStringCollection.relationList=relationList;
			 }
		}catch(Exception ex){
			System.err.println("QueyModelByOrderno Error:"+ex.getMessage());
			
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueyModelByOrderno connnetion release fail! Error:"+ex.getMessage());
			}
		}
		return sourceStringCollection;
	}

	
	public static  IMEIDataSourceStringCollection QueyApplyByContent(String modelname,
			String content) {
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		List<String> modelList=new ArrayList<String>();
		List<String> relationList=new ArrayList<String>();
		IMEIDataSourceStringCollection sourceStringCollection=new IMEIDataSourceStringCollection();
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			String sqlString="match (n:"+modelname+" {content:'"+content+"'})-[r:"+applyrelationstr+"] -(p:ApplyInfo) return n,r,p,p.lbs";
			System.out.println("QueyApplyByContent:"+sqlString);
			rs = stmt.executeQuery(sqlString);
			 while(rs.next())
			 {
				 relationList.add(rs.getString("r"));
//				 System.out.println(rs.getString("p"));
				 if(rs.getString("p.lbs")!=null){
					 modelList.add(rs.getString("p").replace(rs.getString("p.lbs"), ""));
				 }else {
					 modelList.add(rs.getString("p"));
				}
				 sourceStringCollection.modelList=modelList;
				 sourceStringCollection.relationList=relationList;
				 sourceStringCollection.selectNode=rs.getString("n");
			 }
		}catch(Exception ex){
			System.err.println("QueyApplyByContent Error:"+ex.getMessage());
			
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueyApplyByContent connnetion release fail! Error:"+ex.getMessage());
			}
		}
		return sourceStringCollection;
	}


	
	public static  int QueyContactCallBlackCount(String mobile, String type,
			String list_type,String relation) {
		
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		int count=0;
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			String sql ="match (n:"+ModelType.MOBILE.getModelName()+" {content:'"+mobile+"'})-[r:"+relation+"]-(p:"+ModelType.MOBILE.getModelName()+" {type:'"+type+"',list_type:'"+list_type+"'}) return count(p) as count";
			rs = stmt.executeQuery(sql);
			 while(rs.next())
			 {
				 count = rs.getInt("count");

			 }
		}catch(Exception ex){
			System.err.println("QueyContactCallBlackCount Error:"+ex.getMessage());
		}
		finally {
			try{
			   if(rs!=null){
			    	rs.close();
			    }
			    if(stmt!=null){
			    	stmt.close();
			    }
			    if (con!=null) {
					con.close();
				}
			}catch(Exception ex){
				System.err.println("QueyContactCallBlackCount connnetion release fail! Error:"+ex.getMessage());
			}
		}
		return count;
	}
}

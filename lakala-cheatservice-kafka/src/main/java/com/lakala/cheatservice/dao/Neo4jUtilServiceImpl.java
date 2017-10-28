package com.lakala.cheatservice.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lakala.cheatservice.constant.ConstantsConfig;
import com.lakala.cheatservice.constant.ModelType;
//import com.lakala.cheatservice.constant.RelationType;




public class Neo4jUtilServiceImpl {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
//	private final String applyrelationstr=RelationType.APPLYMYMOBILE.getRelationType()+"|"+RelationType.LOANAPPLY.getRelationType()+"|"+RelationType.DEVICE.getRelationType()+"|"
//			+RelationType.BANKCARD.getRelationType()+"|"+RelationType.CREDITCARD.getRelationType()+"|"+RelationType.IDENTIFICATION.getRelationType()+"|"
//			+RelationType.EMAIL.getRelationType()+"|"+RelationType.TERMINAL.getRelationType()+"|"+RelationType.MOBILEIMEI.getRelationType()+"|"
//			+RelationType.LBS.getRelationType()+"|"+RelationType.COMPANY.getRelationType()+"|"+RelationType.IPV4.getRelationName()+"|"
//			+RelationType.COMPANYADDRESS.getRelationType()+"|"+RelationType.COMPANYTEL.getRelationType();




	public static void executeInsert(String sql) {
		Driver driver=null;
		Session session=null;
		try {
			long t1=System.currentTimeMillis();
				
			driver = GraphDatabase.driver(ConstantsConfig.neo4jurl,
					AuthTokens.basic(ConstantsConfig.neo4jusername,ConstantsConfig.neo4jpassword));
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
			con = DriverManager.getConnection("jdbc:neo4j:"+ConstantsConfig.neo4jurl,ConstantsConfig.neo4jusername,ConstantsConfig.neo4jpassword);
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

	@PostConstruct
	private Session getSession() {
		Driver driver = GraphDatabase.driver(ConstantsConfig.neo4jurl,
				AuthTokens.basic(ConstantsConfig.neo4jusername,ConstantsConfig.neo4jpassword));
		Session session = driver.session();
		return session;
	}
	
	
	public String executeQuery(String sql) {
		try {
			StatementResult result = getSession().run(sql);
			if(result.hasNext()){
				Record record = result.next();
				return record.get("name")==null?null:record.get("name").toString();
			}
		} catch (Exception e) {
			System.err.println("【查询neo4j报错】:"+e.getMessage());
		}
		return null;
	}
	
	
	public int QueyContactCallBlackCount(String mobile, String type,
			String list_type,String relation) {
		
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
		int count=0;
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+ConstantsConfig.neo4jurl,ConstantsConfig.neo4jusername,ConstantsConfig.neo4jpassword);
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

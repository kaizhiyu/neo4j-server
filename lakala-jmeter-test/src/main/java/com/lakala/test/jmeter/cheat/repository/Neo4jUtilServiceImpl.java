/*******************************************************************************
 * Project Key : BIGDATACENTER 
 * Create on 2016年12月25日 下午1:00:55
 * Copyright (c) 2004 - 2016. 拉卡拉支付有限公司版权所有. 京ICP备12007612号
 * 注意：本内容仅限于拉卡拉支付有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.lakala.test.jmeter.cheat.repository;

import java.io.File;
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
import org.neo4j.jdbc.PreparedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lakala.test.jmeter.cheat.constant.ModelType;
import com.lakala.test.jmeter.cheat.constant.RelationType;
import com.lakala.test.jmeter.cheat.model.DataSourceStringCollection;


public class Neo4jUtilServiceImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(Neo4jUtilServiceImpl.class);
	
	private final String applyrelationstr=RelationType.APPLYMYMOBILE.getRelationType()+"|"+RelationType.LOANAPPLY.getRelationType()+"|"+RelationType.DEVICE.getRelationType()+"|"
			+RelationType.BANKCARD.getRelationType()+"|"+RelationType.CREDITCARD.getRelationType()+"|"+RelationType.IDENTIFICATION.getRelationType()+"|"
			+RelationType.EMAIL.getRelationType()+"|"+RelationType.TERMINAL.getRelationType()+"|"+RelationType.MOBILEIMEI.getRelationType()+"|"
			+RelationType.LBS.getRelationType()+"|"+RelationType.COMPANY.getRelationType()+"|"+RelationType.IPV4.getRelationName()+"|"
			+RelationType.COMPANYADDRESS.getRelationType()+"|"+RelationType.COMPANYTEL.getRelationType();
	

	public static String url;
	private static String username="neo4j";
	private static String password="123456";
	
	
//	private String ip;
//	public Neo4jUtilServiceImpl(String ip){
//		//如果ip为空则使用本地ip地址
//		if(ip==null||ip.equals("")){
//			this.ip="127.0.0.1";
//		}else {
//			this.ip=ip;
//		}
//		this.url="bolt://"+this.ip+":7687";
//		
//		
//	}
//	
	
	
	private static Session getSession() {
		Driver driver = GraphDatabase.driver(url,
				AuthTokens.basic(username,password));
		Session session = driver.session();
		return session;
	}
	
	public static void executeInsert(String sql) {
		Driver driver=null;
		Session session=null;
//		Transaction tx=null;
		try {
			long t1=System.currentTimeMillis();
				
			driver = GraphDatabase.driver(url,
					AuthTokens.basic(username,password));
			session = driver.session();
			session.run(sql);
//			tx= session.beginTransaction();

//			tx.run(sql);
//			tx.success();
//			System.out.println("executeInsert cost seconds time:"+(System.currentTimeMillis()- t1)/1000);
			logger.info("executeInsert cost milliseconds time:"+(System.currentTimeMillis()- t1)+"ms");
		} catch (Exception e) {
			logger.error("【执行neo4j的sql报错】Error msg: "+e.getMessage()+"  Error:"+ e);
			System.out.println("【执行neo4j的sql报错】Error msg: "+e.getMessage()+"  Error:"+ e+"   executeSql:"+sql);
		}
		finally{
			
//			if(tx!=null){
//				tx.close();
//			}
			if(session!=null){
				session.close();
			}
			if(driver!=null){
				driver.close();
			}
		}
	}

	public static void executeInserttx(String sql) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;
//		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			stmt.execute(sql);
			con.commit();
			
		}catch(Exception ex){
			logger.error("batchInserttx Error:"+ex.getMessage());
			
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
				logger.error("batchInserttx connnetion release fail! Error:"+ex.getMessage());
			}
		}
		
	}

	public static void batchInsert(List<String> sqlList) {
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
			logger.error("batchInsert Error:"+ex.getMessage());
			
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
				logger.error("batchInsert connnetion release fail! Error:"+ex.getMessage());
			}
		}
		

	}

	
/*	private String DB_PATH = "D:/Program Files/Neo4j/graph.db";  
	GraphDatabaseService graphDB = new GraphDatabaseFactory().newEmbeddedDatabase(new File(DB_PATH));  */
	  

}

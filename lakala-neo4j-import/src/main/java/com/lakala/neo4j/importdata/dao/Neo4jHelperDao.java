package com.lakala.neo4j.importdata.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class Neo4jHelperDao {
	
	public static String url;
	private static String username="neo4j";
	private static String password="123456";
/*	public static void executeInserttx(String sql) {
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
			System.err.println("batchInserttx Error:"+ex.getMessage());
			
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
				System.err.println("batchInserttx connnetion release fail! Error:"+ex.getMessage());
			}
		}
		
	}*/
	
	
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
			System.out.println("executeInsert cost milliseconds time:"+(System.currentTimeMillis()- t1)+"ms");
		} catch (Exception e) {
//			logger.error("【执行neo4j的sql报错】Error msg: "+e.getMessage()+"  Error:"+ e);
			System.err.println("【执行neo4j的sql报错】Error msg: "+e.getMessage()+"  Error:"+ e+"   executeSql:"+sql);
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

}

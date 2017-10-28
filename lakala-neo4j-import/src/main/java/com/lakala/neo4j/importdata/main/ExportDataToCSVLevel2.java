package com.lakala.neo4j.importdata.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;


public class ExportDataToCSVLevel2 {

	public static String url;
	private static String username="neo4j";
	private static String password="123456";
	public static void main(String[] args) {
		System.out.println("args:"+args[0]);
		String[] argString=args[0].split(",");
		String ip=argString[1];
		url="bolt://"+ip+":7687";
		System.out.println("url:"+url);
//		QueryApplyByApplyLevel1("ApplyInfo",argString[0].replace('_', '|'),"/home/graphdb/batchimporthandle/apply/applyexportdata/callcontactlevel2/"+argString[1]+".csv");
		QueryApplyByApplyLevel1("ApplyInfo",argString[0].replace('_', '|'),"/home/graphdb/batchimporthandle/apply/exportblackorder2/alllevel2.csv");
	}
	
	
	public static void QueryApplyByApplyLevel1(String modelname,String relation,String filepath) {
		//192.168.0.247
		Connection con =null;
		Statement stmt =null;
		ResultSet rs =null;
//		//进件实体对应的json字符串
//		List<String> modelList=new ArrayList<String>();
//		//进件关系
//		List<String> relationList=new ArrayList<String>();
		//applymymobile|loanapply|device|bankcard|creditcard|identification|email|terminal|IMEI|LBS|company|ipv4|companyaddress|companytel

		
		//System.out.println(relationstr.toString());
		try
		{
			System.out.println("Start export data!");
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
//			stmt.setQueryTimeout(120);
			 System.out.println("pathfile:"+filepath);
//			String sql="match (n:"+modelname+" {tag:'buxia'})-[r:"+relation+"]-(p)-[r1:"+relation+"]-(m:ApplyInfo) return n.orderno,p.content,m.orderno";
//			String sql="match (n:"+modelname+" {tag:'black'})-[r:"+relation+"]-(p)-[r1:"+relation+"]-(m:ApplyInfo)-[r2:"+relation+"]-(q)-[r3:"+relation+"]-(w:ApplyInfo) return n.orderno,r,p.content,r1,m.orderno,r2,q.content,r3,w.orderno";
			String sql="match (n:"+modelname+" {tag:'black'})-[r:"+relation+"]-(p)-[r1:"+relation+"]-(m:ApplyInfo) return n.orderno,r,p.content,r1,m.orderno";
			System.out.println("sql:"+sql);
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			 {
				
				 System.out.println("n.orderno:"+rs.getString("n.orderno"));
				 System.out.println("p.content:"+rs.getString("p.content"));
				 System.out.println("m.orderno:"+rs.getString("m.orderno"));
				
//				 writeDataToTxt(rs.getString("n.orderno")+","+rs.getString("p.content")+","+rs.getString("m.orderno"),filepath);
				 //+","+getPropertyValue(rs.getString("r"),"type")
				 writeDataToTxt(rs.getString("n.orderno")+","+getPropertyValue(rs.getString("r"),"type")+","+rs.getString("p.content")+","+getPropertyValue(rs.getString("r1"),"type")+","+rs.getString("m.orderno"),filepath);
//				 writeDataToTxt(rs.getString("n.orderno")+","+getPropertyValue(rs.getString("r"),"type")+","+rs.getString("p.content")+","+getPropertyValue(rs.getString("r1"),"type")+","+rs.getString("m.orderno")+","+getPropertyValue(rs.getString("r2"),"type")+","+rs.getString("q.content")+","+getPropertyValue(rs.getString("r3"),"type")+","+rs.getString("w.orderno"),filepath);
			 }
			 System.out.println("End export data!");
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
		
	}
	
	public static String getPropertyValue(String jsonStr, String propName) {

		Map<String, String> jsonmap = JSON.parseObject(jsonStr,
				new HashMap<String, String>().getClass());
		String propValue = jsonmap.get(propName);
		return propValue;

	}
	
	/**
	 * 读取的数据写入文件
	 * @param content
	 * @param path
	 */
	private static void writeDataToTxt(String content,String path) {
		FileWriter fw = null;
		try {
			//如果文件存在，则追加内容；如果文件不存在，则创建文件
			
			File f=new File(path);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		pw.flush();
		try {
				fw.flush();
				pw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	

	
}

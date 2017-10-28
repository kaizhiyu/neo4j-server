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
import java.util.List;


public class ExportDataToCSVCallContactLevel2 {

	public static String url;
	private static String username="neo4j";
	private static String password="123456";
	public static void main(String[] args) {
		url="bolt://10.16.65.16:7687";
//		String[] argString=args[0].split(",");
		QueryApplyByApplyLevel1("ApplyInfo","applymymobile|loanapply|emergencymobile|device|bankcard|creditcard|identification|email|IMEI","/home/graphdb/batchimporthandle/apply/applyexportdata/exportmobilelevel2/mobilerelationlevel2.csv");
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
//		applymymobile|loanapply|device|bankcard|creditcard|identification|email|terminal|IMEI|LBS|company|ipv4|companyaddress|companytel

		
		//System.out.println(relationstr.toString());
		try
		{
			con = DriverManager.getConnection("jdbc:neo4j:"+url,username,password);
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			rs = stmt.executeQuery("match (p:Mobile {type:'1'})-[r:call|contact]-(n:Mobile)-[r1:"+relation+"]-(m:ApplyInfo) return p.content,n.content,m.orderno");
			 while(rs.next())
			 {
				
				 System.out.println("n.content:"+rs.getString("n.content"));
				 System.out.println("p.content:"+rs.getString("p.content"));
				 System.out.println("m.orderno:"+rs.getString("m.orderno"));
				 writeDataToTxt(rs.getString("n.content")+","+rs.getString("p.content")+","+rs.getString("m.orderno"),filepath);

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

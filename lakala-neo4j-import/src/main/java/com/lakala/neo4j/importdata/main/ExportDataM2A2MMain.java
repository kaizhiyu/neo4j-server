package com.lakala.neo4j.importdata.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 黑名单手机号到进件到手机号
 * @author jack.guo
 *
 */

public class ExportDataM2A2MMain {

	public static String url;
	private static String username="neo4j";
	private static String password="123456";
	public static void main(String[] args) {
//		System.out.println("args:"+args[0]);
//		String[] argString=args[0].split(",");
		String ip="10.16.65.15";
		url="bolt://"+ip+":7687";
		System.out.println("url:"+url);
		String filePath="/home/graphdb/batchimporthandle/apply/applyexportdata/exportdatablack2apply2mobile/data.csv";
		System.out.println("ExportFilePath:"+filePath);
		File file=new File(filePath);
		if(!file.exists())    
		{    
		    try {    
		        file.createNewFile();    
		    } catch (IOException e) {    
		        // TODO Auto-generated catch block    
		        e.printStackTrace();    
		    }    
		} 
		QueryApplyByApplyLevel1(filePath);
	}
	
	
	public static void QueryApplyByApplyLevel1(String filepath) {
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
//			 System.out.println("pathfile:"+filepath);
			String sql="match(n:Mobile {type:'1'})-[r:applymymobile|loanapply|emergencymobile]-(m:ApplyInfo)-[r1:applymymobile|loanapply|emergencymobile]-(p:Mobile) return n.content,m.orderno,p.content";
			
			System.out.println("sql:"+sql);
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			 {
				
				 System.out.println("n.content:"+rs.getString("n.content"));
				
				 System.out.println("m.orderno:"+rs.getString("m.orderno"));
				 
				 System.out.println("p.content:"+rs.getString("p.content"));
				
				 writeDataToTxt(rs.getString("n.content")+","+rs.getString("m.orderno")+","+rs.getString("p.content"),filepath);

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

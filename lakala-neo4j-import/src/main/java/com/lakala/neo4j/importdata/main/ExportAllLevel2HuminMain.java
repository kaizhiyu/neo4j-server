package com.lakala.neo4j.importdata.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.lakala.neo4j.importdata.constant.DoTagOrders;

public class ExportAllLevel2HuminMain {

	public static void main(String[] args) {
		exportLevel2();

	}

	public static boolean exportLevel2() {
		List<String> verterModels = new ArrayList<String>();
		verterModels.add("Mobile");
		verterModels.add("Email");
		verterModels.add("BankCard");
		verterModels.add("Identification");
		verterModels.add("Device");
		Map<String, String> relationToModelMap = new HashMap<String, String>();

		relationToModelMap.put("applymymobile", "Mobile");
		relationToModelMap.put("loanapply", "Mobile");
		relationToModelMap.put("emergencymobile", "Mobile");
		relationToModelMap.put("device", "Device");
		relationToModelMap.put("email", "Email");
		relationToModelMap.put("bankcard", "BankCard");
		relationToModelMap.put("identification", "Identification");
		List<String[]> relationCertnos=TraversalRelationParams("identification",relationToModelMap);
		Connection con = null;
		Statement stmt = null;
		try {
			
			con = DriverManager.getConnection(
					"jdbc:neo4j:bolt://10.16.65.15:7687", "neo4j", "123456");
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			for (String content: DoTagOrders.CertNoList) {
				for(String[] t:relationCertnos){
					getBlackListLevel2(con,stmt,content,relationToModelMap, t[0], t[1], t[2], t[3], t[4]);
				}
				
			}
			
			List<String[]> relationMyMobiles=TraversalRelationParams("applymymobile",relationToModelMap);
			for (String content: DoTagOrders.MobileList) {
				for(String[] t:relationMyMobiles){
					getBlackListLevel2(con,stmt,content,relationToModelMap, t[0], t[1], t[2], t[3], t[4]);
				}
			}
			
			List<String[]> relationloanapplys=TraversalRelationParams("loanapply",relationToModelMap);
			
			for (String content: DoTagOrders.MobileList) {

				for(String[] t:relationloanapplys){
					getBlackListLevel2(con,stmt,content,relationToModelMap, t[0], t[1], t[2], t[3], t[4]);
				}
			}
			
			List<String[]> relationemergencymobiles=TraversalRelationParams("emergencymobile",relationToModelMap);
			
			for (String content: DoTagOrders.MobileList) {
				for(String[] t:relationemergencymobiles){
					getBlackListLevel2(con,stmt,content,relationToModelMap, t[0], t[1], t[2], t[3], t[4]);
				}
			}
			
			List<String[]> relationemails=TraversalRelationParams("email",relationToModelMap);
			for (String content: DoTagOrders.EmailList) {
			
				for(String[] t:relationemails){
					getBlackListLevel2(con,stmt,content,relationToModelMap, t[0], t[1], t[2], t[3], t[4]);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		finally{
			try{
				    if(stmt!=null){
				    	stmt.close();
				    }
				    if (con!=null) {
						con.close();
					}
				}catch(Exception ex){
					System.err.println("ExportAllLevel2HuminMain connnetion release fail! Error:"+ex.getMessage());
				}
		}
		return true;
	}
	
	
	/**
	 * 指定第一层关系获取所有关系的排列组合
	 * 
	 * @param relationToModelMap
	 * @return
	 */
	public static List<String[]> TraversalRelationParams(String r1,
			Map<String, String> relationToModelMap) {
		List<String[]> relationTuple = new ArrayList<String[]>();
		List<String[]> level1 = getRelaionTupes(relationToModelMap,r1);
		level1.forEach(t -> {
						List<String[]> level2 = getRelaionTupes(relationToModelMap, t[1]);
						level2.forEach(t2 -> {
							relationTuple.add(new String[] { r1, t[0], t[1],
									t2[0], t2[1] });
						});
					});
		return relationTuple;
	}

	/**
	 * 获取所有关系的排列组合
	 * 
	 * @param relationToModelMap
	 * @return
	 */
	public static List<String[]> TraversalRelationParams(
			Map<String, String> relationToModelMap) {
		List<String[]> relationTuple = new ArrayList<String[]>();
		relationToModelMap.keySet().forEach(r1 -> {// 第一层的关系
					List<String[]> level1 = getRelaionTupes(relationToModelMap,
							r1);
					level1.forEach(t -> {
						List<String[]> level2 = getRelaionTupes(
								relationToModelMap, t[1]);
						level2.forEach(t2 -> {
							relationTuple.add(new String[] { r1, t[0], t[1],
									t2[0], t2[1] });
						});
					});
				});
		return relationTuple;
	}

	/**
	 * 获取进件间单个属性关系的排列组合
	 * 
	 * @param relationToModelMap
	 * @param preRelation
	 */
	public static List<String[]> getRelaionTupes(
			Map<String, String> relationToModelMap, String preRelation) {
		List<String[]> tupleList = new ArrayList<String[]>();
		relationToModelMap
				.keySet()
				.forEach(
						r -> {
							if (!preRelation.equals(r)) {
								if (r.equals("applymymobile")
										|| r.equals("loanapply")
										|| r.equals("emergencymobile")) {
									tupleList.add(new String[] { r,
											"applymymobile" });
									tupleList
											.add(new String[] { r, "loanapply" });
									tupleList.add(new String[] { r,
											"emergencymobile" });
								} else {
									tupleList.add(new String[] { r, r });
								}
							}
						});
		return tupleList;

	}

	/**
	 * 通过属性实体找到三度关联的进件
	 * 
	 * @param relationToModelMap
	 * @param r1
	 * @param r2
	 * @param r3
	 * @param r4
	 * @param r5
	 * @return
	 */
	public static boolean getBlackListLevel2(Connection con,Statement stmt,Map<String, String> relationToModelMap, String r1, String r2,
			String r3, String r4, String r5) {

		ResultSet rs = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			con = DriverManager.getConnection(
					"jdbc:neo4j:bolt://10.16.65.15:7687", "neo4j", "123456");
			stmt = con.createStatement();
			stmt.setQueryTimeout(120);
			stringBuilder.append("match(p1:" + relationToModelMap.get(r1)
					+ " {type:'1'})");
			stringBuilder.append("-[r1:" + r1 + "]-");
			stringBuilder.append("(a1:ApplyInfo)");
			stringBuilder.append("-[r2:" + r2 + "]-");
			stringBuilder.append("(p2:" + relationToModelMap.get(r2) + ")");
			stringBuilder.append("-[r3:" + r3 + "]-");
			stringBuilder.append("(a2:ApplyInfo)");
			stringBuilder.append("-[r4:" + r4 + "]-");
			stringBuilder.append("(p3:" + relationToModelMap.get(r4) + ")");
			stringBuilder.append("-[r5:" + r5 + "]-");
			stringBuilder.append("(a3:ApplyInfo)");
			stringBuilder.append(" return p1.content,r1,a1.orderno,r2,p2.content,r3,a2.orderno,r4,p3.content,r5,a3.orderno");

			String sql = stringBuilder.toString();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);

			String p1data = null;
			String r1data = null;
			String a1data = null;
			String r2data = null;
			String p2data = null;
			String r3data = null;
			String a2data = null;
			String r4data = null;
			String p3data = null;
			String r5data = null;
			String a3data = null;

			while (rs.next()) {
				try {
					p1data = rs.getString("p1.content");
					r1data = getPropertyValue(rs.getString("r1"), "type");
					a1data = rs.getString("a1.orderno");
					r2data = getPropertyValue(rs.getString("r2"), "type");
					p2data = rs.getString("p2.content");
					r3data = getPropertyValue(rs.getString("r3"), "type");
					a2data = rs.getString("a2.orderno");
					r4data = getPropertyValue(rs.getString("r4"), "type");
					p3data = rs.getString("p3.content");
					r5data = getPropertyValue(rs.getString("r5"), "type");
					a3data = rs.getString("a3.orderno");
					writeDataToTxt(p1data + "," + r1data + "," + a1data + ","
							+ r2data + "," + p2data + "," + r3data + "," + a2data
							+ "," + r4data + "," + p3data + "," + r5data + ","
							+ a3data, "/home/graphdb/batchimporthandle/apply/exportalllevel2/alllevel2.csv");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
			}

		} catch (Exception e) {
			System.err.println("batchInsert Error:" + e.getMessage());
		} finally {
			try {
				if(rs!=null){
			    	rs.close();
			    }
			} catch (Exception e) {

				System.err.println("batchInsert connnetion release fail! Error:"+ e.getMessage());
			}
		}
		return true;
	}
	
	
	
	
	//getBlackListLevel2(content,relationToModelMap, t[0], t[1], t[2], t[3], t[4]);
	
	
	/**
	 * 通过属性实体找到三度关联的进件
	 * 
	 * @param relationToModelMap
	 * @param r1
	 * @param r2
	 * @param r3
	 * @param r4
	 * @param r5
	 * @return
	 */
	public static boolean getBlackListLevel2(Connection conn,Statement stmt,String content,Map<String, String> relationToModelMap, String r1, String r2,
			String r3, String r4, String r5) {

		ResultSet rs = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			
			stringBuilder.append("match(p1:" + relationToModelMap.get(r1)
					+ " {content:'"+content+"'})");
			stringBuilder.append("-[r1:" + r1 + "]-");
			stringBuilder.append("(a1:ApplyInfo)");
			stringBuilder.append("-[r2:" + r2 + "]-");
			stringBuilder.append("(p2:" + relationToModelMap.get(r2) + ")");
			stringBuilder.append("-[r3:" + r3 + "]-");
			stringBuilder.append("(a2:ApplyInfo)");
			stringBuilder.append("-[r4:" + r4 + "]-");
			stringBuilder.append("(p3:" + relationToModelMap.get(r4) + ")");
			stringBuilder.append("-[r5:" + r5 + "]-");
			stringBuilder.append("(a3:ApplyInfo)");
			stringBuilder.append(" return p1.content,r1,a1.orderno,r2,p2.content,r3,a2.orderno,r4,p3.content,r5,a3.orderno");

			String sql = stringBuilder.toString();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);

			String p1data = null;
			String r1data = null;
			String a1data = null;
			String r2data = null;
			String p2data = null;
			String r3data = null;
			String a2data = null;
			String r4data = null;
			String p3data = null;
			String r5data = null;
			String a3data = null;

			while (rs.next()) {
				try {
					p1data = rs.getString("p1.content");
					r1data = getPropertyValue(rs.getString("r1"), "type");
					a1data = rs.getString("a1.orderno");
					r2data = getPropertyValue(rs.getString("r2"), "type");
					p2data = rs.getString("p2.content");
					r3data = getPropertyValue(rs.getString("r3"), "type");
					a2data = rs.getString("a2.orderno");
					r4data = getPropertyValue(rs.getString("r4"), "type");
					p3data = rs.getString("p3.content");
					r5data = getPropertyValue(rs.getString("r5"), "type");
					a3data = rs.getString("a3.orderno");
					writeDataToTxt(p1data + "," + r1data + "," + a1data + ","
							+ r2data + "," + p2data + "," + r3data + "," + a2data
							+ "," + r4data + "," + p3data + "," + r5data + ","
							+ a3data, "/home/graphdb/batchimporthandle/apply/exportalllevel2/alllevel2.csv");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
			}

		} catch (Exception e) {
			System.err.println("batchInsert Error:" + e.getMessage());
		} finally {
			try {
				if(rs!=null){
			    	rs.close();
			    }
			} catch (Exception e2) {
				System.err.println(e2.getMessage());
			}
			
		}
		return true;
	}

	public static String getPropertyValue(String jsonStr, String propName) {

		Map<String, String> jsonmap = JSON.parseObject(jsonStr,
				new HashMap<String, String>().getClass());
		String propValue = jsonmap.get(propName);
		return propValue;

	}

	/**
	 * 读取的数据写入文件
	 * 
	 * @param content
	 * @param path
	 * @throws IOException
	 */
	public static void writeDataToTxt(String content, String path) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件

			File f = new File(path);
			fw = new FileWriter(f, true);
			pw = new PrintWriter(fw);
			pw.println(content);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception e2) {
					System.out.println("writeDataToTxt finally:"+ e2.getMessage());
				}

			}

		}
	}

}

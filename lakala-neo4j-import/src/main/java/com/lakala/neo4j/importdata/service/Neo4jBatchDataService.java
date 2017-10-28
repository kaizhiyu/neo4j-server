package com.lakala.neo4j.importdata.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import com.alibaba.fastjson.JSON;
import com.lakala.neo4j.importdata.constant.MajorCompany;
import com.lakala.neo4j.importdata.constant.ModelType;
import com.lakala.neo4j.importdata.constant.RelationType;
import com.lakala.neo4j.importdata.dao.Neo4jHelperDao;


public class Neo4jBatchDataService {
	/**
	 * 将文件中的"\"替换成""
	 * @param inputfile 输入文件的路径
	 * @param outputfile 输出文件的路径
	 * @param tableHeader 表结果字符串
	 */
	public static void handFile(String type,List<String> paths) {
//		File file = new File("C:\\Users\\jack.guo\\Desktop\\databasd\\callhistory_data.1474473734365");//Text文件
//		File file = new File("C:\\Users\\jack.guo\\Desktop\\databasd\\contactlist");
//		writeDataToTxt(tableHeader,outputfile);
		
		if(type.equals("call")){
			handleCall(paths);
		}else if (type.equals("contact")) {
			handleContact(paths);
		}else if (type.equals("apply")) {
			handleApply(paths);
		}
	}
	
	
	/**
	 * 将通讯录和通话记录的数据分割符替换
	 * @param paths
	 * @param outputfile
	 */
	private static void handleCall(List<String> paths) {
		
		try {
			long t1=System.currentTimeMillis();
			paths.parallelStream().forEach(path->{
				BufferedReader br=null;
				try {
					br = new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件
					String str = null;
					StringBuilder sql=new StringBuilder();
					long i=0;
					long rowindex=0;
					while((str = br.readLine())!=null){
						try {
							rowindex++;
							i++;
							//"type,appversion,devicemodel ,deviceid,loginname,nickname,caller_phone ,duration,date,collecttime,platform,osversion";
							String[] strArray=str.replace("\"", "").replace("\\", "").split("\001");
		//					writeDataToTxt(lineStr,outputfile);
							if(strArray[4].trim().length()==11){
								sql.append("MERGE (p"+i+":Mobile {content:'"+strArray[4]+"'}) set p"+i+".modelname='Mobile',p"+i+".content='"+strArray[4]+"',p"+i+".type='"+strArray[0]+"',p"+i+".appversion='"+strArray[1]+"',p"+i+".devicemodel ='"+strArray[2]+"',p"+i+".deviceid='"+strArray[3]+"',p"+i+".nickname='"+strArray[5]+"',p"+i+".duration='"+strArray[7]+"',p"+i+".date='"+strArray[8]+"',p"+i+".collecttime='"+strArray[9]+"',p"+i+".platform='"+strArray[10]+"',p"+i+".osversion='"+strArray[11]+"',p"+i+".group='2'");
								sql.append("\n");
								sql.append("MERGE (q"+i+":Mobile {content:'"+strArray[6]+"'}) set q"+i+".modelname='Mobile',q"+i+".content='"+strArray[6]+"' ,q"+i+".group='2' MERGE (p"+i+")-[:call]->(q"+i+")");
								sql.append("\n");
								
								if(i==10){
									System.out.println("rowindex:"+rowindex+"! Start import data! end mobile:"+strArray[4]);
									System.out.println("call import data!");
									Neo4jHelperDao.executeInsert(sql.toString());
									System.out.println("import data success!");
									sql=new StringBuilder();
									i=0;
								}
							}
						} catch (Exception e) {
							System.err.println("handleCall Executing sql:"+sql.toString()+"  Executing error:"+e.getMessage());
							sql=new StringBuilder();
							i=0;
						}
					}
					
					
					if (!sql.toString().equals("")) {
						Neo4jHelperDao.executeInsert(sql.toString());
					}
					System.out.println("handleCall cost milliseconds time:"+(System.currentTimeMillis()- t1)+"ms");
				} catch (Exception e) {
					System.err.println("handleCall Executing error:"+e.getMessage());
					
				}
				finally{
					if(br!=null){
						try {
							br.close();
						} catch (Exception e2) {
							System.err.println("handleCall handle error:"+e2.getMessage());
						}
						
					}
				}
			});
		} catch (Exception e) {
			System.err.println("handleCall error:"+e.getMessage());
		}
		
	}
	
	/**
	 * 将通讯录和通话记录的数据分割符替换
	 * @param paths
	 * @param outputfile
	 */
	private static void handleContact(List<String> paths) {
		try {
			
			paths.parallelStream().forEach(path->{
				System.out.println(Thread.currentThread().getName()+":start read file:"+path);
					BufferedReader br=null;//构造一个BufferedReader类来读取文件
					try {
					br = new BufferedReader(new FileReader(path));
					String str = null;
					StringBuilder sql=new StringBuilder();
					long rowcount=0L;
					int i=0;
					String[] strArray=null;
					while((str = br.readLine())!=null){
						try {
							i++;
							rowcount++;
//							System.out.println("start rowidex:"+rowcount);
							strArray=str.replace("\"", "").replace("\\", "").split("\001");	
							sql.append("MERGE (p"+i+":Mobile {content:'"+strArray[0]+"'}) set p"+i+".modelname='Mobile',p"+i+".content='"+strArray[0]+"',p"+i+".deviceid='"+strArray[1]+"',p"+i+".name='"+strArray[2]+"',p"+i+".collecttime='"+strArray[4]+"',p"+i+".group='2'");
							sql.append("\n");
							sql.append("MERGE (q"+i+":Mobile {content:'"+strArray[3]+"'}) set q"+i+".modelname='Mobile',q"+i+".content='"+strArray[3]+"' ,q"+i+".group='2' MERGE (p"+i+")-[:contact]->(q"+i+")");
							sql.append("\n");
							
							if(i==10){
								System.out.println("rowcount:"+rowcount+"  Start import data! mobile:"+strArray[0]);
								Neo4jHelperDao.executeInsert(sql.toString());
								System.out.println("import data success!");
								sql=new StringBuilder();
								i=0;
							}
							
						} catch (Exception e) {
							System.err.println("handleContact Executing sql:"+sql.toString()+"  Executing error:"+e.getMessage());
							sql=new StringBuilder();
							i=0;
						}
					}
					if (!sql.toString().equals("")) {
						Neo4jHelperDao.executeInsert(sql.toString());
					}
				} catch (Exception e) {
					System.err.println("handleContact error:"+e.getMessage());
				}
				finally{
					if(br!=null){
						try {
							br.close();
						} catch (Exception e2) {
							System.err.println("handleCall handle error:"+e2.getMessage());
						}
					}
				}
			});
		} catch (Exception e) {
			System.err.println("handleContact error:"+e.getMessage());
		}
	}
	
	/**
	 * 读取进件的数据并进行格式转换
	 * @param paths
	 * @param outputfile
	 */
	private static void handleApply(List<String> paths) {
		//读取大单位信息
		List<String> majorList=MajorCompany.getMajorCompany();
//		List<String> paths=new ArrayList<String>();
		try {
			
			long t1=System.currentTimeMillis();
			paths.parallelStream().forEach(path->{
				System.out.println("read path:"+path);
				BufferedReader br=null;
				try {
					br= new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件
					String str = null;
					while((str = br.readLine())!=null){
						try {
							Map<String, Object> map = JSON.parseObject(str, (new HashMap<String, Object>()).getClass());
							if(!majorList.contains(map.get("companyname"))){
								updateApplyInfo(map,false);
							}else {
								updateApplyInfo(map,true);
							}
						} catch (Exception e) {
						     System.err.println("json格式错误："+str+"     error:"+e.getMessage());
						}
					}
					
				} catch (Exception e) {
					System.err.println("handleApply error:"+e.getMessage());
				}
				finally{
					if(br!=null){
						try {
							br.close();
						} catch (Exception e) {
							System.out.println("finally exception:");
						}
						
					}
				}
			});
			System.out.println("handleAppy cost milliseconds time:"+(System.currentTimeMillis()- t1)+"ms");
		} catch (Exception e) {
			System.err.println("handleApply error:"+e.getMessage());
		}
	}
	

	/**
	 * 
	 * 功能描述
	 * 创建手机号实体
	 * @author guozhijie 2017年03月23日 上午11:10:28
	 * @since 1.0.0.000
	 * @param mobile
	 * @param set
	 * @param _deviceid
	 * @return
	 */
	private static void updateApplyInfo(Map<String,Object> maps,boolean ismajorcompany){
		
		StringBuilder sbApply=new StringBuilder();
		//MERGE (p)-[r:"+RelationType.LOANAPPLY+"]->(m)
		StringBuilder sbMobileAndRelation=new StringBuilder();
		sbApply.append("merge (m:"+ModelType.APPLYINFO.getModelName()+" {orderno:'"+maps.get("orderno")+"'}) set m.modelname='"+ModelType.APPLYINFO.getModelName()+"',m.orderno='"+maps.get("orderno")+"',m.group='"+ModelType.APPLYINFO.getEntityGroup()+"',");
//		 List<String> sqlList=new ArrayList<String>();
		
		String keyString=null;
		String valueString=null;
		for(Map.Entry<String, Object> map:maps.entrySet()){
		
			keyString=map.getKey();
			valueString=(map.getValue()==null?null:map.getValue().toString());
			switch (keyString) {
			case "mobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (a:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set a.modelname='"+ModelType.MOBILE.getModelName()+"',a.content='"+valueString+"',a.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.APPLYMYMOBILE.getRelationType()+"]->(a)");
				}
				break;
			case "emergencymobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (b:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set b.modelname='"+ModelType.MOBILE.getModelName()+"',b.content='"+valueString+"',b.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(b)");
				}
				break;
			case "hometel":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (c:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set c.modelname='"+ModelType.MOBILE.getModelName()+"',c.content='"+valueString+"',c.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(c)");

				}
				break;
			case "contactmobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (d:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set d.modelname='"+ModelType.MOBILE.getModelName()+"',d.content='"+valueString+"',d.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(d)");

				}
				break;
			case "usercoremobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (e:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set e.modelname='"+ModelType.MOBILE.getModelName()+"',e.content='"+valueString+"',e.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(e)");

				}
				break;
			case "channelmobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (f:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set f.modelname='"+ModelType.MOBILE.getModelName()+"',f.content='"+valueString+"',f.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(f)");

				}
				break;
			case "partnercontantmobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (g:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set g.modelname='"+ModelType.MOBILE.getModelName()+"',g.content='"+valueString+"',g.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(g)");

				}
				break;
			case "merchantmobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (h:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set h.modelname='"+ModelType.MOBILE.getModelName()+"',h.content='"+valueString+"',h.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(h)");
				}
				break;
			case "debitcard":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (i:"+ModelType.BANKCARD.getModelName()+" {content:'"+valueString+"'}) set i.modelname='"+ModelType.BANKCARD.getModelName()+"',i.content='"+valueString+"',i.group='"+ModelType.BANKCARD.getEntityGroup()+"' merge (m)-[:"+RelationType.BANKCARD.getRelationType()+"]->(i)");

				}
				break;
			case "certno":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (j:"+ModelType.IDENTIFICATION.getModelName()+" {content:'"+valueString+"'}) set j.modelname='"+ModelType.IDENTIFICATION.getModelName()+"',j.content='"+valueString+"',j.group='"+ModelType.IDENTIFICATION.getEntityGroup()+"' merge (m)-[:"+RelationType.IDENTIFICATION.getRelationType()+"]->(j)");

				}
				break;

			case "creditcard":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (k:"+ModelType.BANKCARD.getModelName()+" {content:'"+valueString+"'}) set k.modelname='"+ModelType.BANKCARD.getModelName()+"',k.content='"+valueString+"',k.group='"+ModelType.BANKCARD.getEntityGroup()+"' merge (m)-[:"+RelationType.CREDITCARD.getRelationType()+"]->(k)");

				}
				break;
			case "_deviceid":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (l:"+ModelType.DEVICE.getModelName()+" {content:'"+valueString+"'}) set l.modelname='"+ModelType.DEVICE.getModelName()+"',l.content='"+valueString+"',l.group='"+ModelType.DEVICE.getEntityGroup()+"' merge (m)-[:"+RelationType.DEVICE.getRelationType()+"]->(l)");
				}
				break;

			case "ipv4":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (n:"+ModelType.IPV4.getModelName()+" {content:'"+valueString+"'}) set n.modelname='"+ModelType.IPV4.getModelName()+"',n.content='"+valueString+"',n.group='"+ModelType.IPV4.getEntityGroup()+"' merge (m)-[:"+RelationType.IPV4.getRelationType()+"]->(n)");

				}
				break;
			case "lbs":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m.lbsx='"+(maps.get("lbsx")==null?null:maps.get("lbsx").toString())+"',m.lbsy='"+(maps.get("lbsy")==null?null:maps.get("lbsy").toString())+"',m.lbsanalyzed='"+(maps.get("lbsanalyzed")==null?null:maps.get("lbsanalyzed").toString())+"',");
					sbMobileAndRelation.append(" merge (o:"+ModelType.LBS.getModelName()+" {content:'"+maps.get("lbsx").toString()+"_"+maps.get("lbsy").toString()+"_"+(maps.get("lbsanalyzed")==null?"":maps.get("lbsanalyzed").toString())+"'}) set o.modelname='"+ModelType.LBS.getModelName()+"',o.content='"+maps.get("lbsx").toString()+"_"+maps.get("lbsy").toString()+"_"+(maps.get("lbsanalyzed")==null?"":maps.get("lbsanalyzed").toString())+"',"+"o.lbsx='"+(maps.get("lbsx")==null?null:maps.get("lbsx").toString())+"',o.lbsy='"+(maps.get("lbsy")==null?null:maps.get("lbsy").toString())+"',o.lbsanalyzed='"+(maps.get("lbsanalyzed")==null?"":maps.get("lbsanalyzed").toString())+"'"+",o.group='"+ModelType.LBS.getEntityGroup()+"' merge (m)-[:"+RelationType.LBS.getRelationType()+"]->(o)");

				}
				break;
			case "companyname":
				if(valueString!=null&&!"".equals(valueString))
				{
					if(!ismajorcompany){
						sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
						sbMobileAndRelation.append(" merge (p:"+ModelType.COMPANY.getModelName()+" {content:'"+valueString+"'}) set p.modelname='"+ModelType.COMPANY.getModelName()+"', p.content='"+valueString+"',p.group='"+ModelType.COMPANY.getEntityGroup()+"' merge (m)-[:"+RelationType.COMPANY.getRelationType()+"]->(p)");
					}else {
						sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
						sbMobileAndRelation.append(" merge (p:"+ModelType.COMPANY.getModelName()+" {content:'"+valueString+"'}) set p.modelname='"+ModelType.COMPANY.getModelName()+"', p.content='"+valueString+"',p.group='"+ModelType.COMPANY.getEntityGroup()+"' merge (m)-[:"+RelationType.MAJORCOMPANY.getRelationType()+"]->(p)");
					}
				}
				break;
			case "companyaddress":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (q:"+ModelType.COMPANYADDRESS.getModelName()+" {content:'"+valueString+"'}) set q.modelname='"+ModelType.COMPANYADDRESS.getModelName()+"',q.content='"+valueString+"',q.group='"+ModelType.COMPANYADDRESS.getEntityGroup()+"' merge (m)-[:"+RelationType.COMPANYADDRESS.getRelationType()+"]->(q)");
				}
				break;
			case "companytel":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString()).replace("|", "")+"',");
					sbMobileAndRelation.append(" merge (s:"+ModelType.COMPANYTEL.getModelName()+" {content:'"+valueString.replace("|", "")+"'}) set s.modelname='"+ModelType.COMPANYTEL.getModelName()+"',s.content='"+valueString.replace("|", "")+"',s.group='"+ModelType.COMPANYTEL.getEntityGroup()+"' merge (m)-[:"+RelationType.COMPANYTEL.getRelationType()+"]->(s)");
				}
				break;	
				
			case "imei":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (t:"+ModelType.MOBILEIMEI.getModelName()+" {content:'"+valueString+"'}) set t.modelname='"+ModelType.MOBILEIMEI.getModelName()+"',t.content='"+valueString+"',t.group='"+ModelType.MOBILEIMEI.getEntityGroup()+"' merge (m)-[:"+RelationType.MOBILEIMEI.getRelationType()+"]->(t)");

				}
				break;
			case "email":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (u:"+ModelType.EMAIL.getModelName()+" {content:'"+valueString+"'}) set u.modelname='"+ModelType.EMAIL.getModelName()+"',u.content='"+valueString+"',u.group='"+ModelType.EMAIL.getEntityGroup()+"' merge (m)-[:"+RelationType.EMAIL.getRelationType()+"]->(u)");

				}
				break;
			case "termid":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (v:"+ModelType.TERMINAL.getModelName()+" {content:'"+valueString+"'}) set v.modelname='"+ModelType.TERMINAL.getModelName()+"',v.content='"+valueString+"',v.group='"+ModelType.TERMINAL.getEntityGroup()+"' merge (m)-[:"+RelationType.TERMINAL.getRelationType()+"]->(v)");

				}
				break;
			default:
				break;
			}
		}
		String applysql=sbApply.toString().substring(0, sbApply.length()-1);//将最后一个逗号去掉
		StringBuilder sbBuilder=new StringBuilder();
		sbBuilder.append(applysql);
		sbBuilder.append(sbMobileAndRelation);
		System.out.println("Start import data!");
		System.out.println("apply import data!");
		try {
			Neo4jHelperDao.executeInsert(sbBuilder.toString());
		} catch (Exception e) {
			System.err.println("Execute fail sql:"+sbBuilder.toString()+"   error:"+e.getMessage());
		}
		System.out.println("import data success!");
	}

}

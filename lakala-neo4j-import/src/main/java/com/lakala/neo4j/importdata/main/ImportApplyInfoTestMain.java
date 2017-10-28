package com.lakala.neo4j.importdata.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.lakala.neo4j.importdata.constant.MajorCompany;
import com.lakala.neo4j.importdata.constant.ModelType;
import com.lakala.neo4j.importdata.constant.RelationType;
import com.lakala.neo4j.importdata.dao.Neo4jHelperDao;

public class ImportApplyInfoTestMain {

	public static void main(String[] args) {
		Neo4jHelperDao.url="bolt://192.168.0.33:7687";
		handleApply("D:/服务器浏览器下载路径/export.csv");
	}
	
	/**
	 * 读取进件的数据并进行格式转换
	 * @param paths
	 * @param outputfile
	 */
	private static void handleApply(String path) {
		//读取大单位信息
		List<String> majorList=MajorCompany.getMajorCompany();
//		List<String> paths=new ArrayList<String>();
		try {
			StringBuffer sbBuffer=null;
		
				System.out.println("read path:"+path);
				BufferedReader br = new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件
				String str = null;
//				String lineStr="";
				int linecount=0;
				while((str = br.readLine())!=null){
					System.out.println(linecount);
					linecount++;
					int ic=0;
					System.out.println(str);
//					String strsss="["+str.replace("\"\"", "\"").substring(0,1).substring(str.length()-1,1)+"]";
					System.out.println(str);
					try {
						List<Map<String, Object>> arrstrStrings=JSON.parseObject(str, (new ArrayList<Map<String, Object>>().getClass()));

						for (Map<String, Object> map1 : arrstrStrings) {
							try {
//								Map<String, Object> map = JSON.parseObject(strresult, (new HashMap<String, Object>()).getClass());

								if(!majorList.contains(map1.get("companyname"))){
									updateApplyInfo(map1,false);
								}else {
									updateApplyInfo(map1,true);
								}	
							} catch (Exception e) {
							     System.err.println("json格式错误："+str+"     error:"+e.getMessage());
							}
						
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
				
				br.close();
			
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
		
//		Neo4jUtilServiceImpl.executeInsert(sbBuilder.toString());
//		Neo4jUtilServiceImpl.executeInserttx(sbBuilder.toString());
	}

}

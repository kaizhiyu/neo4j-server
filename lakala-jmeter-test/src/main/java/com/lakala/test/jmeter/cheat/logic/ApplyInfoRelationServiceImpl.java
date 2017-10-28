package com.lakala.test.jmeter.cheat.logic;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.lakala.test.jmeter.cheat.constant.ModelType;
import com.lakala.test.jmeter.cheat.constant.RelationType;
import com.lakala.test.jmeter.cheat.repository.Neo4jUtilServiceImpl;
import com.lakala.test.jmeter.cheat.utils.IdCardGenerator;
import com.lakala.test.jmeter.cheat.utils.RadomGenerateData;
import com.lakala.test.jmeter.cheat.utils.RandomCreditCardNumberGenerator;



public class ApplyInfoRelationServiceImpl{
	final static Logger logger = LoggerFactory.getLogger(ApplyInfoRelationServiceImpl.class);


	private String isInsert="";
	public ApplyInfoRelationServiceImpl(String isInsert){
		this.isInsert=isInsert;
	}
	
	public boolean saveNeo4j() {
		logger.info("进入neo4j操作类saveNeo4j");

	try {
		
			Map<String, String> maps=new HashMap<String, String>();
			Set<String> mobileSet = new HashSet<String>();
			String mobile=RadomGenerateData.getPhone();
			maps.put("mobile",mobile);
			
			
			maps.put("orderno",RadomGenerateData.getOrderIdByUUId());
			maps.put("emergencymobile",RadomGenerateData.getPhone());
			maps.put("hometel",RadomGenerateData.getTelphone());
			maps.put("contactmobile",RadomGenerateData.getPhone());
			maps.put("usercoremobile",RadomGenerateData.getPhone());
			maps.put("channelmobile",RadomGenerateData.getPhone());
			maps.put("partnercontantmobile",RadomGenerateData.getPhone());
			maps.put("merchantmobile",RadomGenerateData.getPhone());
			maps.put("debitcard",RandomCreditCardNumberGenerator.get_Bank_account());
			maps.put("certno",IdCardGenerator.generate());
			maps.put("creditcard",IdCardGenerator.generate());
			maps.put("_deviceid",RadomGenerateData.getDeviceId());
			maps.put("ipv4",RadomGenerateData.getRandomIp());
			maps.put("lbs",RadomGenerateData.getAddress());
			maps.put("companyname",RadomGenerateData.getChineseName());
			maps.put("companyaddress",RadomGenerateData.getAddress());
			maps.put("companytel",RadomGenerateData.getTelphone());
			maps.put("imei",RadomGenerateData.getImei());
			maps.put("email",RadomGenerateData.getEmail(6, 9));
//			maps.put("termid");
			if(isInsert.equals("1")){
				create(mobile,maps);
			}
			else {
				update(mobile,maps);
			}
		} catch (Exception e) {			
			logger.error("ApplyInfoRelationServiceImpl error:"+e.getMessage());
		}

		return true;
	}

	/**
	 * 
	 * 功能描述
	 * 创建手机号实体
	 * @author tianhuaxing 2016年12月26日 上午11:10:28
	 * @since 1.0.0.000
	 * @param mobile
	 * @param set
	 * @param _deviceid
	 * @return
	 */
	private void create(String mobile,Map<String,String> maps){
		
		StringBuilder sbApply=new StringBuilder();
		//create (p)-[r:"+RelationType.LOANAPPLY+"]->(m)
		StringBuilder sbMobileAndRelation=new StringBuilder();
		 List<String> sqlList=new ArrayList<String>();
		sbApply.append("create (m:"+ModelType.APPLYINFO.getModelName()+" {orderno:'"+maps.get("orderno")+"'}) set m.modelname='"+ModelType.APPLYINFO.getModelName()+"',m.orderno='"+maps.get("orderno")+"',m.group='"+ModelType.APPLYINFO.getEntityGroup()+"',");
		int count=maps.size();
		int i=0;
		String keyString=null;
		String valueString=null;
		for(Map.Entry<String, String> map:maps.entrySet()){
			i++;
			keyString=map.getKey();
			valueString=(map.getValue()==null?null:map.getValue().toString());
			switch (keyString) {
			case "mobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (a:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set a.modelname='"+ModelType.MOBILE.getModelName()+"',a.content='"+valueString+"',a.group='"+ModelType.MOBILE.getEntityGroup()+"' create (m)-[:"+RelationType.APPLYMYMOBILE.getRelationType()+"]->(a)");
				}
				break;
			case "ecreatencymobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (b:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set b.modelname='"+ModelType.MOBILE.getModelName()+"',b.content='"+valueString+"',b.group='"+ModelType.MOBILE.getEntityGroup()+"' create (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(b)");
				}
				break;
			case "hometel":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (c:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set c.modelname='"+ModelType.MOBILE.getModelName()+"',c.content='"+valueString+"',c.group='"+ModelType.MOBILE.getEntityGroup()+"' create (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(c)");

				}
				break;
			case "contactmobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (d:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set d.modelname='"+ModelType.MOBILE.getModelName()+"',d.content='"+valueString+"',d.group='"+ModelType.MOBILE.getEntityGroup()+"' create (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(d)");

				}
				break;
			case "usercoremobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (e:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set e.modelname='"+ModelType.MOBILE.getModelName()+"',e.content='"+valueString+"',e.group='"+ModelType.MOBILE.getEntityGroup()+"' create (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(e)");

				}
				break;
			case "channelmobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (f:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set f.modelname='"+ModelType.MOBILE.getModelName()+"',f.content='"+valueString+"',f.group='"+ModelType.MOBILE.getEntityGroup()+"' create (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(f)");

				}
				break;
			case "partnercontantmobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (g:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set g.modelname='"+ModelType.MOBILE.getModelName()+"',g.content='"+valueString+"',g.group='"+ModelType.MOBILE.getEntityGroup()+"' create (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(g)");

				}
				break;
			case "merchantmobile":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (h:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set h.modelname='"+ModelType.MOBILE.getModelName()+"',h.content='"+valueString+"',h.group='"+ModelType.MOBILE.getEntityGroup()+"' create (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(h)");

				}
				break;
			case "debitcard":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (i:"+ModelType.BANKCARD.getModelName()+" {content:'"+valueString+"'}) set i.modelname='"+ModelType.BANKCARD.getModelName()+"',i.content='"+valueString+"',i.group='"+ModelType.BANKCARD.getEntityGroup()+"' create (m)-[:"+RelationType.BANKCARD.getRelationType()+"]->(i)");

				}
				break;
			case "certno":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (j:"+ModelType.IDENTIFICATION.getModelName()+" {content:'"+valueString+"'}) set j.modelname='"+ModelType.IDENTIFICATION.getModelName()+"',j.content='"+valueString+"',j.group='"+ModelType.IDENTIFICATION.getEntityGroup()+"' create (m)-[:"+RelationType.IDENTIFICATION.getRelationType()+"]->(j)");

				}
				break;

			case "creditcard":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (k:"+ModelType.BANKCARD.getModelName()+" {content:'"+valueString+"'}) set k.modelname='"+ModelType.BANKCARD.getModelName()+"',k.content='"+valueString+"',k.group='"+ModelType.BANKCARD.getEntityGroup()+"' create (m)-[:"+RelationType.CREDITCARD.getRelationType()+"]->(k)");

				}
				break;
			case "_deviceid":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (l:"+ModelType.DEVICE.getModelName()+" {content:'"+valueString+"'}) set l.modelname='"+ModelType.DEVICE.getModelName()+"',l.content='"+valueString+"',l.group='"+ModelType.DEVICE.getEntityGroup()+"' create (m)-[:"+RelationType.DEVICE.getRelationType()+"]->(l)");
				}
				break;

			case "ipv4":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (n:"+ModelType.IPV4.getModelName()+" {content:'"+valueString+"'}) set n.modelname='"+ModelType.IPV4.getModelName()+"',n.content='"+valueString+"',n.group='"+ModelType.IPV4.getEntityGroup()+"' create (m)-[:"+RelationType.IPV4.getRelationType()+"]->(n)");

				}
				break;
			case "lbs":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (o:"+ModelType.LBS.getModelName()+" {content:'"+valueString+"'}) set o.modelname='"+ModelType.LBS.getModelName()+"',o.content='"+valueString+"',o.group='"+ModelType.LBS.getEntityGroup()+"' create (m)-[:"+RelationType.LBS.getRelationType()+"]->(o)");

				}
				break;
			case "companyname":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (p:"+ModelType.COMPANY.getModelName()+" {content:'"+valueString+"'}) set p.modelname='"+ModelType.COMPANY.getModelName()+"', p.content='"+valueString+"',p.group='"+ModelType.COMPANY.getEntityGroup()+"' create (m)-[:"+RelationType.COMPANY.getRelationType()+"]->(p)");

				}
				break;
			case "companyaddress":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (q:"+ModelType.COMPANYADDRESS.getModelName()+" {content:'"+valueString+"'}) set q.modelname='"+ModelType.COMPANYADDRESS.getModelName()+"',q.content='"+valueString+"',q.group='"+ModelType.COMPANYADDRESS.getEntityGroup()+"' create (m)-[:"+RelationType.COMPANYADDRESS.getRelationType()+"]->(q)");
				}
				break;
			case "companytel":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString()).replace("|", "")+"',");
					sbMobileAndRelation.append(" create (s:"+ModelType.COMPANYTEL.getModelName()+" {content:'"+valueString.replace("|", "")+"'}) set s.modelname='"+ModelType.COMPANYTEL.getModelName()+"',s.content='"+valueString.replace("|", "")+"',s.group='"+ModelType.COMPANYTEL.getEntityGroup()+"' create (m)-[:"+RelationType.COMPANYTEL.getRelationType()+"]->(s)");
				}
				break;	
				
			case "imei":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (t:"+ModelType.MOBILEIMEI.getModelName()+" {content:'"+valueString+"'}) set t.modelname='"+ModelType.MOBILEIMEI.getModelName()+"',t.content='"+valueString+"',t.group='"+ModelType.MOBILEIMEI.getEntityGroup()+"' create (m)-[:"+RelationType.MOBILEIMEI.getRelationType()+"]->(t)");

				}
				break;
			case "email":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (u:"+ModelType.EMAIL.getModelName()+" {content:'"+valueString+"'}) set u.modelname='"+ModelType.EMAIL.getModelName()+"',u.content='"+valueString+"',u.group='"+ModelType.EMAIL.getEntityGroup()+"' create (m)-[:"+RelationType.EMAIL.getRelationType()+"]->(u)");

				}
				break;
			case "termid":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" create (v:"+ModelType.TERMINAL.getModelName()+" {content:'"+valueString+"'}) set v.modelname='"+ModelType.TERMINAL.getModelName()+"',v.content='"+valueString+"',v.group='"+ModelType.TERMINAL.getEntityGroup()+"' create (m)-[:"+RelationType.TERMINAL.getRelationType()+"]->(v)");

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
		sqlList.add(applysql);
		sqlList.add(sbMobileAndRelation.toString());
		Neo4jUtilServiceImpl.batchInsert(sqlList);
//		Neo4jUtilServiceImpl.executeInsert(sbBuilder.toString());
//		Neo4jUtilServiceImpl.executeInserttx(sbBuilder.toString());
	}
	/**
	 * 
	 * 功能描述
	 * 创建手机号实体
	 * @author tianhuaxing 2016年12月26日 上午11:10:28
	 * @since 1.0.0.000
	 * @param mobile
	 * @param set
	 * @param _deviceid
	 * @return
	 */
	private void update(String mobile,Map<String,String> maps){
		
		StringBuilder sbApply=new StringBuilder();
		//MERGE (p)-[r:"+RelationType.LOANAPPLY+"]->(m)
		StringBuilder sbMobileAndRelation=new StringBuilder();
		sbApply.append("merge (m:"+ModelType.APPLYINFO.getModelName()+" {orderno:'"+maps.get("orderno")+"'}) set m.modelname='"+ModelType.APPLYINFO.getModelName()+"',m.orderno='"+maps.get("orderno")+"',m.group='"+ModelType.APPLYINFO.getEntityGroup()+"',");
		 List<String> sqlList=new ArrayList<String>();
		int i=0;
		String keyString=null;
		String valueString=null;
		for(Map.Entry<String, String> map:maps.entrySet()){
			i++;
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
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (o:"+ModelType.LBS.getModelName()+" {content:'"+valueString+"'}) set o.modelname='"+ModelType.LBS.getModelName()+"',o.content='"+valueString+"',o.group='"+ModelType.LBS.getEntityGroup()+"' merge (m)-[:"+RelationType.LBS.getRelationType()+"]->(o)");

				}
				break;
			case "companyname":
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (p:"+ModelType.COMPANY.getModelName()+" {content:'"+valueString+"'}) set p.modelname='"+ModelType.COMPANY.getModelName()+"', p.content='"+valueString+"',p.group='"+ModelType.COMPANY.getEntityGroup()+"' merge (m)-[:"+RelationType.COMPANY.getRelationType()+"]->(p)");

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
		sqlList.add(applysql);
		sqlList.add(sbMobileAndRelation.toString());
		Neo4jUtilServiceImpl.batchInsert(sqlList);
//		Neo4jUtilServiceImpl.executeInsert(sbBuilder.toString());
//		Neo4jUtilServiceImpl.executeInserttx(sbBuilder.toString());
	}
}

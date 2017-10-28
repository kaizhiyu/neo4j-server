package com.lakala.neo4j.importdata.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;
import com.lakala.neo4j.importdata.constant.ModelType;
import com.lakala.neo4j.importdata.constant.RelationType;
import com.lakala.neo4j.importdata.dao.Neo4jHelperDao;
import com.lakala.neo4j.importdata.service.Neo4jBatchDataService;


/**
 * 导入风控已经算好的规则进件数据
 * @author jack.guo
 *
 */
public class ImportApplyByRuleMain {
	private static List<String> listfile=new ArrayList<String>();
/**
 * 输入文件路径（不包括文件名）,neo4j的IP地址
 * @param args
 */
	public static void main(String[] args) {
	
		System.out.println("Start handle data format!");
		System.out.println("input args :"+args[0]);
		//取得输入参数 
		String[] argsarr=args[0].split(",");
		//argsarr中第一个参数是文件路劲 第二个是ip地址
		Neo4jHelperDao.url="bolt://"+argsarr[1]+":7687";
		
		//通过文件路劲读取数据
		updateApplyInfo(argsarr[0]);

	}
	
	
	/**
	 * 递归获取当前文件夹下所有文件
	 * @param path
	 */
	public static void traverseFolder(String path) {
		
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                		listfile.add(file2.getAbsolutePath());
                		System.out.println("文件:" + file2.getAbsolutePath());
                    	
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }


	/**
	 * 将进件数据导入到neo4j
	 * @param paths
	 * @param outputfile
	 */
	private static void updateApplyInfo(String filepath) {
		try {
			traverseFolder(filepath);
			long t1=System.currentTimeMillis();
			listfile.parallelStream().forEach(p->{
				System.out.println("read path:"+p);
				BufferedReader br=null;
				try {
					br = new BufferedReader(new FileReader(p));//构造一个BufferedReader类来读取文件
					String str = null;
					Map<String, String> map=null;
					long idex=0;
					while((str = br.readLine())!=null){
						try {
							idex++;
							map=getApplyMap(str,idex);
							if(map==null) {continue;}
							updateApplyInfos(map);
							
						} catch (Exception e) {
							System.err.println("updateApplyInfo br:"+str+" [err:"+e.getMessage()+"]");
						}
					}
					System.out.println("import success!");
					System.out.println("ImportApplyByRuleMain updateApplyInfo cost milliseconds time:"+(System.currentTimeMillis()- t1)+"ms");
					
				} catch (Exception e) {
					System.err.println("handleApply error:"+e.getMessage());
				}
				finally{
					if(br!=null){
						try {
							br.close();
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
				}
			});
			
			
		} catch (Exception e) {
			System.out.println("ImportApplyByRuleMain updateApplyInfo Executing error:"+e.getMessage());
		}

	}


	

	private static Map<String, String> getApplyMap(String line,Long idex) {
		Map<String, String> map=new HashMap<String, String>();
		String[] arr=line.split("\001");
		if(arr[1]!=null&&!arr[1].equals("")&&!arr[1].equals("null")){
			map.put("orderno",arr[1]); 
			System.out.println("row:"+idex+"  orderno:"+arr[1]);
			}
		else {
			return null;
		}
		if(arr[0]!=null&&!arr[0].equals("")&&!arr[0].equals("null")){
			map.put("id",arr[0]); 
			}
			 
			 if(arr[2]!=null&&!arr[2].equals("")&&!arr[2].equals("null")){
			map.put("contractno",arr[2]); 
			}
			 if(arr[3]!=null&&!arr[3].equals("")&&!arr[3].equals("null")){
			map.put("userid",arr[3]); 
			}
			 if(arr[4]!=null&&!arr[4].equals("")&&!arr[4].equals("null")){
			map.put("businessno",arr[4]); 
			}
			 if(arr[5]!=null&&!arr[5].equals("")&&!arr[5].equals("null")){
			map.put("productno",arr[5]); 
			}
			 if(arr[6]!=null&&!arr[6].equals("")&&!arr[6].equals("null")){
			map.put("orgid",arr[6]); 
			}
			 if(arr[7]!=null&&!arr[7].equals("")&&!arr[7].equals("null")){
			map.put("termid",arr[7]); 
			}
			 if(arr[8]!=null&&!arr[8].equals("")&&!arr[8].equals("null")){
			map.put("tele_code",arr[8]); 
			}
			 if(arr[9]!=null&&!arr[9].equals("")&&!arr[9].equals("null")){
			map.put("loan_bank_code",arr[9]); 
			}
			 if(arr[10]!=null&&!arr[10].equals("")&&!arr[10].equals("null")){
			map.put("loan_pan",arr[10]); 
			}
			 if(arr[11]!=null&&!arr[11].equals("")&&!arr[11].equals("null")){
			map.put("capital_amount",arr[11]); 
			}
			 if(arr[12]!=null&&!arr[12].equals("")&&!arr[12].equals("null")){
			map.put("rate",arr[12]); 
			}
			 if(arr[13]!=null&&!arr[13].equals("")&&!arr[13].equals("null")){
			map.put("loan_date",arr[13]); 
			}
			 if(arr[14]!=null&&!arr[14].equals("")&&!arr[14].equals("null")){
			map.put("return_date",arr[14]); 
			}
			 if(arr[15]!=null&&!arr[15].equals("")&&!arr[15].equals("null")){
			map.put("return_bank_code",arr[15]); 
			}
			 if(arr[16]!=null&&!arr[16].equals("")&&!arr[16].equals("null")){
			map.put("return_pan",arr[16]); 
			}
			 if(arr[17]!=null&&!arr[17].equals("")&&!arr[17].equals("null")){
			map.put("insert_time",arr[17]); 
			}
			 if(arr[18]!=null&&!arr[18].equals("")&&!arr[18].equals("null")){
			map.put("work_flow_id",arr[18]); 
			}
			 if(arr[19]!=null&&!arr[19].equals("")&&!arr[19].equals("null")){
			map.put("flow_step_id",arr[19]); 
			}
			 if(arr[20]!=null&&!arr[20].equals("")&&!arr[20].equals("null")){
			map.put("note",arr[20]); 
			}
			 if(arr[21]!=null&&!arr[21].equals("")&&!arr[21].equals("null")){
			map.put("pan_auth_status",arr[21]); 
			}
			 if(arr[22]!=null&&!arr[22].equals("")&&!arr[22].equals("null")){
			map.put("manual_auth_status",arr[22]); 
			}
			 if(arr[23]!=null&&!arr[23].equals("")&&!arr[23].equals("null")){
			map.put("fail_reason",arr[23]); 
			}
			 if(arr[24]!=null&&!arr[24].equals("")&&!arr[24].equals("null")){
			map.put("status",arr[24]); 
			}
			 if(arr[25]!=null&&!arr[25].equals("")&&!arr[25].equals("null")){
			map.put("update_time",arr[25]); 
			}
			 if(arr[26]!=null&&!arr[26].equals("")&&!arr[26].equals("null")){
			map.put("cert_addr_note",arr[26]); 
			}
			 if(arr[27]!=null&&!arr[27].equals("")&&!arr[27].equals("null")){
			map.put("comp_addr_note",arr[27]); 
			}
			 if(arr[28]!=null&&!arr[28].equals("")&&!arr[28].equals("null")){
			map.put("manual_auth_user",arr[28]); 
			}
			 if(arr[29]!=null&&!arr[29].equals("")&&!arr[29].equals("null")){
			map.put("audit_time",arr[29]); 
			}
			 if(arr[30]!=null&&!arr[30].equals("")&&!arr[30].equals("null")){
			map.put("channel_id",arr[30]); 
			}
			 if(arr[31]!=null&&!arr[31].equals("")&&!arr[31].equals("null")){
			map.put("supply_status",arr[31]); 
			}
			 if(arr[32]!=null&&!arr[32].equals("")&&!arr[32].equals("null")){
			map.put("contract_file",arr[32]); 
			}
			 if(arr[33]!=null&&!arr[33].equals("")&&!arr[33].equals("null")){
			map.put("apply_amount",arr[33]); 
			}
			 if(arr[34]!=null&&!arr[34].equals("")&&!arr[34].equals("null")){
			map.put("loan_score",arr[34]); 
			}
			 if(arr[35]!=null&&!arr[35].equals("")&&!arr[35].equals("null")){
			map.put("inputer",arr[35]); 
			}
			 if(arr[36]!=null&&!arr[36].equals("")&&!arr[36].equals("null")){
			map.put("credit_amount",arr[36]); 
			}
			 if(arr[37]!=null&&!arr[37].equals("")&&!arr[37].equals("null")){
			map.put("recommend",arr[37]); 
			}
			 if(arr[38]!=null&&!arr[38].equals("")&&!arr[38].equals("null")){
			map.put("gift_amount",arr[38]); 
			}
			 if(arr[39]!=null&&!arr[39].equals("")&&!arr[39].equals("null")){
			map.put("empno",arr[39]); 
			}
			 if(arr[40]!=null&&!arr[40].equals("")&&!arr[40].equals("null")){
			map.put("empname",arr[40]); 
			}
			 if(arr[41]!=null&&!arr[41].equals("")&&!arr[41].equals("null")){
			map.put("empmobile",arr[41]); 
			}
			 if(arr[42]!=null&&!arr[42].equals("")&&!arr[42].equals("null")){
			map.put("mpcr_codes",arr[42]); 
			}
			 if(arr[43]!=null&&!arr[43].equals("")&&!arr[43].equals("null")){
			map.put("mpcr_names",arr[43]); 
			}
			 if(arr[44]!=null&&!arr[44].equals("")&&!arr[44].equals("null")){
			map.put("branchcomp",arr[44]); 
			}
			 if(arr[45]!=null&&!arr[45].equals("")&&!arr[45].equals("null")){
			map.put("authname",arr[45]); 
			}
			 if(arr[46]!=null&&!arr[46].equals("")&&!arr[46].equals("null")){
			map.put("authpwd",arr[46]); 
			}
			 if(arr[47]!=null&&!arr[47].equals("")&&!arr[47].equals("null")){
			map.put("identitycode",arr[47]); 
			}
			 if(arr[48]!=null&&!arr[48].equals("")&&!arr[48].equals("null")){
			map.put("manageradvise",arr[48]); 
			}
			 if(arr[49]!=null&&!arr[49].equals("")&&!arr[49].equals("null")){
			map.put("account_manager",arr[49]); 
			}
			 if(arr[50]!=null&&!arr[50].equals("")&&!arr[50].equals("null")){
			map.put("urge_status",arr[50]); 
			}
			 if(arr[51]!=null&&!arr[51].equals("")&&!arr[51].equals("null")){
			map.put("branch_id",arr[51]); 
			}
			 if(arr[52]!=null&&!arr[52].equals("")&&!arr[52].equals("null")){
			map.put("companyno",arr[52]); 
			}
			 if(arr[53]!=null&&!arr[53].equals("")&&!arr[53].equals("null")){
//			map.put("credit_score",arr[53]); 
				 Map<String, Object> mapscore = JSON.parseObject(arr[53], (new HashMap<String, Object>()).getClass());
				 for (Map.Entry<String, Object> score:mapscore.entrySet()) {
					 if(score.getValue()!=null){
						 map.put(score.getKey(), score.getValue().toString());
					 }
				}
			}
			 if(arr[54]!=null&&!arr[54].equals("")&&!arr[54].equals("null")){
			map.put("mobile_idcard",arr[54]); 
			}
			 if(arr[55]!=null&&!arr[55].equals("")&&!arr[55].equals("null")){
			map.put("mobile_police",arr[55]); 
			}
			 if(arr[56]!=null&&!arr[56].equals("")&&!arr[56].equals("null")){
			map.put("idcard_police",arr[56]); 
			}
			 if(arr[57]!=null&&!arr[57].equals("")&&!arr[57].equals("null")){
			map.put("errcode",arr[57]); 
			}
			 if(arr[58]!=null&&!arr[58].equals("")&&!arr[58].equals("null")){
			map.put("errmessage",arr[58]); 
			}
			 if(arr[59]!=null&&!arr[59].equals("")&&!arr[59].equals("null")){
			map.put("credit_hack_status",arr[59]); 
			}
			 if(arr[60]!=null&&!arr[60].equals("")&&!arr[60].equals("null")){
			map.put("remark_status",arr[60]); 
			}
			 if(arr[61]!=null&&!arr[61].equals("")&&!arr[61].equals("null")){
			map.put("remark_auth",arr[61]); 
			}
			 if(arr[62]!=null&&!arr[62].equals("")&&!arr[62].equals("null")){
			map.put("remark_pic",arr[62]); 
			}
			 if(arr[63]!=null&&!arr[63].equals("")&&!arr[63].equals("null")){
			map.put("channel",arr[63]); 
			}
			 if(arr[64]!=null&&!arr[64].equals("")&&!arr[64].equals("null")){
			map.put("pb_credit_limit",arr[64]); 
			}
			 if(arr[65]!=null&&!arr[65].equals("")&&!arr[65].equals("null")){
			map.put("pb_risk_level",arr[65]); 
			}
			 if(arr[66]!=null&&!arr[66].equals("")&&!arr[66].equals("null")){
			map.put("limit_no",arr[66]); 
			}
			 if(arr[67]!=null&&!arr[67].equals("")&&!arr[67].equals("null")){
			map.put("consum_type",arr[67]); 
			}
			 if(arr[68]!=null&&!arr[69].equals("")&&!arr[69].equals("null")){
			map.put("credit_mode",arr[68]); 
			}
			 if(arr[69]!=null&&!arr[69].equals("")&&!arr[69].equals("null")){
			map.put("target_amount",arr[69]); 
			}
			 if(arr[70]!=null&&!arr[70].equals("")&&!arr[70].equals("null")){
			map.put("instanceid",arr[70]); 
			}
			 if(arr[71]!=null&&!arr[71].equals("")&&!arr[71].equals("null")){
			map.put("personsignstatus",arr[71]); 
			}
			 if(arr[72]!=null&&!arr[72].equals("")&&!arr[72].equals("null")){
			map.put("hack_score",arr[72]); 
			}
			 if(arr[73]!=null&&!arr[73].equals("")&&!arr[73].equals("null")){
			map.put("channel_source",arr[73]); 
			}
			 if(arr[74]!=null&&!arr[74].equals("")&&!arr[74].equals("null")){
			map.put("group_code",arr[74]); 
			}
			 if(arr[75]!=null&&!arr[75].equals("")&&!arr[75].equals("null")){
			map.put("assignstatus",arr[75]); 
			}
			 if(arr[76]!=null&&!arr[76].equals("")&&!arr[76].equals("null")){
			map.put("merchant_address_codes",arr[76]); 
			}
			 if(arr[77]!=null&&!arr[77].equals("")&&!arr[77].equals("null")){
			map.put("merchant_address_names",arr[77]); 
			}
			 if(arr[78]!=null&&!arr[78].equals("")&&!arr[78].equals("null")){
			map.put("locked",arr[78]); 
			}
			 if(arr[79]!=null&&!arr[79].equals("")&&!arr[79].equals("null")){
			map.put("send_status",arr[79]); 
			}
			 if(arr[80]!=null&&!arr[80].equals("")&&!arr[80].equals("null")){
			map.put("send_time",arr[80]); 
			}
			 if(arr[81]!=null&&!arr[81].equals("")&&!arr[81].equals("null")){
			map.put("body_credit_org",arr[81]); 
			}
			 if(arr[82]!=null&&!arr[82].equals("")&&!arr[82].equals("null")){
			map.put("local_org",arr[82]); 
			}
			 if(arr[83]!=null&&!arr[83].equals("")&&!arr[83].equals("null")){
			map.put("engineflag",arr[83]); 
			}
			 if(arr[84]!=null&&!arr[84].equals("")&&!arr[84].equals("null")){
			map.put("apply_time",arr[84]);
			}
			 return map;
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
	private static void updateApplyInfos(Map<String,String> maps){
		
		StringBuilder sbApply=new StringBuilder();
		//MERGE (p)-[r:"+RelationType.LOANAPPLY+"]->(m)
		StringBuilder sbMobileAndRelation=new StringBuilder();
		sbApply.append("merge (m:"+ModelType.APPLYINFO.getModelName()+" {orderno:'"+maps.get("orderno")+"'}) set m.modelname='"+ModelType.APPLYINFO.getModelName()+"',m.orderno='"+maps.get("orderno")+"',m.group='"+ModelType.APPLYINFO.getEntityGroup()+"',");
//		 List<String> sqlList=new ArrayList<String>();
		
		String keyString=null;
		String valueString=null;
		for(Map.Entry<String, String> map:maps.entrySet()){
		
			keyString=map.getKey();
			valueString=(map.getValue()==null?null:map.getValue().toString());
			switch (keyString) {
//			case "mobile":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (a:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set a.modelname='"+ModelType.MOBILE.getModelName()+"',a.content='"+valueString+"',a.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.APPLYMYMOBILE.getRelationType()+"]->(a)");
//				}
//				break;
//			case "emergencymobile":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (b:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set b.modelname='"+ModelType.MOBILE.getModelName()+"',b.content='"+valueString+"',b.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(b)");
//				}
//				break;
//			case "hometel":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (c:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set c.modelname='"+ModelType.MOBILE.getModelName()+"',c.content='"+valueString+"',c.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(c)");
//
//				}
//				break;
//			case "contactmobile":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (d:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set d.modelname='"+ModelType.MOBILE.getModelName()+"',d.content='"+valueString+"',d.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(d)");
//
//				}
//				break;
//			case "usercoremobile":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (e:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set e.modelname='"+ModelType.MOBILE.getModelName()+"',e.content='"+valueString+"',e.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(e)");
//
//				}
//				break;
//			case "channelmobile":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (f:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set f.modelname='"+ModelType.MOBILE.getModelName()+"',f.content='"+valueString+"',f.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(f)");
//
//				}
//				break;
//			case "partnercontantmobile":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (g:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set g.modelname='"+ModelType.MOBILE.getModelName()+"',g.content='"+valueString+"',g.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(g)");
//
//				}
//				break;
//			case "merchantmobile":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (h:"+ModelType.MOBILE.getModelName()+" {content:'"+valueString+"'}) set h.modelname='"+ModelType.MOBILE.getModelName()+"',h.content='"+valueString+"',h.group='"+ModelType.MOBILE.getEntityGroup()+"' merge (m)-[:"+RelationType.LOANAPPLY.getRelationType()+"]->(h)");
//
//				}
//				break;
//			case "debitcard":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (i:"+ModelType.BANKCARD.getModelName()+" {content:'"+valueString+"'}) set i.modelname='"+ModelType.BANKCARD.getModelName()+"',i.content='"+valueString+"',i.group='"+ModelType.BANKCARD.getEntityGroup()+"' merge (m)-[:"+RelationType.BANKCARD.getRelationType()+"]->(i)");
//
//				}
//				break;
			case "loan_pan"://贷款银行卡号
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (i0:"+ModelType.BANKCARD.getModelName()+" {content:'"+valueString+"'}) set i0.modelname='"+ModelType.BANKCARD.getModelName()+"',i0.content='"+valueString+"',i0.pan='loan_pan',i0.group='"+ModelType.BANKCARD.getEntityGroup()+"' merge (m)-[:"+RelationType.BANKCARD.getRelationType()+"]->(i0)");

				}
				break;
			case "return_pan"://还款银行卡号
				if(valueString!=null&&!"".equals(valueString))
				{
					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
					sbMobileAndRelation.append(" merge (i1:"+ModelType.BANKCARD.getModelName()+" {content:'"+valueString+"'}) set i1.modelname='"+ModelType.BANKCARD.getModelName()+"',i1.content='"+valueString+"',i1.pan='return_pan',i1.group='"+ModelType.BANKCARD.getEntityGroup()+"' merge (m)-[:"+RelationType.BANKCARD.getRelationType()+"]->(i1)");

				}
				break;
//			case "certno":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (j:"+ModelType.IDENTIFICATION.getModelName()+" {content:'"+valueString+"'}) set j.modelname='"+ModelType.IDENTIFICATION.getModelName()+"',j.content='"+valueString+"',j.group='"+ModelType.IDENTIFICATION.getEntityGroup()+"'");
//					if(!maps.containsKey("certtype")&&maps.get("certtype")!=null){
//						sbMobileAndRelation.append(",j.certtype='"+maps.get("certtype")+"' ");
//					}
//					sbMobileAndRelation.append(" merge (m)-[:"+RelationType.IDENTIFICATION.getRelationType()+"]->(j)");
//				}
//				break;
//
//			case "creditcard":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (k:"+ModelType.BANKCARD.getModelName()+" {content:'"+valueString+"'}) set k.modelname='"+ModelType.BANKCARD.getModelName()+"',k.content='"+valueString+"',k.group='"+ModelType.BANKCARD.getEntityGroup()+"' merge (m)-[:"+RelationType.CREDITCARD.getRelationType()+"]->(k)");
//
//				}
//				break;
//			case "_deviceid":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (l:"+ModelType.DEVICE.getModelName()+" {content:'"+valueString+"'}) set l.modelname='"+ModelType.DEVICE.getModelName()+"',l.content='"+valueString+"',l.group='"+ModelType.DEVICE.getEntityGroup()+"' merge (m)-[:"+RelationType.DEVICE.getRelationType()+"]->(l)");
//				}
//				break;
//
//			case "ipv4":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (n:"+ModelType.IPV4.getModelName()+" {content:'"+valueString+"'}) set n.modelname='"+ModelType.IPV4.getModelName()+"',n.content='"+valueString+"',n.group='"+ModelType.IPV4.getEntityGroup()+"' merge (m)-[:"+RelationType.IPV4.getRelationType()+"]->(n)");
//
//				}
//				break;
//			case "lbs":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					if(maps.containsKey("lbsx")&&maps.containsKey("lbsy")&&maps.containsKey("lbsanalyzed")&&maps.get("lbsanalyzed")!=null&&!maps.get("lbsanalyzed").equals("")){
//						sbApply.append("m.lbsx='"+(maps.get("lbsx")==null?null:maps.get("lbsx").toString())+"',m.lbsy='"+(maps.get("lbsy")==null?null:maps.get("lbsy").toString())+"',m.lbsanalyzed='"+(maps.get("lbsanalyzed")==null?null:maps.get("lbsanalyzed").toString())+"',");
//						sbMobileAndRelation.append(" merge (o:"+ModelType.LBS.getModelName()+" {content:'"+maps.get("lbsanalyzed")+"'}) set o.modelname='"+ModelType.LBS.getModelName()+"',o.content='"+maps.get("lbsanalyzed")+"',"+"o.lbsx='"+(maps.get("lbsx")==null?null:maps.get("lbsx").toString())+"',o.lbsy='"+(maps.get("lbsy")==null?null:maps.get("lbsy").toString())+"',o.lbsanalyzed='"+maps.get("lbsanalyzed").toString()+"'"+",o.group='"+ModelType.LBS.getEntityGroup()+"' merge (m)-[:"+RelationType.LBS.getRelationType()+"]->(o)");
//					}
//				}
//				break;
//			case "lbsaddress":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (o0:"+ModelType.LBS.getModelName()+" {content:'"+maps.get("lbsaddress").toString()+"'}) set o0.modelname='"+ModelType.LBS.getModelName()+"',o0.content='"+maps.get("lbsaddress").toString()+"',"+"o0.lbsx='"+(maps.get("lbsx")==null?null:maps.get("lbsx").toString())+"',o0.group='"+ModelType.LBS.getEntityGroup()+"' merge (m)-[:"+RelationType.LBS.getRelationType()+"]->(o0)");
//					
//				}
//				break;
//				
//			case "companyname":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//						sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//						sbMobileAndRelation.append(" merge (p:"+ModelType.COMPANY.getModelName()+" {content:'"+valueString+"'}) set p.modelname='"+ModelType.COMPANY.getModelName()+"', p.content='"+valueString+"',p.group='"+ModelType.COMPANY.getEntityGroup()+"' merge (m)-[:"+RelationType.MAJORCOMPANY.getRelationType()+"]->(p)");
//				}
//				break;
//			case "companyaddress":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (q:"+ModelType.COMPANYADDRESS.getModelName()+" {content:'"+valueString+"'}) set q.modelname='"+ModelType.COMPANYADDRESS.getModelName()+"',q.content='"+valueString+"',q.group='"+ModelType.COMPANYADDRESS.getEntityGroup()+"' merge (m)-[:"+RelationType.COMPANYADDRESS.getRelationType()+"]->(q)");
//				}
//				break;
//			case "companytel":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString()).replace("|", "")+"',");
//					sbMobileAndRelation.append(" merge (s:"+ModelType.COMPANYTEL.getModelName()+" {content:'"+valueString.replace("|", "")+"'}) set s.modelname='"+ModelType.COMPANYTEL.getModelName()+"',s.content='"+valueString.replace("|", "")+"',s.group='"+ModelType.COMPANYTEL.getEntityGroup()+"' merge (m)-[:"+RelationType.COMPANYTEL.getRelationType()+"]->(s)");
//				}
//				break;	
//				
//			case "imei":
//				if(valueString!=null&&!"".equals(valueString))
//				{
////					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
////					sbMobileAndRelation.append(" merge (t:"+ModelType.MOBILEIMEI.getModelName()+" {content:'"+valueString+"'}) set t.modelname='"+ModelType.MOBILEIMEI.getModelName()+"',t.content='"+valueString+"',t.group='"+ModelType.MOBILEIMEI.getEntityGroup()+"' merge (m)-[:"+RelationType.MOBILEIMEI.getRelationType()+"]->(t)");
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (l:"+ModelType.DEVICE.getModelName()+" {content:'"+valueString+"'}) set l.modelname='"+ModelType.DEVICE.getModelName()+"',l.content='"+valueString+"',l.group='"+ModelType.DEVICE.getEntityGroup()+"' merge (m)-[:"+RelationType.DEVICE.getRelationType()+"]->(l)");
//				
//				}
//				break;
//			case "email":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (u:"+ModelType.EMAIL.getModelName()+" {content:'"+valueString+"'}) set u.modelname='"+ModelType.EMAIL.getModelName()+"',u.content='"+valueString+"',u.group='"+ModelType.EMAIL.getEntityGroup()+"' merge (m)-[:"+RelationType.EMAIL.getRelationType()+"]->(u)");
//
//				}
//				break;
//			case "termid":
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					sbMobileAndRelation.append(" merge (v:"+ModelType.TERMINAL.getModelName()+" {content:'"+valueString+"'}) set v.modelname='"+ModelType.TERMINAL.getModelName()+"',v.content='"+valueString+"',v.group='"+ModelType.TERMINAL.getEntityGroup()+"' merge (m)-[:"+RelationType.TERMINAL.getRelationType()+"]->(v)");
//
//				}
//				break;
			default:
//				if(valueString!=null&&!"".equals(valueString))
//				{
//					sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
//					
//				}
				break;
			}
		}
		String applysql=sbApply.toString().substring(0, sbApply.length()-1);//将最后一个逗号去掉
		StringBuilder sbBuilder=new StringBuilder();
		sbBuilder.append(applysql);
		sbBuilder.append(sbMobileAndRelation);
		
//		System.out.println(sbBuilder.toString());
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

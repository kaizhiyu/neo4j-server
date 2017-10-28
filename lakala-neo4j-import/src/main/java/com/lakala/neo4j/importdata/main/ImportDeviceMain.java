package com.lakala.neo4j.importdata.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lakala.neo4j.importdata.constant.ModelType;
import com.lakala.neo4j.importdata.constant.RelationType;
import com.lakala.neo4j.importdata.dao.Neo4jHelperDao;

public class ImportDeviceMain {

	
	private static List<String> listfile=new ArrayList<String>();
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
	 * 将进件用户数据导入到neo4j
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
//							str="100894581016939941刘正山185656493791 342422197803185814东湖街道大望新平村181栋302室1 1YFQ/2016-10-28/XNA20161028161036608607/323D479DB9AF4705A3E4A355F80CC7E1.jpgYFQ/2016-10-28/XNA20161028161036608607/D38571B7A4874B80A15598521902A1CC.jpgYFQ/2016-10-28/XNA20161028161036608607/99E3E34C85BC44AD8476E1C82B982297.jpg1null308657624@qq.com深圳市世纪非凡电子科技有限公司益田路3008号皇都广场A座505室0755-32925339null东湖街道大望新平村181栋302室440000|440300|440303广东省|深圳市|罗湖区440000|440300|440304广东省|深圳市|福田区null刘正艳13637261518nullnullnullnull周肖肖13901602763nullnullnullnullnullnull4nullnull4nullnullnullnullnullnullnullnullnullnullnullnullnullnullnullnullnull广东省深圳市宝安区人民路4219号-2层nullnullnullnullnull0nullnullnullnullnullnullnull112.97.61.551男汉1978-03-18辱县瓦房乡冯楼村大辜篓组安徽省寿县公安局20070227-20270227刘正山3424221978031858140null";
							idex++;
							
							map=getApplyUserMap(str,idex);
							if(map==null) {continue;}
							System.out.println(Thread.currentThread().getName()+":Start import data!");
							updateApplyInfos(map);
							System.out.println(Thread.currentThread().getName()+":End import data!");
							
							
						} catch (Exception e) {
							System.err.println(Thread.currentThread()+":ImportDeviceMain updateApplyInfo br:"+str+" [err:"+e.getMessage()+"]");
						}
					}
					System.out.println(Thread.currentThread()+":import success!");
					System.out.println(Thread.currentThread()+":ImportDeviceMain updateApplyInfo cost milliseconds time:"+(System.currentTimeMillis()- t1)+"ms");
					
				} catch (Exception e) {
					System.err.println(Thread.currentThread()+":handleApply error:"+e.getMessage());
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
//				
			});
			
			
		} catch (Exception e) {
			System.out.println(Thread.currentThread()+":ImportDeviceMain updateApplyInfo Executing error:"+e.getMessage());
		}

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
	 * 获取用户表的字段对应值
	 * @param line
	 * @param idex
	 * @return
	 */
	public static Map<String, String> getApplyUserMap(String line,long idex) {
		
		
		String[] arr=line.split("\001");
		Map<String, String> map=new HashMap<String, String>();
		if(arr[3]!=null&&!arr[3].equals("")&&!arr[3].equals("null")){
			map.put("orderno",arr[3]);
			System.out.println("index:"+idex+",orderno:"+arr[3]);
		}
		else {
			return null;
		}
		if(arr[1]!=null&&!arr[1].equals("")&&!arr[1].equals("null")){
		 map.put("mobile",arr[1]);
		 }
		if(arr[2]!=null&&!arr[2].equals("")&&!arr[2].equals("null")){
			 map.put("_deviceid",arr[2]);
		}
		if(arr[11]!=null&&!arr[11].equals("")&&!arr[11].equals("null")){
			 map.put("certno",arr[11]);
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
	private static void updateApplyInfos(Map<String, String> mapDevice){
		
		try {
			if(mapDevice.get("orderno")==null||mapDevice.get("orderno").equals("")||mapDevice.get("orderno").equals("null")) return;
			
		} catch (Exception e) {
			System.err.println(Thread.currentThread()+":"+e.getMessage());
			return;
		}
		StringBuilder sbApply=new StringBuilder();
		//MERGE (p)-[r:"+RelationType.LOANAPPLY+"]->(m)
		StringBuilder sbMobileAndRelation=new StringBuilder();
		String orderno=mapDevice.get("orderno");
//		String orderno="XNA20161028161036608607";
//		System.out.println(Thread.currentThread()+": orderno:"+orderno);
		sbApply.append("merge (m:"+ModelType.APPLYINFO.getModelName()+" {orderno:'"+orderno+"'}) set m.modelname='"+ModelType.APPLYINFO.getModelName()+"',m.orderno='"+orderno+"',m.group='"+ModelType.APPLYINFO.getEntityGroup()+"',");

		String keyString=null;
		String valueString=null;
		for(Map.Entry<String, String> map:mapDevice.entrySet()){
			try {
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
				case "certno":
					if(valueString!=null&&!"".equals(valueString))
					{
						sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
						sbMobileAndRelation.append(" merge (j:"+ModelType.IDENTIFICATION.getModelName()+" {content:'"+valueString+"'}) set j.modelname='"+ModelType.IDENTIFICATION.getModelName()+"',j.content='"+valueString+"',j.group='"+ModelType.IDENTIFICATION.getEntityGroup()+"'");
						if(!mapDevice.containsKey("certtype")&&mapDevice.get("certtype")!=null){
							sbMobileAndRelation.append(",j.certtype='"+mapDevice.get("certtype")+"' ");
						}
						sbMobileAndRelation.append(" merge (m)-[:"+RelationType.IDENTIFICATION.getRelationType()+"]->(j)");
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
						if(mapDevice.containsKey("lbsx")&&mapDevice.containsKey("lbsy")&&mapDevice.containsKey("lbsanalyzed")&&mapDevice.get("lbsanalyzed")!=null&&!mapDevice.get("lbsanalyzed").equals("")){
							sbApply.append("m.lbsx='"+(mapDevice.get("lbsx")==null?null:mapDevice.get("lbsx").toString())+"',m.lbsy='"+(mapDevice.get("lbsy")==null?null:mapDevice.get("lbsy").toString())+"',m.lbsanalyzed='"+(mapDevice.get("lbsanalyzed")==null?null:mapDevice.get("lbsanalyzed").toString())+"',");
							sbMobileAndRelation.append(" merge (o:"+ModelType.LBS.getModelName()+" {content:'"+mapDevice.get("lbsanalyzed")+"'}) set o.modelname='"+ModelType.LBS.getModelName()+"',o.content='"+mapDevice.get("lbsanalyzed")+"',"+"o.lbsx='"+(mapDevice.get("lbsx")==null?null:mapDevice.get("lbsx").toString())+"',o.lbsy='"+(mapDevice.get("lbsy")==null?null:mapDevice.get("lbsy").toString())+"',o.lbsanalyzed='"+mapDevice.get("lbsanalyzed").toString()+"'"+",o.group='"+ModelType.LBS.getEntityGroup()+"' merge (m)-[:"+RelationType.LBS.getRelationType()+"]->(o)");
						}
					}
					break;
				case "lbsaddress":
					if(valueString!=null&&!"".equals(valueString))
					{
						sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
						sbMobileAndRelation.append(" merge (o0:"+ModelType.LBS.getModelName()+" {content:'"+valueString+"'}) set o0.modelname='"+ModelType.LBS.getModelName()+"',o0.content='"+valueString+"',o0.group='"+ModelType.LBS.getEntityGroup()+"' merge (m)-[:"+RelationType.LBS.getRelationType()+"]->(o0)");
						
					}
					break;
					
				case "companyname":
					if(valueString!=null&&!"".equals(valueString))
					{
						sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
						sbMobileAndRelation.append(" merge (p:"+ModelType.COMPANY.getModelName()+" {content:'"+valueString+"'}) set p.modelname='"+ModelType.COMPANY.getModelName()+"', p.content='"+valueString+"',p.group='"+ModelType.COMPANY.getEntityGroup()+"' merge (m)-[:"+RelationType.MAJORCOMPANY.getRelationType()+"]->(p)");
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
					if(valueString!=null&&!"".equals(valueString)&&!keyString.equals("id"))
					{
						sbApply.append("m."+keyString+"='"+(map.getValue()==null?null:map.getValue().toString())+"',");
						
					}
					break;
				}
			} catch (Exception e) {
				System.err.println(Thread.currentThread()+":keyString:"+keyString+ " valueString:"+valueString);
			}
			
		}
		String applysql=sbApply.toString().substring(0, sbApply.length()-1);//将最后一个逗号去掉
		StringBuilder sbBuilder=new StringBuilder();
		sbBuilder.append(applysql);
		sbBuilder.append(sbMobileAndRelation);
		
//		System.out.println(sbBuilder.toString());
		System.out.println(Thread.currentThread()+":import data by sql!");
		try {
			Neo4jHelperDao.executeInsert(sbBuilder.toString());
		} catch (Exception e) {
			System.err.println(Thread.currentThread()+":Execute fail sql:"+sbBuilder.toString()+"   error:"+e.getMessage());
		}
		System.out.println(Thread.currentThread()+":execute sql end! import data success!");

	}


}

package com.lakala.neo4j.importdata.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lakala.neo4j.importdata.dao.Neo4jHelperDao;
import com.lakala.neo4j.importdata.service.LoadCSVService;

/**
 * 通过清洗数据读取csv文件load数据
 */
public class LoadCSVImportDataMain {


	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String dateString =df.format(new Date());
		String tableHeader="";
		System.out.println("Start clean data format!");
		System.out.println("input args :"+args[0]);
		String[] argsarr=args[0].split(",");
		String ip=argsarr[3];
		String saveFilepathString="";//保存路径，同时也是导入数据路径
		String savePathString="";
		String endfilePath=dateString+".csv";
		StringBuffer sql=new StringBuffer();
		if(argsarr[0].equals("call")){
			tableHeader="type,appversion,devicemodel ,deviceid,loginname,nickname,caller_phone ,duration,date,collecttime,platform,osversion";
			savePathString=argsarr[2]+"/call/"+dateString+"/";
			saveFilepathString=savePathString+endfilePath;
			sql=getCallBuffer(sql);
		}else if(argsarr[0].equals("contact")){
			tableHeader="loginname,deviceid,name,mobile,collecttime";
			savePathString=argsarr[2]+"/contact/"+dateString+"/";
			saveFilepathString=savePathString+endfilePath;
			sql=getContactBuffer(sql);
			
		}else if(argsarr[0].equals("apply")){
			tableHeader="manageradvise,_platform,certsecondimg,partneraccountbank,userid,orderno,certfirstimg,partnerid,schname,username,married,schcity,emergencymobile,income,merchanttel,hostname,highestlevel,industry,usermobile,channelid,businesstype,applyamt,partnercontantmobile,partneraccounttype,macodes,ocrcertno,entnature,uuid,companyno,busid,position,contactmobile,getcontacts,biztypeenum,certno,modelno,manames,wechatno,debitbank,partnercontantemail,branchid,quarters,token,cooporgno,creditcard,credtype,telecode,apcrnames,hometel,giftid,sid,graddate,systemcode,curcode,partnercontant,merchantorderexptime,gifttips,giftfeecode,companyname,process,userapcrcodes,schdorm,collecttime,receivetime,partneraddr,merchantname,productno,ocrname,fcatschname,hostip,contactrelation,authpwd,_macwifi,partnercompany,userapcrnames,usercoremobile,_deviceid,authname,lbsaddress,registerorg,giftfeetype,request,creditmode,brithday,fingerprinttab,identitycode,paymode,smscode,credit_productno,gifttype,cpcrcodes,merchantordertime,nation,merchantid,resitemno,schyear,userfullife,partnername,sessionid,schcitycode,email,rpflag,applicant,biztype,merchantno,debitcard,merchantmobile,channelenum,purseid,mobile,ipv4,sex,consumtype,department,merchantorderno,emergencyrelation,partneraccounter,apcrcodes,recommend,merchantregistertime,giftno,creditbank,channo,cpcrnames,bizno,schroll,qq,emergencyname,_macbluetooth,logno,branchcomp,termid,companyaddress,merchantaddress,userno,partneraccount,mchno,address,contactname,registered,nowphoto,consumapplyid,tips,companytel,worktime,channelcode,channelsource,authenticationflag,channelmobile,lbsx,lbsy,lbsanalyzed,receivetimefmt,collecttimefmt,belongprovince,protocolversion,operatetypeenum,operatetype,belongregion,belongcity,ver,msgmobile,ismajorcompany";
			savePathString=argsarr[2]+"/apply/"+dateString+"/";
			saveFilepathString=savePathString+endfilePath;

			sql=getApplyBuffer(sql);
		}
		Neo4jHelperDao.url="bolt://"+ip+":7687";
		
		traverseFolder(argsarr[0],argsarr[1]);//递归查找文件
		//打包时候取消以下两个注释
		System.out.println("output file:"+saveFilepathString);
		removeOutputfile(new File(saveFilepathString));
		judeDirExists(new File(savePathString));
		
		
		LoadCSVService.handFile(argsarr[0],listfile, saveFilepathString, tableHeader);
		System.out.println("Start import data!");
		System.out.println(argsarr[0]+" import data!");
//		Neo4jBatchDataService.savesimple(String.format(sql.toString(), saveFilepathString));
		System.out.println(String.format(sql.toString(), "/"+argsarr[0]+"/"+dateString+"/"+endfilePath));
		Neo4jHelperDao.executeInsert(String.format(sql.toString(), "/"+argsarr[0]+"/"+dateString+"/"+endfilePath));
		System.out.println(argsarr[0]+" import data!");
	}


 /** 
  * 判断文件夹是否存在 不存在则创建
  * @param file
  */
	private static void judeDirExists(File file) {
	
		try {
			   if (!file.exists()) {
				   file.mkdirs();
			   }
		} catch (Exception e) {
			
		}

	
	}
	
	private static List<String> listfile=new ArrayList<String>();
	/**
	 * 递归获取当前文件夹下所有文件
	 * @param path
	 */
	public static void traverseFolder(String type,String path) {
		
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
                        traverseFolder(type,file2.getAbsolutePath());
                    } else {
                    	if(type.equals("call")||type.equals("contact")){
                    		if(file2.getAbsolutePath().contains("part")){
	                    		listfile.add(file2.getAbsolutePath());
	                    		System.out.println("文件:" + file2.getAbsolutePath());
                    		}
                    	}
                    	else {
                    		if(file2.getAbsolutePath().contains("events")){
                        		listfile.add(file2.getAbsolutePath());
                        		System.out.println("文件:" + file2.getAbsolutePath());
                    		}
						}
                        
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	
	private static void removeOutputfile(File file){
		
		if(!file.exists()){
			System.out.println("文件不存在");
		}
		else{
			System.out.println("存在文件");
			System.out.println(file.delete());//删除文件
		}
	}
	
	private static StringBuffer getCallBuffer(StringBuffer sql) {
		sql.append("load csv with headers from \"file:%s\" AS row ");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.loginname is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p:Mobile {content:row.loginname}) set p.modelname='Mobile',p.content=row.loginname,p.type=row.type,p.appversion=row.appversion,p.devicemodel =row.devicemodel,p.deviceid=row.deviceid,p.nickname=row.nickname,p.duration=row.duration,p.date=row.date,p.collecttime=row.collecttime,p.platform=row.platform,p.osversion=row.osversion,p.group='2'");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.caller_phone is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (q:Mobile {content:row.caller_phone}) set q.modelname='Mobile',q.content=row.caller_phone ,q.group='2' MERGE (p)-[:call]->(q) ");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append(")");
		return sql;
	}
	
	private static StringBuffer getContactBuffer(StringBuffer sql) {
		sql.append("load csv with headers from \"file:%s\" AS row ");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.loginname is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p:Mobile {content:row.loginname}) set p.modelname='Mobile',p.content=row.loginname,p.deviceid=row.deviceid,p.name=row.name,p.collecttime=row.collecttime,p.group='2'");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.mobile is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (q:Mobile {content:row.mobile}) set q.modelname='Mobile',q.content=row.mobile ,q.group='2' MERGE (p)-[:call]->(q)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append(")");
		return sql;
	}
	
	private static StringBuffer getApplyBuffer(StringBuffer sql) {
		sql.append("load csv with headers from \"file:%s\" AS row ");
		sql.append("FOREACH (_ IN case when row.orderno is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p1:ApplyInfo {orderno: row.orderno}) set "
				+ "p1.manageradvise=row.manageradvise,p1._platform=row._platform,p1.certsecondimg=row.certsecondimg,p1.partneraccountbank=row.partneraccountbank,p1.userid=row.userid,"
				+ "p1.orderno=row.orderno,p1.certfirstimg=row.certfirstimg,p1.partnerid=row.partnerid,p1.schname=row.schname,p1.username=row.username,"
				+ "p1.married=row.married,p1.schcity=row.schcity,p1.emergencymobile=row.emergencymobile,p1.income=row.income,p1.merchanttel=row.merchanttel,p1.hostname=row.hostname,"
				+ "p1.highestlevel=row.highestlevel,p1.industry=row.industry,p1.usermobile=row.usermobile,p1.channelid=row.channelid,p1.businesstype=row.businesstype,"
				+ "p1.applyamt=row.applyamt,p1.partnercontantmobile=row.partnercontantmobi,p1.partneraccounttype=row.partneraccounttype,p1.macodes=row.macodes,p1.ocrcertno=row.ocrcertno,"
				+ "p1.entnature=row.entnature,p1.uuid=row.uuid,p1.companyno=row.companyno,p1.busid=row.busid,p1.position=row.position,p1.contactmobile=row.contactmobile,"
				+ "p1.getcontacts=row.getcontacts,p1.biztypeenum=row.biztypeenum,p1.certno=row.certno,p1.modelno=row.modelno,p1.manames=row.manames,p1.wechatno=row.wechatno,"
				+ "p1.debitbank=row.debitbank,p1.partnercontantemail=row.partnercontantemai,p1.branchid=row.branchid,p1.quarters=row.quarters,p1.token=row.token,"
				+ "p1.cooporgno=row.cooporgno,p1.creditcard=row.creditcard,p1.credtype=row.credtype,p1.telecode=row.telecode,p1.apcrnames=row.apcrnames,p1.hometel=row.hometel,"
				+ "p1.giftid=row.giftid,p1.sid=row.sid,p1.graddate=row.graddate,p1.systemcode=row.systemcode,p1.curcode=row.curcode,p1.partnercontant=row.partnercontant,"
				+ "p1.merchantorderexptime=row.merchantorderexpti,p1.gifttips=row.gifttips,p1.giftfeecode=row.giftfeecode,p1.companyname=row.companyname,p1.process=row.process,"
				+ "p1.userapcrcodes=row.userapcrcodes,p1.schdorm=row.schdorm,p1.collecttime=row.collecttime,p1.receivetime=row.receivetime,p1.partneraddr=row.partneraddr,"
				+ "p1.merchantname=row.merchantname,p1.productno=row.productno,p1.ocrname=row.ocrname,p1.fcatschname=row.fcatschname,p1.hostip=row.hostip,"
				+ "p1.contactrelation=row.contactrelation,p1.authpwd=row.authpwd,p1._macwifi=row._macwifi,p1.partnercompany=row.partnercompany,p1.userapcrnames=row.userapcrnames,"
				+ "p1.usercoremobile=row.usercoremobile,p1._deviceid=row._deviceid,p1.authname=row.authname,p1.lbsaddress=row.lbsaddress,p1.registerorg=row.registerorg,"
				+ "p1.giftfeetype=row.giftfeetype,p1.request=row.request,p1.creditmode=row.creditmode,p1.brithday=row.brithday,p1.fingerprinttab=row.fingerprinttab,"
				+ "p1.identitycode=row.identitycode,p1.paymode=row.paymode,p1.smscode=row.smscode,p1.credit_productno=row.credit_productno,p1.gifttype=row.gifttype,"
				+ "p1.cpcrcodes=row.cpcrcodes,p1.merchantordertime=row.merchantordertime,p1.nation=row.nation,p1.merchantid=row.merchantid,p1.resitemno=row.resitemno,"
				+ "p1.schyear=row.schyear,p1.userfullife=row.userfullife,p1.partnername=row.partnername,p1.sessionid=row.sessionid,p1.schcitycode=row.schcitycode,"
				+ "p1.email=row.email,p1.rpflag=row.rpflag,p1.applicant=row.applicant,p1.biztype=row.biztype,p1.merchantno=row.merchantno,p1.debitcard=row.debitcard,"
				+ "p1.merchantmobile=row.merchantmobile,p1.channelenum=row.channelenum,p1.purseid=row.purseid,p1.mobile=row.mobile,p1.ipv4=row.ipv4,p1.sex=row.sex,"
				+ "p1.consumtype=row.consumtype,p1.department=row.department,p1.merchantorderno=row.merchantorderno,p1.emergencyrelation=row.emergencyrelation,"
				+ "p1.partneraccounter=row.partneraccounter,p1.apcrcodes=row.apcrcodes,p1.recommend=row.recommend,p1.merchantregistertime=row.merchantregisterti,"
				+ "p1.giftno=row.giftno,p1.creditbank=row.creditbank,p1.channo=row.channo,p1.cpcrnames=row.cpcrnames,p1.bizno=row.bizno,p1.schroll=row.schroll,"
				+ "p1.qq=row.qq,p1.emergencyname=row.emergencyname,p1._macbluetooth=row._macbluetooth,p1.logno=row.logno,p1.branchcomp=row.branchcomp,p1.termid=row.termid,"
				+ "p1.companyaddress=row.companyaddress,p1.merchantaddress=row.merchantaddress,p1.userno=row.userno,p1.partneraccount=row.partneraccount,p1.mchno=row.mchno,"
				+ "p1.address=row.address,p1.contactname=row.contactname,p1.registered=row.registered,p1.nowphoto=row.nowphoto,p1.consumapplyid=row.consumapplyid,p1.tips=row.tips,"
				+ "p1.companytel=row.companytel,p1.worktime=row.worktime,p1.channelcode=row.channelcode,p1.channelsource=row.channelsource,p1.authenticationflag=row.authenticationflag,"
				+ "p1.channelmobile=row.channelmobile,p1.lbs=row.lbs,p1.lbsx=row.lbsx,p1.lbsy=row.lbsy,p1.lbsanalyzed=row.lbsanalyzed,p1.receivetimefmt=row.receivetimefmt,"
				+ "p1.collecttimefmt=row.collecttimefmt,p1.belongprovince=row.belongprovince,p1.protocolversion=row.protocolversion,p1.operatetypeenum=row.operatetypeenum,"
				+ "p1.operatetype=row.operatetype,p1.belongregion=row.belongregion,p1.belongcity=row.belongcity,p1.ver=row.ver,p1.msgmobile=row.msgmobile");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.mobile is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p3:Mobile {content:row.mobile}) set p3.modelname='Mobile',p3.content=row.mobile,p3.group='2'  MERGE (p1)-[:applymymobile]->(p3)");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.hometel is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p31:Mobile {content:row.hometel}) set p31.modelname='Mobile',p31.content=row.hometel,p31.group='2'  MERGE (p1)-[:loanapply]->(p31)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.emergencymobile is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p32:Mobile {content:row.emergencymobile}) set p32.modelname='Mobile',p32.content=row.emergencymobile,p32.group='2'  MERGE (p1)-[:loanapply]->(p32)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.contactmobile is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p33:Mobile {content:row.contactmobile}) set p33.modelname='Mobile',p33.content=row.contactmobile,p33.group='2'  MERGE (p1)-[:loanapply]->(p33)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.usercoremobile is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p34:Mobile {content:row.usercoremobile}) set p34.modelname='Mobile',p34.content=row.usercoremobile,p34.group='2'  MERGE (p1)-[:loanapply]->(p34)");
		sql.append("\n");
		sql.append(")");
		sql.append("FOREACH (_ IN case when row.channelmobile is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p35:Mobile {content:row.channelmobile}) set p35.modelname='Mobile',p35.content=row.channelmobile,p35.group='2'  MERGE (p1)-[:loanapply]->(p35)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.partnercontantmobile is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p36:Mobile {content:row.partnercontantmobile}) set p36.modelname='Mobile',p36.content=row.partnercontantmobile,p36.group='2'  MERGE (p1)-[:loanapply]->(p36)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.merchantmobile is not null  then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p37:Mobile {content:row.merchantmobile}) set p37.modelname='Mobile',p37.content=row.merchantmobile,p37.group='2'  MERGE (p1)-[:loanapply]->(p37)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row._deviceid is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p4:Device {content:row._deviceid}) set p4.modelname='Device',p4.content=row._deviceid,p4.group='3'  MERGE (p1)-[:device]->(p4)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.debitbank is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p5:BankCard {content:row.debitbank}) set p5.modelname='BankCard',p5.content=row.debitbank,p5.cardtype='1',p5.group='4' MERGE (p1)-[:bankcard]->(p5)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.creditbank is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p51:BankCard {content:row.creditbank}) set p51.modelname='BankCard',p51.content=row.creditbank,p51.cardtype='2',p51.group='4' MERGE (p1)-[:creditcard]->(p51)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.certno is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p6:Identification {content:row.certno}) set p6.modelname='Identification',p6.content=row.certno,p6.group='5'  MERGE (p1)-[:identification]->(p6)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.email is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p7:Email {content:row.email}) set p7.modelname='Email',p7.content=row.email ,p7.group='6' MERGE (p1)-[:email]->(p7)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.termid is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p8:Terminal {content:row.termid}) set p8.modelname='Terminal',p8.content=row.termid ,p8.group='7' MERGE (p1)-[:terminal]->(p8)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.lbsanalyzed is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p11:LBS {content:row.lbsanalyzed}) set p11.modelname='LBS',p11.content=row.lbsanalyzed ,p11.lbsx=row.lbsx ,p11.lbsy=row.lbsy ,p11.group='10' MERGE (p1)-[:LBS]->(p11)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.companyname is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p12:Company {content:row.companyname}) set p12.modelname='Company',p12.content=row.companyname ,p12.group='11'  MERGE (p1)-[:company]->(p12)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.companyaddress is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p14:CompanyAddress {content:row.companyaddress}) set p14.modelname='CompanyAddress',p14.content=row.companyaddress ,p14.group='13' MERGE (p1)-[:companyaddress]->(p14)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.ipv4 is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p15:IPV4 {content:row.ipv4}) set p15.modelname='IPV4',p15.content=row.ipv4 ,p15.group='14' MERGE (p1)-[:ipv4]->(p15)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append("FOREACH (_ IN case when row.companytel is not null then [1] else [] end|");
		sql.append("\n");
		sql.append("MERGE (p16:CompanyTel {content:row.companytel}) set p16.modelname='CompanyTel',p16.content=row.companytel ,p16.group='15' MERGE (p1)-[:companytel]->(p16)");
		sql.append("\n");
		sql.append(")");
		sql.append("\n");
		sql.append(")");
		return sql;
	}


}

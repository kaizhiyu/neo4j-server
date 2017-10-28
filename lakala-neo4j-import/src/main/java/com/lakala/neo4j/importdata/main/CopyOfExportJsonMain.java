package com.lakala.neo4j.importdata.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lakala.neo4j.importdata.constant.ModelType;
import com.lakala.neo4j.importdata.dao.Neo4jUtilServiceImpl;
import com.lakala.neo4j.importdata.models.IMEIApplyInfoV;
import com.lakala.neo4j.importdata.models.IMEIDataSourceStringCollection;
import com.lakala.neo4j.importdata.models.IMEILoanApplyNeo4j;
import com.lakala.neo4j.importdata.models.IMEIModelNeo4j;
import com.lakala.neo4j.importdata.models.IMEIModelV;
import com.lakala.neo4j.importdata.models.IMEIRelationE;
import com.lakala.neo4j.importdata.models.IMEIRelationNeo4j;
import com.lakala.neo4j.importdata.utils.Neo4jModelToIMEIModel;

public class CopyOfExportJsonMain {

	public static void main(String[] args) {
	
		
		System.out.println("args:"+args[0]);
		String[] argString=args[0].split(",");
		
		String filePath=argString[0];
		String filename=argString[1];
		
		System.out.println("ExportFilePath:"+filePath+"/"+filename);
		judeDirExists(new File(filePath));
		
		
		File file=new File(filePath+"/"+filename);
		if(!file.exists())    
		{    
		    try {    
		        file.createNewFile();    
		    } catch (IOException e) {    
		        // TODO Auto-generated catch block    
		        e.printStackTrace();    
		    }    
		} 
		//通过订单获取所有属性和关联订单
		StringBuilder sbApply=getApplyLoanInfo("AX20170120140133111439");
		//手机号 15933101236
		//device 864188032309971
		//手机号 15932193178 ->AX20170120140133111439->223.104.13.57

		writeDataToTxt("初始查询的订单信息:"+sbApply.toString(),filePath+"/"+filename);
		
		StringBuilder sBuilder = getApplyLoanInfoByProp("Mobile","15932193178");
		
		writeDataToTxt("根据手机号查询的订单:"+sBuilder.toString(),filePath+"/"+filename);
		
		StringBuilder sbApplysub=getApplyLoanInfoProps("AX20170120140133111439");
		writeDataToTxt("根据订单查询订单的所有属性:"+sbApplysub.toString(),filePath+"/"+filename);
		
		StringBuilder sbipv4getApply = getApplyLoanInfoByProp("IPV4","223.104.13.57");
		
		writeDataToTxt("根据IP地址查询的订单:"+sbipv4getApply.toString(),filePath+"/"+filename);
		
	}
	
	/**
	 * 通过订单号查找对应的关系，包括通讯录通话记录，设备，进件关系
	 * @param orderNo
	 * @return
	 */
	public static StringBuilder getApplyLoanInfoProps(String orderno){
		if(orderno==null||orderno.equals("")){
			return new StringBuilder();
		}
		IMEIDataSourceStringCollection collection=null;
		List<Map<String, String>> relationModels=null;
		collection=Neo4jUtilServiceImpl.QueyModelByOrderno(orderno);

//		relationModels=JSON.parseObject(relations,new ArrayList<Map<String,String>>().getClass());
//		List<String> relationidList=new ArrayList<String>();
//		DataSourceStringCollection collection=null;
		
//		if (relationids!=null||relationids.length()==0) {
//			collection=Neo4jUtilServiceImpl.QueyApplyByContent(modelname, content);
//		}else {//通过序列化机制来反系列化成relationneo4j去除重复点和边
//			List<RelationNeo4j> neo4jList=JSON.parseObject(relationids,new ArrayList<String>().getClass());
//			collection=Neo4jUtilServiceImpl.QueyApplyByContent(modelname, content,neo4jList);
//		}

		
		StringBuilder sbStringBuilder=new StringBuilder();
		sbStringBuilder.append("{");
		sbStringBuilder.append("\"nodes\":");
		sbStringBuilder.append("[");
		StringBuilder sbmodelstr=new StringBuilder();//实体对应的json集合
		List<String> idList=new ArrayList<String>();
		List<String> eList=new ArrayList<String>();
		if(collection.modelList==null){
			return new StringBuilder();
		}
//		if(relations!=null&&!relations.equals("")){
//			for(Map<String, String> model:relationModels){
//				idList.add(model.get("target"));
//				idList.add(model.get("source"));
//				eList.add(model.get("id"));
//			}
//		}

		for(String modelstr:  collection.modelList){
			IMEIModelNeo4j modelNeo4j  = JSON.parseObject(modelstr, IMEIModelNeo4j.class);
			if(!idList.contains(modelNeo4j.getId()))
			{
				idList.add(modelNeo4j.getId());
				IMEIModelV modelV=Neo4jModelToIMEIModel.ModelNeo4jToModelV(modelNeo4j);
				String json = JSONObject.toJSON(modelV).toString();
				sbmodelstr.append(json);
					sbmodelstr.append(",");
			}
			
			
		}
		String modelstr="";
		if(sbmodelstr.length()>0)
		{
			modelstr=sbmodelstr.substring(0,sbmodelstr.length()-1);
		}
		sbStringBuilder.append(modelstr);
		sbStringBuilder.append("],");
		
		sbStringBuilder.append("\"links\":");
		sbStringBuilder.append("[");
		
		StringBuilder sbRelationstr=new StringBuilder();//关系对应的json集合
		
		for(String relationstr: collection.relationList){
			IMEIRelationNeo4j relationNeo4j= JSON.parseObject(relationstr, IMEIRelationNeo4j.class);
			if(!eList.contains(relationNeo4j.getId())){
				eList.add(relationNeo4j.getId());
				IMEIRelationE relationE = Neo4jModelToIMEIModel.RelationNeo4jToRelationE(relationNeo4j);
				String json = JSONObject.toJSON(relationE).toString();
				sbRelationstr.append(json);
				sbRelationstr.append(",");
			}
			
		}
		String relationStr="";
		if(sbRelationstr.length()>0)
		{
			relationStr=sbRelationstr.substring(0,sbRelationstr.length()-1);
		}
		sbStringBuilder.append(relationStr);
		sbStringBuilder.append("]");
		sbStringBuilder.append("}");
		return sbStringBuilder;
	}
	
	/**
	 * 通过订单号查找对应的关系，包括通讯录通话记录，设备，进件关系
	 * @param orderNo
	 * @return
	 */
	public static StringBuilder getApplyLoanInfoByProp(String modelname,String content){
		List<String> relationidList=new ArrayList<String>();
		if(modelname==null||modelname.equals("")||content==null||content.equals("")){
			return new StringBuilder();
		}
		IMEIDataSourceStringCollection collection=Neo4jUtilServiceImpl.QueyApplyByContent(modelname, content);
//		List<Map<String,String>> neo4jList=null;
//		neo4jList=JSON.parseObject(relationids,new ArrayList<Map<String,String>>().getClass());
	
//		if (relationids==null||relationids.length()==0) {
//			
//		}else {//通过序列化机制来反系列化成relationneo4j去除重复点和边
//			neo4jList=JSON.parseObject(relationids,new ArrayList<Map<String,String>>().getClass());
//			collection=Neo4jUtilServiceImpl.QueyApplyByContent(modelname, content,neo4jList);
//		}

//		DataSourceStringCollection collection=Neo4jUtilServiceImpl.QueyApplyByContent(modelname, content);
		StringBuilder sbStringBuilder=new StringBuilder();
		sbStringBuilder.append("{");
		sbStringBuilder.append("\"nodes\":");
		sbStringBuilder.append("[");
		StringBuilder sbmodelstr=new StringBuilder();//实体对应的json集合
		List<String> idList=new ArrayList<String>();
		List<String> eList=new ArrayList<String>();
		if(collection.modelList==null){
			return new StringBuilder();
		}
//		if(relationids!=null&&!relationids.equals("")) {
//			for(Map<String,String> model:neo4jList){
//				idList.add(model.get("target"));
//				idList.add(model.get("source"));
//				eList.add(model.get("id"));
//			}
//		}

		for(String modelstr:  collection.modelList){
			IMEILoanApplyNeo4j loanApplyNeo4j  = JSON.parseObject(modelstr, IMEILoanApplyNeo4j.class);
			if(!idList.contains(loanApplyNeo4j.getId()))
			{
				idList.add(loanApplyNeo4j.getId());
				IMEIApplyInfoV applyInfoV=Neo4jModelToIMEIModel.ApplyNeo4jToApplyV(loanApplyNeo4j);
				String json = JSONObject.toJSON(applyInfoV).toString();
				sbmodelstr.append(json);
					sbmodelstr.append(",");
			}
		}
		String modelstr="";
		if(sbmodelstr.length()>0)
		{
			modelstr=sbmodelstr.substring(0,sbmodelstr.length()-1);
		}
		sbStringBuilder.append(modelstr);
		sbStringBuilder.append("],");
		
		sbStringBuilder.append("\"links\":");
		sbStringBuilder.append("[");
		
		StringBuilder sbRelationstr=new StringBuilder();//关系对应的json集合
		
		for(String relationstr: collection.relationList){
			IMEIRelationNeo4j relationNeo4j= JSON.parseObject(relationstr, IMEIRelationNeo4j.class);
			if(!eList.contains(relationNeo4j.getId())){
				eList.add(relationNeo4j.getId());
				IMEIRelationE relationE = Neo4jModelToIMEIModel.RelationNeo4jToRelationE(relationNeo4j);
				String json = JSONObject.toJSON(relationE).toString();
				sbRelationstr.append(json);
				sbRelationstr.append(",");
			}
			
		}
		String relationStr="";
		if(sbmodelstr.length()>0)
		{
			relationStr=sbRelationstr.substring(0,sbRelationstr.length()-1);
		}
		sbStringBuilder.append(relationStr);
		sbStringBuilder.append("]");
		sbStringBuilder.append("}");
		return sbStringBuilder;
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
	/**
	 * 读取的数据写入文件
	 * @param content
	 * @param path
	 * @throws IOException 
	 */
	private static void writeDataToTxt(String content,String path)  {
		FileWriter fw = null;
		PrintWriter pw=null;
		try {
			//如果文件存在，则追加内容；如果文件不存在，则创建文件
			
			File f=new File(path);
			fw = new FileWriter(f, true);
			pw = new PrintWriter(fw);
			pw.println(content);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
			}
			if(fw!=null){
				try {
					fw.close();
				} catch (Exception e2) {
					System.out.println("writeDataToTxt finally:"+e2.getMessage());
				}
				
			}
			
		}
		
				
			
		}
	

	
	/**
	 * 通过订单号查找对应的关系，包括通讯录通话记录，设备，进件关系
	 * @param orderNo
	 * @return
	 */
	public static StringBuilder getApplyLoanInfo(String orderNo){
		IMEIDataSourceStringCollection collection0=Neo4jUtilServiceImpl.QueryApplyByApplyLevel0(orderNo);
		IMEIDataSourceStringCollection collection1=Neo4jUtilServiceImpl.QueryApplyByApplyLevel1(orderNo);
		IMEIDataSourceStringCollection collection2=Neo4jUtilServiceImpl.QueryApplyByApplyLevel2(orderNo);
		StringBuilder sbStringBuilder=new StringBuilder();
		sbStringBuilder.append("{");
		sbStringBuilder.append("\"nodes\":");
		sbStringBuilder.append("[");
		StringBuilder sbmodelstr=new StringBuilder();//实体对应的json集合
		List<String> idList=new ArrayList<String>();//所有id映射
		if(collection2.modelList!=null){
			for(String modelstr:  collection2.modelList){
	
				if(modelstr.contains(ModelType.APPLYINFO.getModelName())){//进件实体
					
					IMEILoanApplyNeo4j loanApplyNeo4j  = JSON.parseObject(modelstr, IMEILoanApplyNeo4j.class);
					if(!idList.contains(loanApplyNeo4j.getId()))
					{
						idList.add(loanApplyNeo4j.getId());
						IMEIApplyInfoV applyInfoV=Neo4jModelToIMEIModel.ApplyNeo4jToApplyV(loanApplyNeo4j);
						String json = JSONObject.toJSON(applyInfoV).toString();
						sbmodelstr.append(json);
						sbmodelstr.append(",");
					}
					
				}else {//除进件之外的实体
					IMEIModelNeo4j modelNeo4j  = JSON.parseObject(modelstr, IMEIModelNeo4j.class);
					if(!idList.contains(modelNeo4j.getId()))
					{
						idList.add(modelNeo4j.getId());
						IMEIModelV modelV=Neo4jModelToIMEIModel.ModelNeo4jToModelV(modelNeo4j);
						String json = JSONObject.toJSON(modelV).toString();
						sbmodelstr.append(json);
						sbmodelstr.append(",");
	
					}
				}
				
			}
		}
		
		if(collection1.modelList!=null){
			for(String modelstr:  collection1.modelList){
				if(modelstr.contains(ModelType.APPLYINFO.getModelName())){//进件实体
					System.err.println(modelstr);
					IMEILoanApplyNeo4j loanApplyNeo4j  = JSON.parseObject(modelstr, IMEILoanApplyNeo4j.class);
					if(!idList.contains(loanApplyNeo4j.getId()))
					{
						idList.add(loanApplyNeo4j.getId());
						IMEIApplyInfoV applyInfoV=Neo4jModelToIMEIModel.ApplyNeo4jToApplyV(loanApplyNeo4j);
						String json = JSONObject.toJSON(applyInfoV).toString();
						sbmodelstr.append(json);
						sbmodelstr.append(",");
					}
					
				}else {//除进件之外的实体
					IMEIModelNeo4j modelNeo4j  = JSON.parseObject(modelstr, IMEIModelNeo4j.class);
					if(!idList.contains(modelNeo4j.getId()))
					{
						idList.add(modelNeo4j.getId());
						IMEIModelV modelV=Neo4jModelToIMEIModel.ModelNeo4jToModelV(modelNeo4j);
						String json = JSONObject.toJSON(modelV).toString();
						sbmodelstr.append(json);
						sbmodelstr.append(",");
	
					}
				}
			}
		}
		if(collection0.modelList!=null){
			for(String modelstr:  collection0.modelList){
				if(modelstr.contains(ModelType.APPLYINFO.getModelName())){//进件实体
					IMEILoanApplyNeo4j loanApplyNeo4j  = JSON.parseObject(modelstr, IMEILoanApplyNeo4j.class);
					if(!idList.contains(loanApplyNeo4j.getId()))
					{
						idList.add(loanApplyNeo4j.getId());
						IMEIApplyInfoV applyInfoV=Neo4jModelToIMEIModel.ApplyNeo4jToApplyV(loanApplyNeo4j);
						String json = JSONObject.toJSON(applyInfoV).toString();
						sbmodelstr.append(json);
						sbmodelstr.append(",");
					}
					
				}else {//除进件之外的实体
					IMEIModelNeo4j modelNeo4j  = JSON.parseObject(modelstr, IMEIModelNeo4j.class);
					if(!idList.contains(modelNeo4j.getId()))
					{
						idList.add(modelNeo4j.getId());
						IMEIModelV modelV=Neo4jModelToIMEIModel.ModelNeo4jToModelV(modelNeo4j);
						String json = JSONObject.toJSON(modelV).toString();
						sbmodelstr.append(json);
						sbmodelstr.append(",");
	
					}
				}
			}
		}
		String sbModelstr="";
		if(sbmodelstr.length()>0){
		 sbModelstr=sbmodelstr.substring(0,sbmodelstr.length()-1);
		}
		sbStringBuilder.append(sbModelstr);
		sbStringBuilder.append("],");
		
		sbStringBuilder.append("\"links\":");
		sbStringBuilder.append("[");
		
		StringBuilder sbRelationstr=new StringBuilder();//关系对应的json集合

		List<String> eList=new ArrayList<String>();
		if(collection0.relationList!=null)
		{
			for(String relationstr: collection0.relationList){
				IMEIRelationNeo4j relationNeo4j= JSON.parseObject(relationstr, IMEIRelationNeo4j.class);
				if(!eList.contains(relationNeo4j.getId())){
					eList.add(relationNeo4j.getId());
					IMEIRelationE relationE = Neo4jModelToIMEIModel.RelationNeo4jToRelationE(relationNeo4j);
					String json = JSONObject.toJSON(relationE).toString();
					sbRelationstr.append(json);
					sbRelationstr.append(",");
					
				}
				
			}
		}
		
		if(collection1.relationList!=null)
		{
			for(String relationstr: collection1.relationList){
				IMEIRelationNeo4j relationNeo4j= JSON.parseObject(relationstr, IMEIRelationNeo4j.class);
				if(!eList.contains(relationNeo4j.getId())){
					eList.add(relationNeo4j.getId());
					IMEIRelationE relationE = Neo4jModelToIMEIModel.RelationNeo4jToRelationE(relationNeo4j);
					String json = JSONObject.toJSON(relationE).toString();
					sbRelationstr.append(json);
					sbRelationstr.append(",");
					
				}
				
			}
		}
		
		if(collection2.relationList!=null){
			for(String relationstr: collection2.relationList){
				IMEIRelationNeo4j relationNeo4j= JSON.parseObject(relationstr, IMEIRelationNeo4j.class);
				if(!eList.contains(relationNeo4j.getId())){
					eList.add(relationNeo4j.getId());
					IMEIRelationE relationE = Neo4jModelToIMEIModel.RelationNeo4jToRelationE(relationNeo4j);
					String json = JSONObject.toJSON(relationE).toString();
					sbRelationstr.append(json);
					sbRelationstr.append(",");
					
				}
				
			}
		}
		String relationStr="";
		if(sbRelationstr.length()>0)
		{
			relationStr=sbRelationstr.substring(0,sbRelationstr.length()-1);
		}
		sbStringBuilder.append(relationStr);
		sbStringBuilder.append("]");
		sbStringBuilder.append("}");
		
		return sbStringBuilder;
	}


}

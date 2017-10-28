package com.lakala.neo4j.importdata.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lakala.neo4j.importdata.dao.Neo4jHelperDao;
import com.lakala.neo4j.importdata.models.IMEILoanApplyNeo4j;

public class TestMain {
	private static List<String> listfile=new ArrayList<String>();
	public static void main(String[] args) {
		
//			System.out.println("Start handle data format!");
////			System.out.println("input args :"+args[0]);
////			//取得输入参数 
////			String[] argsarr=args[0].split(",");
//			//argsarr中第一个参数是文件路劲 第二个是ip地址
//			Neo4jHelperDao.url="bolt://192.168.0.33:7687";
//			//通过文件路劲读取数据
//			Neo4jHelperDao.executeInsert("create (a1:ApplyInfo {orderno:'TNH33458946564686848',mobile:'13819785611',deviceid:'000_111_555_13829785641',group:'3'})");
	
		
		
		
		
		String modelstr="{\"id\":526000, \"labels\":[\"ApplyInfo\"], \"education\":\"5\", \"target_amount\":\"1100000\", \"orderno\":\"AX20170120140133111439\", \"img_check_status\":\"1\", \"companytel\":\"031188279351\", \"modelname\":\"ApplyInfo\", \"emergency_relation\":\"2\", \"userid\":\"1014984484\", \"debitcard\":\"6217000130022781396\", \"apcr_names\":\"河北省|石家庄市|正定县\", \"emergencymobile\":\"15933101236\", \"pb_credit_limit\":\"1100000\", \"pb_risk_level\":\"1\", \"auth_status\":\"1\", \"id\":\"12910140\", \"contactmobile\":\"15932193178\", \"group\":\"1\", \"contact_relation\":\"1\", \"apcr_codes\":\"130000|130100|130123\", \"enterprise_nature\":\"6\", \"loan_bank_code\":\"01050000\", \"id_cert_addr\":\"新安镇新安村眺远路17号\", \"orgid\":\"orgcash\", \"cpcr_names\":\"河北省|石家庄市|正定县\", \"termid\":\"CBC3A11014984484\", \"companyname\":\"正定县腾跃摩托车商行\", \"return_pan\":\"6217000130022781396\", \"position\":\"7\", \"return_bank_code\":\"01050000\", \"fail_reason\":\"X69,\", \"productno\":\"PC03\", \"return_date\":\"2018-01-20 00:00:00.0\", \"status\":\"F\", \"credit_mode\":\"null\", \"flow_step_id\":\"4\", \"emergencyname\":\"李华兴\", \"capital_amount\":\"1100000\", \"loan_date\":\"2017-01-20 00:00:00.0\", \"group_code\":\"ED\", \"manual_auth_user\":\"-2\", \"insert_time\":\"2017-01-20 14:26:07.126\", \"industry\":\"6\", \"photo_img\":\"CASH/2017-01-20/AX20170120140133111439/3144A8BC7F5741FA9C101B154C5D700B.jpg\", \"contactname\":\"李艳明\", \"companyaddress\":\"新安车站信用社北邻宝岛电动专卖\", \"audit_time\":\"2017-01-20 14:27:22.863\", \"certno\":\"130123198610193344\", \"_deviceid\":\"864188032309971\", \"update_time\":\"2017-01-21 02:28:00.0\", \"body_credit_org\":\"J0000000019007\", \"ipv4\":\"223.104.13.57\", \"certtype\":\"1\", \"rate\":\"1800\", \"department\":\"销售部\", \"gift_amount\":\"0\", \"loan_pan\":\"6217000130022781396\", \"email\":\"841485912@qq.com\", \"tele_code\":\"100290024534645\", \"auth_type\":\"1 \", \"address\":\"新安镇新安村眺远路17号\", \"contractno\":\"XJA2017012012910140\", \"quarters\":\"销售部经理\", \"certsecond_img\":\"CASH/2017-01-20/AX20170120140133111439/A2039BD7C3D54FD9BB5CFD827BE59644.jpg\", \"work_flow_id\":\"14\", \"mobile\":\"13784398007\", \"certfirstimg\":\"CASH/2017-01-20/AX20170120140133111439/26CBA691C7EF42CC8CFE456549BD10B5.jpg\", \"deviceid\":\"864188032309971\", \"creditcard\":\"6217000130022781396\", \"migrate_status\":\"SUCCESSFUL\", \"manual_auth_status\":\"S\", \"realname\":\"李冲冲\", \"businessno\":\"CASH\", \"pan_auth_status\":\"T\", \"cpcr_codes\":\"130000|130100|130123\", \"channel_source\":\"Z\", \"worktime\":\"6\", \"married\":\"1\", \"istarget\":\"1\", \"incomerange\":\"2 \"}";
		try {
			IMEILoanApplyNeo4j loanApplyNeo4j  = JSON.parseObject(modelstr, IMEILoanApplyNeo4j.class);
			System.err.println(JSONObject.toJSON(loanApplyNeo4j));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		
		
		}
		

		
	}


package com.lakala.neo4j.importdata.utils;

import com.lakala.neo4j.importdata.models.IMEIApplyInfoModel;

public class ApplyInfoUtils {
	
	/**
	 * 将java对象转换成逗号分隔的csv格式字符串
	 * @return
	 */
	public static StringBuffer objectToString(IMEIApplyInfoModel model){
		StringBuffer appstr=new StringBuffer();
		if(model.getManageradvise()==null|| model.getManageradvise().equals("")|| model.getManageradvise().equals("NULL")|| model.getManageradvise().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getManageradvise()+",");
		}

		if(model.get_platform()==null|| model.get_platform().equals("")|| model.get_platform().equals("NULL")|| model.get_platform().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.get_platform()+",");
		}

		if(model.getCertsecondimg()==null|| model.getCertsecondimg().equals("")|| model.getCertsecondimg().equals("NULL")|| model.getCertsecondimg().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getCertsecondimg()+",");
		}

		if(model.getPartneraccountbank()==null|| model.getPartneraccountbank().equals("")|| model.getPartneraccountbank().equals("NULL")|| model.getPartneraccountbank().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getPartneraccountbank()+",");
		}

		if(model.getUserid()==null|| model.getUserid().equals("")|| model.getUserid().equals("NULL")|| model.getUserid().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getUserid()+",");
		}

		if(model.getOrderno()==null|| model.getOrderno().equals("")|| model.getOrderno().equals("NULL")|| model.getOrderno().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getOrderno()+",");
		}

		if(model.getCertfirstimg()==null|| model.getCertfirstimg().equals("")|| model.getCertfirstimg().equals("NULL")|| model.getCertfirstimg().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getCertfirstimg()+",");
		}

		if(model.getPartnerid()==null|| model.getPartnerid().equals("")|| model.getPartnerid().equals("NULL")|| model.getPartnerid().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getPartnerid()+",");
		}

		if(model.getSchname()==null|| model.getSchname().equals("")|| model.getSchname().equals("NULL")|| model.getSchname().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getSchname()+",");
		}

		if(model.getUsername()==null|| model.getUsername().equals("")|| model.getUsername().equals("NULL")|| model.getUsername().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getUsername()+",");
		}

		if(model.getMarried()==null|| model.getMarried().equals("")|| model.getMarried().equals("NULL")||model.getMarried().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getMarried()+",");
		}

		if(model.getSchcity()==null|| model.getSchcity().equals("")|| model.getSchcity().equals("NULL")|| model.getSchcity().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getSchcity()+",");
		}

		if(model.getEmergencymobile()==null|| model.getEmergencymobile().equals("")|| model.getEmergencymobile().equals("NULL")|| model.getEmergencymobile().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getEmergencymobile()+",");
		}

		if(model.getIncome()==null|| model.getIncome().equals("")|| model.getIncome().equals("NULL")|| model.getIncome().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getIncome()+",");
		}

		if(model.getMerchanttel()==null|| model.getMerchanttel().equals("")|| model.getMerchanttel().equals("NULL")|| model.getMerchanttel().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getMerchanttel()+",");
		}

		if(model.getHostname()==null|| model.getHostname().equals("")|| model.getHostname().equals("NULL")|| model.getHostname().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getHostname()+",");
		}

		if(model.getHighestlevel()==null|| model.getHighestlevel().equals("")|| model.getHighestlevel().equals("NULL")|| model.getHighestlevel().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getHighestlevel()+",");
		}

		if(model.getIndustry()==null|| model.getIndustry().equals("")|| model.getIndustry().equals("NULL")|| model.getIndustry().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getIndustry()+",");
		}

		if(model.getUsermobile()==null|| model.getUsermobile().equals("")|| model.getUsermobile().equals("NULL")|| model.getUsermobile().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getUsermobile()+",");
		}

		if(model.getChannelid()==null|| model.getChannelid().equals("")|| model.getChannelid().equals("NULL")|| model.getChannelid().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getChannelid()+",");
		}

		if(model.getBusinesstype()==null|| model.getBusinesstype().equals("")|| model.getBusinesstype().equals("NULL")|| model.getBusinesstype().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getBusinesstype()+",");
		}


		if(model.getApplyamt()==null|| model.getApplyamt().equals("")|| model.getApplyamt().equals("NULL")|| model.getApplyamt().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getApplyamt()+",");
		}


		if(model.getPartnercontantmobile()==null|| model.getPartnercontantmobile().equals("")|| model.getPartnercontantmobile().equals("NULL")|| model.getPartnercontantmobile().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getPartnercontantmobile()+",");
		}

		if(model.getPartneraccounttype()==null|| model.getPartneraccounttype().equals("")|| model.getPartneraccounttype().equals("NULL")|| model.getPartneraccounttype().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getPartneraccounttype()+",");
		}

		if(model.getMacodes()==null|| model.getMacodes().equals("")|| model.getMacodes().equals("NULL")|| model.getMacodes().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getMacodes()+",");
		}

		if(model.getOcrcertno()==null|| model.getOcrcertno().equals("")|| model.getOcrcertno().equals("NULL")|| model.getOcrcertno().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getOcrcertno()+",");
		}

		if(model.getEntnature()==null|| model.getEntnature().equals("")|| model.getEntnature().equals("NULL")|| model.getEntnature().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getEntnature()+",");
		}
		if(model.getUuid()==null|| model.getUuid().equals("")|| model.getUuid().equals("NULL")|| model.getUuid().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getUuid()+",");
		}
		if(model.getCompanyno()==null|| model.getCompanyno().equals("")|| model.getCompanyno().equals("NULL")|| model.getCompanyno().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getCompanyno()+",");
		}

		if(model.getBusid()==null|| model.getBusid().equals("")|| model.getBusid().equals("NULL")|| model.getBusid().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getBusid()+",");
		}

		if(model.getPosition()==null|| model.getPosition().equals("")|| model.getPosition().equals("NULL")|| model.getPosition().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getPosition()+",");
		}

		if(model.getContactmobile()==null|| model.getContactmobile().equals("")|| model.getContactmobile().equals("NULL")|| model.getContactmobile().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getContactmobile()+",");
	}
		if(model.getGetcontacts()==null|| model.getGetcontacts().equals("")|| model.getGetcontacts().equals("NULL")|| model.getGetcontacts().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getGetcontacts()+",");
	}

		if(model.getBiztypeenum()==null|| model.getBiztypeenum().equals("")|| model.getBiztypeenum().equals("NULL")|| model.getBiztypeenum().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getBiztypeenum()+",");
	}

		if(model.getCertno()==null|| model.getCertno().equals("")|| model.getCertno().equals("NULL")|| model.getCertno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCertno()+",");
	}

		if(model.getModelno()==null|| model.getModelno().equals("")|| model.getModelno().equals("NULL")|| model.getModelno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getModelno()+",");
	}

		if(model.getManames()==null|| model.getManames().equals("")|| model.getManames().equals("NULL")|| model.getManames().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getManames()+",");
	}

		if(model.getWechatno()==null|| model.getWechatno().equals("")|| model.getWechatno().equals("NULL")|| model.getWechatno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getWechatno()+",");
	}
		if(model.getDebitbank()==null|| model.getDebitbank().equals("")|| model.getDebitbank().equals("NULL")|| model.getDebitbank().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getDebitbank()+",");
	}

		if(model.getPartnercontantemail()==null|| model.getPartnercontantemail().equals("")|| model.getPartnercontantemail().equals("NULL")|| model.getPartnercontantemail().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getPartnercontantemail()+",");
	}

		if(model.getBranchid()==null|| model.getBranchid().equals("")|| model.getBranchid().equals("NULL")|| model.getBranchid().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getBranchid()+",");
	}

		if(model.getQuarters()==null|| model.getQuarters().equals("")|| model.getQuarters().equals("NULL")|| model.getQuarters().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getQuarters()+",");
	}

		if(model.getToken()==null|| model.getToken().equals("")|| model.getToken().equals("NULL")|| model.getToken().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getToken()+",");
	}
		if(model.getCooporgno()==null|| model.getCooporgno().equals("")|| model.getCooporgno().equals("NULL")|| model.getCooporgno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCooporgno()+",");
	}
		if(model.getCreditcard()==null|| model.getCreditcard().equals("")|| model.getCreditcard().equals("NULL")|| model.getCreditcard().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCreditcard()+",");
	}
		if(model.getCredtype()==null|| model.getCredtype().equals("")|| model.getCredtype().equals("NULL")|| model.getCredtype().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCredtype()+",");
	}

		if(model.getTelecode()==null|| model.getTelecode().equals("")|| model.getTelecode().equals("NULL")|| model.getTelecode().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getTelecode()+",");
	}
		if(model.getApcrnames()==null|| model.getApcrnames().equals("")|| model.getApcrnames().equals("NULL")|| model.getApcrnames().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getApcrnames()+",");
		}

		if(model.getHometel()==null|| model.getHometel().equals("")|| model.getHometel().equals("NULL")|| model.getHometel().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getHometel()+",");
		}

		if(model.getGiftid()==null|| model.getGiftid().equals("")|| model.getGiftid().equals("NULL")|| model.getGiftid().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getGiftid()+",");
		}

		if(model.getSid()==null|| model.getSid().equals("")|| model.getSid().equals("NULL")|| model.getSid().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getSid()+",");
		}

		if(model.getGraddate()==null|| model.getGraddate().equals("")|| model.getGraddate().equals("NULL")|| model.getGraddate().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getGraddate()+",");
		}

		if(model.getSystemcode()==null|| model.getSystemcode().equals("")|| model.getSystemcode().equals("NULL")|| model.getSystemcode().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getSystemcode()+",");
		}

		if(model.getCurcode()==null|| model.getCurcode().equals("")|| model.getCurcode().equals("NULL")|| model.getCurcode().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getCurcode()+",");
		}

		if(model.getPartnercontant()==null|| model.getPartnercontant().equals("")|| model.getPartnercontant().equals("NULL")|| model.getPartnercontant().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getPartnercontant()+",");
		}

		if(model.getMerchantorderexptime()==null|| model.getMerchantorderexptime().equals("")|| model.getMerchantorderexptime().equals("NULL")|| model.getMerchantorderexptime().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getMerchantorderexptime()+",");
		}

		if(model.getGifttips()==null|| model.getGifttips().equals("")|| model.getGifttips().equals("NULL")|| model.getGifttips().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getGifttips()+",");
		}

		if(model.getGiftfeecode()==null|| model.getGiftfeecode().equals("")|| model.getGiftfeecode().equals("NULL")|| model.getGiftfeecode().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getGiftfeecode()+",");
		}

		if(model.getCompanyname()==null|| model.getCompanyname().equals("")|| model.getCompanyname().equals("NULL")|| model.getCompanyname().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getCompanyname()+",");
		}

		if(model.getProcess()==null|| model.getProcess().equals("")|| model.getProcess().equals("NULL")|| model.getProcess().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getProcess()+",");
	}

		if(model.getUserapcrcodes()==null|| model.getUserapcrcodes().equals("")|| model.getUserapcrcodes().equals("NULL")|| model.getUserapcrcodes().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getUserapcrcodes()+",");
	}

		if(model.getSchdorm()==null|| model.getSchdorm().equals("")|| model.getSchdorm().equals("NULL")|| model.getSchdorm().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getSchdorm()+",");
	}

		if(model.getCollecttime()==null|| model.getCollecttime().equals("")|| model.getCollecttime().equals("NULL")|| model.getCollecttime().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCollecttime()+",");
	}

		if(model.getReceivetime()==null|| model.getReceivetime().equals("")|| model.getReceivetime().equals("NULL")|| model.getReceivetime().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getReceivetime()+",");
	}

		if(model.getPartneraddr()==null|| model.getPartneraddr().equals("")|| model.getPartneraddr().equals("NULL")|| model.getPartneraddr().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getPartneraddr()+",");
	}
		if(model.getMerchantname()==null|| model.getMerchantname().equals("")|| model.getMerchantname().equals("NULL")|| model.getMerchantname().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMerchantname()+",");
	}

		if(model.getProductno()==null|| model.getProductno().equals("")|| model.getProductno().equals("NULL")|| model.getProductno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getProductno()+",");
	}

		if(model.getOcrname()==null|| model.getOcrname().equals("")|| model.getOcrname().equals("NULL")|| model.getOcrname().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getOcrname()+",");
	}

		if(model.getFcatschname()==null|| model.getFcatschname().equals("")|| model.getFcatschname().equals("NULL")|| model.getFcatschname().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getFcatschname()+",");
	}
		if(model.getHostip()==null|| model.getHostip().equals("")|| model.getHostip().equals("NULL")|| model.getHostip().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getHostip()+",");
	}

		if(model.getContactrelation()==null|| model.getContactrelation().equals("")|| model.getContactrelation().equals("NULL")|| model.getContactrelation().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getContactrelation()+",");
	}

		if(model.getAuthpwd()==null|| model.getAuthpwd().equals("")|| model.getAuthpwd().equals("NULL")|| model.getAuthpwd().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getAuthpwd()+",");
	}

		if(model.get_macwifi()==null|| model.get_macwifi().equals("")|| model.get_macwifi().equals("NULL")|| model.get_macwifi().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.get_macwifi()+",");
	}

		if(model.getPartnercompany()==null|| model.getPartnercompany().equals("")|| model.getPartnercompany().equals("NULL")|| model.getPartnercompany().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getPartnercompany()+",");
	}

		if(model.getUserapcrnames()==null|| model.getUserapcrnames().equals("")|| model.getUserapcrnames().equals("NULL")|| model.getUserapcrnames().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getUserapcrnames()+",");
	}

		if(model.getUsercoremobile()==null|| model.getUsercoremobile().equals("")|| model.getUsercoremobile().equals("NULL")|| model.getUsercoremobile().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getUsercoremobile()+",");
	}

		if(model.get_deviceid()==null|| model.get_deviceid().equals("")|| model.get_deviceid().equals("NULL")|| model.get_deviceid().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.get_deviceid()+",");
	}
		if(model.getAuthname()==null|| model.getAuthname().equals("")|| model.getAuthname().equals("NULL")|| model.getAuthname().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getAuthname()+",");
	}

		if(model.getLbsaddress()==null|| model.getLbsaddress().equals("")|| model.getLbsaddress().equals("NULL")|| model.getLbsaddress().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getLbsaddress()+",");
	}

		if(model.getRegisterorg()==null|| model.getRegisterorg().equals("")|| model.getRegisterorg().equals("NULL")|| model.getRegisterorg().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getRegisterorg()+",");
	}

		if(model.getGiftfeetype()==null|| model.getGiftfeetype().equals("")|| model.getGiftfeetype().equals("NULL")|| model.getGiftfeetype().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getGiftfeetype()+",");
	}

		if(model.getRequest()==null|| model.getRequest().equals("")|| model.getRequest().equals("NULL")|| model.getRequest().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getRequest()+",");
	}

		if(model.getCreditmode()==null|| model.getCreditmode().equals("")|| model.getCreditmode().equals("NULL")|| model.getCreditmode().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCreditmode()+",");
	}

		if(model.getBrithday()==null|| model.getBrithday().equals("")|| model.getBrithday().equals("NULL")|| model.getBrithday().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getBrithday()+",");
	}
		if(model.getFingerprinttab()==null|| model.getFingerprinttab().equals("")|| model.getFingerprinttab().equals("NULL")|| model.getFingerprinttab().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getFingerprinttab()+",");
	}
		if(model.getIdentitycode()==null|| model.getIdentitycode().equals("")|| model.getIdentitycode().equals("NULL")|| model.getIdentitycode().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getIdentitycode()+",");
	}

		if(model.getPaymode()==null|| model.getPaymode().equals("")|| model.getPaymode().equals("NULL")|| model.getPaymode().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getPaymode()+",");
	}
		if(model.getSmscode()==null|| model.getSmscode().equals("")|| model.getSmscode().equals("NULL")|| model.getSmscode().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getSmscode()+",");
	}

		if(model.getCredit_productno()==null|| model.getCredit_productno().equals("")|| model.getCredit_productno().equals("NULL")|| model.getCredit_productno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCredit_productno()+",");
	}

		if(model.getGifttype()==null|| model.getGifttype().equals("")|| model.getGifttype().equals("NULL")|| model.getGifttype().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getGifttype()+",");
	}

		if(model.getCpcrcodes()==null|| model.getCpcrcodes().equals("")|| model.getCpcrcodes().equals("NULL")|| model.getCpcrcodes().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCpcrcodes()+",");
	}

		if(model.getMerchantordertime()==null|| model.getMerchantordertime().equals("")|| model.getMerchantordertime().equals("NULL")|| model.getMerchantordertime().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMerchantordertime()+",");
	}

		if(model.getNation()==null|| model.getNation().equals("")|| model.getNation().equals("NULL")|| model.getNation().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getNation()+",");
	}

		if(model.getMerchantid()==null|| model.getMerchantid().equals("")|| model.getMerchantid().equals("NULL")|| model.getMerchantid().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMerchantid()+",");
	}

		if(model.getResitemno()==null|| model.getResitemno().equals("")|| model.getResitemno().equals("NULL")|| model.getResitemno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getResitemno()+",");
	}

		if(model.getSchyear()==null|| model.getSchyear().equals("")|| model.getSchyear().equals("NULL")|| model.getSchyear().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getSchyear()+",");
	}

		if(model.getUserfullife()==null|| model.getUserfullife().equals("")|| model.getUserfullife().equals("NULL")|| model.getUserfullife().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getUserfullife()+",");
	}

		if(model.getPartnername()==null|| model.getPartnername().equals("")|| model.getPartnername().equals("NULL")|| model.getPartnername().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getPartnername()+",");
	}

		if(model.getSessionid()==null|| model.getSessionid().equals("")|| model.getSessionid().equals("NULL")|| model.getSessionid().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getSessionid()+",");
	}

		if(model.getSchcitycode()==null|| model.getSchcitycode().equals("")|| model.getSchcitycode().equals("NULL")|| model.getSchcitycode().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getSchcitycode()+",");
	}

		if(model.getEmail()==null|| model.getEmail().equals("")|| model.getEmail().equals("NULL")|| model.getEmail().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getEmail()+",");
	}

		if(model.getRpflag()==null|| model.getRpflag().equals("")|| model.getRpflag().equals("NULL")|| model.getRpflag().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getRpflag()+",");
	}
		if(model.getApplicant()==null|| model.getApplicant().equals("")|| model.getApplicant().equals("NULL")|| model.getApplicant().equals("null")){
			appstr.append(",");
		}else
	{
			appstr.append(model.getApplicant()+",");
	}

		if(model.getBiztype()==null|| model.getBiztype().equals("")|| model.getBiztype().equals("NULL")|| model.getBiztype().equals("null")){
			appstr.append(",");
		}else
	{
			appstr.append(model.getBiztype()+",");
	}

		if(model.getMerchantno()==null|| model.getMerchantno().equals("")|| model.getMerchantno().equals("NULL")|| model.getMerchantno().equals("null")){
			appstr.append(",");
		}else
	{
			appstr.append(model.getMerchantno()+",");
	}

		if(model.getDebitcard()==null|| model.getDebitcard().equals("")|| model.getDebitcard().equals("NULL")|| model.getDebitcard().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getDebitcard()+",");
	}

		if(model.getMerchantmobile()==null|| model.getMerchantmobile().equals("")|| model.getMerchantmobile().equals("NULL")|| model.getMerchantmobile().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMerchantmobile()+",");
	}

		if(model.getChannelenum()==null|| model.getChannelenum().equals("")|| model.getChannelenum().equals("NULL")|| model.getChannelenum().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getChannelenum()+",");
	}

		if(model.getPurseid()==null|| model.getPurseid().equals("")|| model.getPurseid().equals("NULL")|| model.getPurseid().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getPurseid()+",");
	}

		if(model.getMobile()==null|| model.getMobile().equals("")|| model.getMobile().equals("NULL")|| model.getMobile().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMobile()+",");
	}

		if(model.getIpv4()==null|| model.getIpv4().equals("")|| model.getIpv4().equals("NULL")|| model.getIpv4().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getIpv4()+",");
	}
		if(model.getSex()==null|| model.getSex().equals("")|| model.getSex().equals("NULL")|| model.getSex().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getSex()+",");
	}

		if(model.getConsumtype()==null|| model.getConsumtype().equals("")|| model.getConsumtype().equals("NULL")|| model.getConsumtype().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getConsumtype()+",");
	}
		if(model.getDepartment()==null|| model.getDepartment().equals("")|| model.getDepartment().equals("NULL")|| model.getDepartment().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getDepartment()+",");
	}

		if(model.getMerchantorderno()==null|| model.getMerchantorderno().equals("")|| model.getMerchantorderno().equals("NULL")|| model.getMerchantorderno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMerchantorderno()+",");
	}

		if(model.getEmergencyrelation()==null|| model.getEmergencyrelation().equals("")|| model.getEmergencyrelation().equals("NULL")|| model.getEmergencyrelation().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getEmergencyrelation()+",");
	}

		if(model.getPartneraccounter()==null|| model.getPartneraccounter().equals("")|| model.getPartneraccounter().equals("NULL")|| model.getPartneraccounter().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getPartneraccounter()+",");
	}

		if(model.getApcrcodes()==null|| model.getApcrcodes().equals("")|| model.getApcrcodes().equals("NULL")|| model.getApcrcodes().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getApcrcodes()+",");
	}

		if(model.getRecommend()==null|| model.getRecommend().equals("")|| model.getRecommend().equals("NULL")|| model.getRecommend().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getRecommend()+",");
	}

		if(model.getMerchantregistertime()==null|| model.getMerchantregistertime().equals("")|| model.getMerchantregistertime().equals("NULL")|| model.getMerchantregistertime().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMerchantregistertime()+",");
	}

		if(model.getGiftno()==null|| model.getGiftno().equals("")|| model.getGiftno().equals("NULL")|| model.getGiftno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getGiftno()+",");
	}

		if(model.getCreditbank()==null|| model.getCreditbank().equals("")|| model.getCreditbank().equals("NULL")|| model.getCreditbank().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCreditbank()+",");
	}
		if(model.getChanno()==null|| model.getChanno().equals("")|| model.getChanno().equals("NULL")|| model.getChanno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getChanno()+",");
	}

		if(model.getCpcrnames()==null|| model.getCpcrnames().equals("")|| model.getCpcrnames().equals("NULL")|| model.getCpcrnames().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCpcrnames()+",");
	}

		if(model.getBizno()==null|| model.getBizno().equals("")|| model.getBizno().equals("NULL")|| model.getBizno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getBizno()+",");
	}
		if(model.getSchroll()==null|| model.getSchroll().equals("")|| model.getSchroll().equals("NULL")|| model.getSchroll().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getSchroll()+",");
	}

		if(model.getQq()==null|| model.getQq().equals("")|| model.getQq().equals("NULL")|| model.getQq().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getQq()+",");
	}
		if(model.getEmergencyname()==null|| model.getEmergencyname().equals("")|| model.getEmergencyname().equals("NULL")|| model.getEmergencyname().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getEmergencyname()+",");
	}

		if(model.get_macbluetooth()==null|| model.get_macbluetooth().equals("")|| model.get_macbluetooth().equals("NULL")|| model.get_macbluetooth().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.get_macbluetooth()+",");
	}
		if(model.getLogno()==null|| model.getLogno().equals("")|| model.getLogno().equals("NULL")|| model.getLogno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getLogno()+",");
	}
		if(model.getBranchcomp()==null|| model.getBranchcomp().equals("")|| model.getBranchcomp().equals("NULL")|| model.getBranchcomp().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getBranchcomp()+",");
	}

		if(model.getTermid()==null|| model.getTermid().equals("")|| model.getTermid().equals("NULL")|| model.getTermid().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getTermid()+",");
	}

		if(model.getCompanyaddress()==null|| model.getCompanyaddress().equals("")|| model.getCompanyaddress().equals("NULL")|| model.getCompanyaddress().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCompanyaddress()+",");
	}

		if(model.getMerchantaddress()==null|| model.getMerchantaddress().equals("")|| model.getMerchantaddress().equals("NULL")|| model.getMerchantaddress().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMerchantaddress()+",");
	}

		if(model.getUserno()==null|| model.getUserno().equals("")|| model.getUserno().equals("NULL")|| model.getUserno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getUserno()+",");
	}
		if(model.getPartneraccount()==null|| model.getPartneraccount().equals("")|| model.getPartneraccount().equals("NULL")|| model.getPartneraccount().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getPartneraccount()+",");
	}

		if(model.getMchno()==null|| model.getMchno().equals("")|| model.getMchno().equals("NULL")|| model.getMchno().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getMchno()+",");
	}

		if(model.getAddress()==null|| model.getAddress().equals("")|| model.getAddress().equals("NULL")|| model.getAddress().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getAddress()+",");
	}

		if(model.getContactname()==null|| model.getContactname().equals("")|| model.getContactname().equals("NULL")|| model.getContactname().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getContactname()+",");
	}
		if(model.getRegistered()==null|| model.getRegistered().equals("")|| model.getRegistered().equals("NULL")|| model.getRegistered().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getRegistered()+",");
	}
		if(model.getNowphoto()==null|| model.getNowphoto().equals("")|| model.getNowphoto().equals("NULL")|| model.getNowphoto().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getNowphoto()+",");
	}

		if(model.getConsumapplyid()==null|| model.getConsumapplyid().equals("")|| model.getConsumapplyid().equals("NULL")|| model.getConsumapplyid().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getConsumapplyid()+",");
	}

		if(model.getTips()==null|| model.getTips().equals("")|| model.getTips().equals("NULL")|| model.getTips().equals("null")){
				appstr.append(",");
		}else
		{
				appstr.append(model.getTips()+",");
		}

		if(model.getCompanytel()==null|| model.getCompanytel().equals("")|| model.getCompanytel().equals("NULL")|| model.getCompanytel().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getCompanytel()+",");
	}

		if(model.getWorktime()==null|| model.getWorktime().equals("")|| model.getWorktime().equals("NULL")|| model.getWorktime().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getWorktime()+",");
	}


		if(model.getChannelcode()==null|| model.getChannelcode().equals("")|| model.getChannelcode().equals("NULL")|| model.getChannelcode().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getChannelcode()+",");
	}


		if(model.getChannelsource()==null|| model.getChannelsource().equals("")|| model.getChannelsource().equals("NULL")|| model.getChannelsource().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getChannelsource()+",");
	}

		if(model.getAuthenticationflag()==null|| model.getAuthenticationflag().equals("")|| model.getAuthenticationflag().equals("NULL")|| model.getAuthenticationflag().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getAuthenticationflag()+",");
	}

		if(model.getChannelmobile()==null|| model.getChannelmobile().equals("")|| model.getChannelmobile().equals("NULL")|| model.getChannelmobile().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getChannelmobile()+",");
	}

//		if(model.getLbs()==null|| model.getLbs().equals("")|| model.getLbs().equals("NULL")|| model.getLbs().equals("null")){
//			appstr.append(",");
//	}else
//	{
//			appstr.append(model.getLbs()+",");
//	}
		if(model.getLbsx()==null|| model.getLbsx().equals("")|| model.getLbsx().equals("NULL")|| model.getLbsx().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getLbsx()+",");
	}


		if(model.getLbsy()==null|| model.getLbsy().equals("")|| model.getLbsy().equals("NULL")|| model.getLbsy().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getLbsy()+",");
	}

		if(model.getLbsanalyzed()==null|| model.getLbsanalyzed().equals("")|| model.getLbsanalyzed().equals("NULL")|| model.getLbsanalyzed().equals("null")){
			appstr.append(",");
	}else
	{
			appstr.append(model.getLbsanalyzed()+",");
	}


		if(model.getReceivetimefmt()==null|| model.getReceivetimefmt().equals("")|| model.getReceivetimefmt().equals("NULL")|| model.getReceivetimefmt().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getReceivetimefmt()+",");
		}

		if(model.getCollecttimefmt()==null|| model.getCollecttimefmt().equals("")|| model.getCollecttimefmt().equals("NULL")|| model.getCollecttimefmt().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getCollecttimefmt()+",");
		}
		if(model.getBelongprovince()==null|| model.getBelongprovince().equals("")|| model.getBelongprovince().equals("NULL")|| model.getBelongprovince().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getBelongprovince()+",");
		}
		if(model.getProtocolversion()==null|| model.getProtocolversion().equals("")|| model.getProtocolversion().equals("NULL")|| model.getProtocolversion().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getProtocolversion()+",");
		}
		if(model.getOperatetypeenum()==null|| model.getOperatetypeenum().equals("")|| model.getOperatetypeenum().equals("NULL")|| model.getOperatetypeenum().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getOperatetypeenum()+",");
		}
		if(model.getOperatetype()==null|| model.getOperatetype().equals("")|| model.getOperatetype().equals("NULL")|| model.getOperatetype().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getOperatetype()+",");
		}
		if(model.getBelongregion()==null|| model.getBelongregion().equals("")|| model.getBelongregion().equals("NULL")|| model.getBelongregion().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getBelongregion()+",");
		}
		if(model.getBelongcity()==null|| model.getBelongcity().equals("")|| model.getBelongcity().equals("NULL")|| model.getBelongcity().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getBelongcity()+",");
		}
		if(model.getVer()==null|| model.getVer().equals("")|| model.getVer().equals("NULL")|| model.getVer().equals("null")){
			appstr.append(",");
		}else
		{
			appstr.append(model.getVer()+",");
		}

		if(model.getMsgmobile()==null||model.getMsgmobile().equals("")||model.getMsgmobile().equals("NULL")||model.getMsgmobile().equals("null")){
			appstr.append(",");
		}else
		{
		appstr.append(model.getMsgmobile()+",");
		}
		return appstr;
	}

}

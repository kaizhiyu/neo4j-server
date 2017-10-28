package com.lakala.contactlist.batch.query.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.lakala.antifraud.dubbo.api.common.DataProductSaveFacade;
import com.lakala.antifraud.model.constant.MerchantNo;
import com.lakala.antifraud.model.constant.OutSide;
import com.lakala.antifraud.model.constant.SystemCode;
import com.lakala.antifraud.model.request.BizRequestParameters;
import com.lakala.antifraud.model.request.DubboRequest;
import com.lakala.antifraud.model.request.InterfaceRequestParameters;
import com.lakala.antifraud.model.response.DubboResponse;
import com.lakala.contactlist.batch.query.service.ExcelBatchImportService;
import com.lakala.creditloan.manager.dto.BlackParams;
import com.lakala.creditloan.manager.model.Blacklist;
import com.lakala.creditloan.manager.provider.BlackManagerProviderService;

/**
 * 
 * <P>
 * 反查项目黑名单跑批
 * </P>
 * 
 * @author 郭志杰 2017年03月01日 下午4:09:04
 * @since 1.0.0.000
 */
@Service
public class BlacklistImportImpl implements ExcelBatchImportService {
	private static final Logger logger = LoggerFactory.getLogger(BlacklistImportImpl.class);

	@Autowired
	private BlackManagerProviderService blackManagerProviderService;
	@Autowired
	private DataProductSaveFacade dataProductSaveFacade;
	
	@Override
	public void batchImport() {
		boolean isinsert = true;
		int i = 0;
		int page = 0;
		int pagesize = 50;
		while (isinsert) {
			i++;
			page++;
			logger.info("===============pagesize:" + pagesize + "===================page:" + i);
			try {
				BlackParams blackParams = new BlackParams();
				blackParams.setPage(page);// 页码
				blackParams.setRows(pagesize);// 每页显示多少条记录
				Map map = blackManagerProviderService.getPageData(blackParams);
				List<Blacklist> blacklist = (List<Blacklist>) map.get("rows");
				logger.info("++++++++++++++++++++++++++pagecount:" + map.get("total") + "++++++++++");
				logger.info("++++++++++++++++++++++++++blacklist size:" + blacklist.size() + "++++++++++");
				if (blacklist != null) {
					for (Blacklist black : blacklist) {
						blacklistSave(black);
					}
				}
				if (blacklist.size() < pagesize)
					return;
				Thread.sleep(2000);
			} catch (Exception e) {
				logger.error("batchImport:" + e.getMessage());
				return;
			}
		}
	}

	private void blacklistSave(Blacklist black) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			// map.put("black",
			// "{\"productNo\":\"Black\",\"applyId\":\"\",\"useRange\":1,\"hostIp\":\"192.168.0.115\",\"modifierName\":\"超级管理员\",\"bizTypeEnum\":\"BizTypeEnum_INTERFACE\",\"contentType\":1,\"type\":1,\"id\":529,\"modelNo\":\"Black\",\"useRangeValue\":\"理财\",\"operateTypeEnum\":\"OperateTypeEnum_MODIFY\",\"originValue\":\"所有\",\"listTypeValue\":\"单位\",\"hostName\":\"wanli-PC\",\"bizNo\":\"Black\",\"riskTypeValue\":\"第三方要求加黑\",\"logNo\":\"RiskControl_Black_Manage\",\"operateType\":\"MODIFY\",\"channelId\":\"PC\",\"systemCode\":\"RiskControl\",\"listType\":1,\"origin\":1,\"listDesc\":\"测试ssss\",\"riskType\":\"7\",\"contentTypeValue\":\"手机号\",\"content\":\"15969943135\",\"process\":\"8272@wanli-PC\",\"protocolVersion\":\"1.0.0\",\"collectTime\":1484213241062,\"sessionId\":\"18b1a83b-9e3a-49ab-8b79-cf11375608db\",\"validTime\":\"2017-01-10\",\"receiveTime\":1484213230706,\"bizType\":\"INTERFACE\",\"merchantNo\":\"RiskControl\",\"typeValue\":\"黑名单\",\"channelEnum\":\"ChannelEnum_PC\"}");
			// map.put("black",
			// "{\"productNo\":\"Black\",\"applyId\":\"\",\"useRange\":3,\"hostIp\":\"192.168.20.115\",\"modifierName\":\"超级管理员\",\"bizTypeEnum\":\"BizTypeEnum_INTERFACE\",\"contentType\":1,\"type\":1,\"id\":529,\"modelNo\":\"Black\",\"useRangeValue\":\"理财\",\"operateTypeEnum\":\"OperateTypeEnum_MODIFY\",\"originValue\":\"同盾\",\"listTypeValue\":\"单位\",\"hostName\":\"wanli-PC\",\"bizNo\":\"Black\",\"riskTypeValue\":\"第三方要求加黑\",\"logNo\":\"RiskControl_Black_Manage\",\"operateType\":\"MODIFY\",\"channelId\":\"PC\",\"systemCode\":\"RiskControl\",\"listType\":1,\"origin\":1,\"listDesc\":\"测试ssss\",\"riskType\":\"7\",\"contentTypeValue\":\"手机号\",\"content\":\"1555555555\",\"process\":\"8272@wanli-PC\",\"protocolVersion\":\"1.0.0\",\"collectTime\":1484213241062,\"sessionId\":\"18b1a83b-9e3a-49ab-8b79-cf11375608db\",\"validTime\":\"2017-01-10\",\"receiveTime\":1484213230706,\"bizType\":\"INTERFACE\",\"merchantNo\":\"RiskControl\",\"typeValue\":\"黑名单\",\"channelEnum\":\"ChannelEnum_PC\"}");
			// map.put("blacklist", new ArrayList<Object>() );//也可以是使用list
			String valuestr = JSON.toJSONString(black);
			map.put("black", valuestr);
			String parameters = JSON.toJSONString(map);
			BizRequestParameters bizQueryParameters = new BizRequestParameters();
			bizQueryParameters.setParametersContent(parameters);
			bizQueryParameters.setParametersType("json");

			InterfaceRequestParameters interfaceParameters = new InterfaceRequestParameters();
			interfaceParameters.setChannelCode("PC");
			interfaceParameters.setInterfaceId("riskBlackService");
			interfaceParameters.setMerchantNo(MerchantNo.RISK.getNo());
			interfaceParameters.setOutSid(OutSide.RISK);
			interfaceParameters.setProductNo("graph_riskmanager");
			interfaceParameters.setSystemCode(SystemCode.RISK.getCode());
			
			DubboRequest request = new DubboRequest();
			request.setBizRequestParameters(bizQueryParameters);
			request.setInterfaceRequestParameters(interfaceParameters);
			DubboResponse response = dataProductSaveFacade.dataSave(request);
			logger.info(JSON.toJSONString(response));
		} catch (Exception e) {
			logger.error("blacklistSave error", e);
		}

	}
}

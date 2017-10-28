package com.lakala.contactlist.batch.query;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lakala.contactlist.batch.query.service.impl.BlacklistImportImpl;
import com.lakala.creditloan.manager.dto.BlackParams;
import com.lakala.creditloan.manager.model.Blacklist;
import com.lakala.creditloan.manager.provider.BlackManagerProviderService;


public class TestBlackListImport {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private ClassPathXmlApplicationContext context;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext(new String[] {"appcontext.xml","customer.xml" });
		context.start();
	}

	@After
	public void contextStop() {
		// 结果输出
		context.stop();
	}
	
	@Test
	public void batchImport() {
		BlacklistImportImpl blackListImport = (BlacklistImportImpl)context.getBean("blacklistImportImpl");
		blackListImport.batchImport();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void getBlackList() {
		BlackManagerProviderService blackManagerProviderService = (BlackManagerProviderService)context.getBean("blackManagerProviderService");
		BlackParams blackParams = new BlackParams();
		blackParams.setPage(1);// 页码
		blackParams.setRows(50);// 每页显示多少条记录
		Map<String,Object> map = (Map<String,Object>)blackManagerProviderService.getPageData(blackParams);
		List<Blacklist> blacklist = (List<Blacklist>) map.get("rows");
		if(blacklist !=null) {
			for(Blacklist black : blacklist) {
				String valuestr = JSON.toJSONString(black);
				logger.info("black:"+valuestr);
			}
		}
	}
}

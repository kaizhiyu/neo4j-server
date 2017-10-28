package com.lakala.contactlist.batch.query.service.impl;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
//import com.lakala.bigdatacenter.query.gateway.component.protocol.BaseQueryDto;
//import com.lakala.bigdatacenter.query.gateway.component.protocol.BizQueryParameters;
//import com.lakala.bigdatacenter.query.gateway.component.protocol.QueryContextImpl;
//import com.lakala.bigdatacenter.query.gateway.component.protocol.QueryResult;
//import com.lakala.bigdatacenter.query.gateway.component.protocol.QueryResultImpl;
//import com.lakala.bigdatacenter.query.gateway.component.protocol.QuerySession;
//import com.lakala.bigdatacenter.query.gateway.component.protocol.QuerySessionImpl;
//import com.lakala.bigdatacenter.query.gateway.dubbo.api.DataProductQueryFacade;
import com.lakala.contactlist.batch.query.service.ExcelBatchImportService;
import com.lakala.contactlist.batch.query.util.UUIDGenerator;


/**
 * 
 * <P>对功能点的描述</P>
 * @author tianhuaxing 2016年5月16日 下午2:41:04
 * @since 1.0.0.000
 */
@Service
public class CallHistoryBatchImpotImpl implements ExcelBatchImportService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CallHistoryBatchImpotImpl.class);

	@PostConstruct
	public void init() {
		batchImport();
	}

	@Override
	public void batchImport() {
		// TODO Auto-generated method stub
		
	}
}

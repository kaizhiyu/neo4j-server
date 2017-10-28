package com.lakala.cheatservice.dao;


import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lakala.cheatservice.constant.ConstantsConfig;
import com.lakala.cheatservice.constant.DataTypeEnum;
import com.lakala.cheatservice.constant.LakalaTable;
import com.lakala.cheatservice.models.HbaseCol;


public class HbaseUtilServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(getClass());



	
	public static void put(String rowkey,String addCellName,DataTypeEnum dataType, String content) {
		try {
			HTable table = new HTable(ConstantsConfig.conf,dataType.getCollectName());
			Date today = new Date();
			Put put = new Put(Bytes.toBytes(StringUtils.reverse(rowkey)));
			put.add(Bytes.toBytes(LakalaTable.TABLE_FAMILY),
					Bytes.toBytes(dataType.getColumnName()+addCellName),
					Bytes.toBytes(content));
			table.put(put);
			
			table.close();
		} catch (Exception e) {
			System.err.println("【插入hbase报错】conf:"+ConstantsConfig.conf.get("hbase.zookeeper.quorum").toString()+" Error:"+e.getMessage());
			System.err.println(e.getMessage());
		}

	}
	

	
	public static Set<String>  get(String rowkey, DataTypeEnum dataType) {
		Set<String> set=new HashSet<String>();
		HTable  table = null;
		try {
			table = new HTable(ConstantsConfig.conf, dataType.getCollectName());
			Get get = new Get(Bytes.toBytes(StringUtils.reverse(rowkey)));
			// 设置需要返回的列(默认全部列返回)
			// get.addColumn(Bytes.toBytes("0"), Bytes.toBytes("LKLDFGPT"));
			Result next = table.get(get);
			NavigableMap<byte[], NavigableMap<byte[], byte[]>> noVersionMap = next
					.getNoVersionMap();
			if(noVersionMap!=null&&noVersionMap.size()>0){
				for (Entry<byte[], NavigableMap<byte[], byte[]>> entry : noVersionMap
						.entrySet()) {
//					System.out.println("列族名：" + Bytes.toString(entry.getKey()));
					NavigableMap<byte[], byte[]> values = entry.getValue();
					for (Entry<byte[], byte[]> clumns : values.entrySet()) {
//						System.out.println(String.format("列名：%s,列值:%s",
//								Bytes.toString(clumns.getKey()),
//								Bytes.toString(clumns.getValue())));
						set.add(Bytes.toString(clumns.getValue()));
					}
				}
			}
		} catch (IOException e) {
			System.err.println("【查询hbase报错】");
			System.err.println(e.getMessage());
		}
		return set;
	}

	
	public static boolean put(String tableName, String family, String rowkey,
			Map<String, String> map) {
		boolean rowExist=false;
		try {

			rowExist= rowExist(tableName, rowkey);
			if (!rowExist) {
				System.out.println("put rowkey({"+rowkey+"}) to map");
				HbaseCol[] cols=new HbaseCol[map.size()];
				int k=0;
				for (Map.Entry<String, String> entry:map.entrySet()) {
					Object object=entry.getValue();
					if(object!=null)
					{
						cols[k]=(new HbaseCol(family, entry.getKey(), object.toString()));
					}
					else {
						cols[k]=(new HbaseCol(family, entry.getKey(), entry.getValue()));
					}
					k++;
				}
			addRow(tableName, rowkey, cols);
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return rowExist;
	}

	/**
	 * 保存多行数据
	 */
	
	public boolean put(String tableName, String family,
			Map<String, Map<String, String>> map) {
		
		List<String> rowKeys = new ArrayList<String>();
		for (Map.Entry<String, Map<String, String>> mapkey:map.entrySet()) {
			rowKeys.add(mapkey.getKey());
		}
		boolean[] rowExists = rowExists(tableName, rowKeys);
		Map<String, HbaseCol[]> puts = new HashMap<String, HbaseCol[]>();
		for (int i = 0; i < rowExists.length; i++) {
			boolean rowExist = rowExists[i];
			if (!rowExist) {
				String rowKey = rowKeys.get(i);
//				logger.info("add pk({}) to comm_cust_info", org.apache.commons.lang.StringUtils.reverse(rowKey));
//				puts.put(rowKey,
//						new HbaseCol[] { new HbaseCol("f", "cell_phone", org.apache.commons.lang.StringUtils.reverse(rowKey)),
//								new HbaseCol("cri", "orion_calls_state", "0"), new HbaseCol("cri", "update_time", DateTimeUtil.getCurrentDateTime()),
//								new HbaseCol("cri", "cust_type", "1") });
				logger.info("add pk({}) to comm_cust_info", rowKey);
				HbaseCol[] cols=new HbaseCol[map.get(rowKey).size()];
				int k=0;
				for (Map.Entry<String, String> entry:map.get(rowKey).entrySet()) {
					cols[k]=(new HbaseCol(family, entry.getKey(), entry.getValue()));
					k++;
				}
				
				puts.put(rowKey,cols);
						
			}
		}
		
		addRows(tableName, puts);
		return true;
			
	}
	
	public boolean[] rowExists(String tableName, List<String> rowKeys) {
		logger.info("rowExists: tableName:{},rowKeys:{}", tableName, rowKeys);
		HTable table = null;
		boolean[] existsAll=null;
		try {
//			table = HbaseUtils.getHTable(tableName, HbaseHelper.getConf());
			table = new HTable(ConstantsConfig.conf, tableName);
			List<Get> gets=new ArrayList<Get>();
			for(String rowKey:rowKeys){
				gets.add(new Get(Bytes.toBytes(rowKey)));
			}
			existsAll = table.existsAll(gets);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("rowExists exception: tableName:{},rowKeys:{}", tableName, rowKeys);
			throw new RuntimeException(e);
		} finally {
//			HbaseUtils.releaseTable(tableName, table);
			closeTable(table);
		}
		return existsAll;
	}
	
	private static boolean rowExist(String tableName, String rowKey) {
		System.out.println("rowExists: tableName:"+tableName+",rowKey:"+ rowKey);
		HTable table = null;
		boolean exists=false;
		try {
//			table = HbaseUtils.getHTable(tableName, HbaseHelper.getConf());
			table = new HTable(ConstantsConfig.conf, tableName);
			final Get get=new Get(Bytes.toBytes(rowKey));
			exists = table.exists(get);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("rowExists exception: tableName:"+tableName+",rowKeys:"+rowKey);
			throw new RuntimeException(e);
		} finally {
//			HbaseUtils.releaseTable(tableName, table);
			closeTable(table);
		}
		return exists;
	}
	
    private static void closeTable(HTable table){
		System.out.println("closeTable: tableName:"+ table);
        if (table != null) {
            try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("closeTable exception: tableName:"+table);
				throw new RuntimeException(e);
			}
        }
    }
    
	private void addRows(String tableName, Map<String, HbaseCol[]> rows) {
		logger.info("addRows: tableName:{},cols:{}", tableName, rows.size());
		HTable table = null;
		try {
			//table = HbaseUtils.getHTable(tableName, HbaseHelper.getConf());
			table = new HTable(ConstantsConfig.conf, tableName);
			for (String key : rows.keySet()) {
				Put put = new Put(Bytes.toBytes(key));
				HbaseCol[] cols = rows.get(key);
				for (HbaseCol c : cols) {
					if(StringUtils.isNotBlank(c.getValue())){
						put.addColumn(Bytes.toBytes(c.getFamily()), Bytes.toBytes(c.getQualifer()), Bytes.toBytes(c.getValue()));
					}
				}
				table.put(put);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addRows exception: tableName:{},cols:{},exception:{}", tableName, rows.toString(), e.toString());
			throw new RuntimeException(e);
		} finally {
//			HbaseUtils.releaseTable(tableName, table);
			closeTable(table);
		}
	}
	
	
	private static void addRow(String tableName, String rowKey, HbaseCol[] cols) {
		System.out.println("addRow: tableName:"+tableName+",rowKey:"+rowKey+",cols:"+cols);
		HTable table = null;
		try {
			//table = HbaseUtils.getHTable(tableName, HbaseHelper.getConf());
			table =new HTable(ConstantsConfig.conf, tableName);
			Put put = new Put(Bytes.toBytes(rowKey));
			for (HbaseCol c : cols) {
				put.addColumn(Bytes.toBytes(c.getFamily()), Bytes.toBytes(c.getQualifer()), Bytes.toBytes(c.getValue()));
			}
			table.put(put);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("addRow exception: tableName:"+tableName+",rowKey:"+rowKey+",cols:"+cols+",exception:"+e.toString() );
			throw new RuntimeException(e);
		} finally {
//			HbaseUtils.releaseTable(tableName, table);
			closeTable(table);
		}
	}
		
}
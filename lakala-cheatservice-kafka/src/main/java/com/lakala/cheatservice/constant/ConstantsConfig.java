package com.lakala.cheatservice.constant;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;


public class ConstantsConfig {
    public static final String CONTACT_COLLECTIONNAME = "contactlist";
    public static final String LBS_COLLECTIONNAME = "lbs";
    public static final String CALLHISTORY_COLLECTIONNAME = "callhistory";
    public static final String SMS_COLLECTIONNAME = "sms";
    
    public static final String OS_TYPE_ANDROID ="android";
    public static final String OS_TYPE_IOS ="ios";
    
    /*电话呼叫类型  1 ：来电 , 2:去电 ,3:未接*/
    public static final int INCOMING_TYPE=1;
    public static final int OUTGOING_TYPE=2;
    public static final int MISSED_TYPE=3;
    
    /*定义是否的标识*/
    public static final String YES="Y";
    public static final String NO="N";
    
    public static Configuration conf;
    
    public static String neo4jurl;

    public static String neo4jusername;

    public static String neo4jpassword;
	
	//("${kafka.zookeeper.connect}")
    public static String kafkaZookeeperConnect;
	
    public static String kafkaApplyInfoTopic;
    
	//("${kafka.topic}")
    public static String kafkaCallContactTopic;
	
	//("${kafka.group}")
    public static String kafkaGroup;
	
	//("${kafka.autoOffset}")
    public static String autoOffset;
	
	//("${zookeeper.session.timeout.ms}")
    public static String sessionTimeOut;
	
	//("${zookeeper.sync.time.ms}")
    public static String sysncTimeOut;
	
	//("${auto.commit.interval.ms}")
    public static String commitIntervalTime;
    
    static{
    	
    	Properties prop = new Properties(); 
    	//读取属性文件a.properties
    	InputStream in = null;
    	Map<String, String> propsMap=new HashMap<String, String>();
    	try {
    		in = new BufferedInputStream (new FileInputStream("../conf/application.properties"));
    		 LogBackConfigLoader.load("../conf/logback.xml");  
//    		in = ConstantsConfig.class.getResourceAsStream("application.properties");
//    		in = ConstantsConfig.class.getClassLoader().getResourceAsStream("../conf/application.properties");
        	prop.load(in);     ///加载属性列表
        	Iterator<String> it=prop.stringPropertyNames().iterator();
        	while(it.hasNext()){
        	    String key=it.next();
        	    propsMap.put(key, prop.getProperty(key));
        	    System.out.println(key+":"+prop.getProperty(key));
        	}
        	
        	conf = HBaseConfiguration.create();
        	
    		conf.set("hbase.zookeeper.quorum", propsMap.get("hbase.zookeeper"));
    		
    		kafkaZookeeperConnect=propsMap.get("kafka.zookeeper.connect");
    		
    	    kafkaCallContactTopic=propsMap.get("kafka.callcontact.topic");
    	    
    	    kafkaApplyInfoTopic=propsMap.get("kafka.applyinfo.topic");
    	    
    		kafkaGroup=propsMap.get("kafka.group");
    		
    		autoOffset=propsMap.get("kafka.autoOffset");
    		
    		sessionTimeOut=propsMap.get("zookeeper.session.timeout.ms");
    		
    		sysncTimeOut=propsMap.get("zookeeper.sync.time.ms");
    		
    		commitIntervalTime=propsMap.get("auto.commit.interval.ms");
    		
    		neo4jurl=propsMap.get("neo4j.url");

    		neo4jusername=propsMap.get("neo4j.username");

    		neo4jpassword = propsMap.get("neo4j.password");
        	
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}


		
    }


}

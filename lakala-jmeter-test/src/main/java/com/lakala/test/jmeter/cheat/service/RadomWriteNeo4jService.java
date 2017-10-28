package com.lakala.test.jmeter.cheat.service;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lakala.test.jmeter.cheat.logic.ApplyInfoRelationServiceImpl;
import com.lakala.test.jmeter.cheat.logic.MobileRelationServiceImpl;
import com.lakala.test.jmeter.cheat.repository.Neo4jUtilServiceImpl;



public class RadomWriteNeo4jService extends AbstractJavaSamplerClient {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RadomWriteNeo4jService.class);
	
    private String ip;
    private String isInsert="1";
    /** Holds the result data (shown as Response Data in the Tree display). */
    private String resultData;

    // 这个方法是用来自定义java方法入参的。
    // params.addArgument("num1","");表示入参名字叫num1，默认值为空。
    //设置可用参数及的默认值；
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("ip", "");
        params.addArgument("isInsert", "");
        
        return params;
    }

    //每个线程测试前执行一次，做一些初始化工作；
    public void setupTest(JavaSamplerContext arg0) {
    }
 
    //开始测试，从arg0参数可以获得参数值；
    public SampleResult runTest(JavaSamplerContext arg0) {
    	
    	LOGGER.info("start.......importdata!");
        ip = arg0.getParameter("ip");
        
        isInsert=arg0.getParameter("isInsert");
        SampleResult sr = new SampleResult();
        sr.setSampleLabel( "Java请求");
        try {
            sr.sampleStart();// jmeter 开始统计响应时间标记
            Hello test = new Hello();
            // 通过下面的操作就可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里面了。
//            resultData = String.valueOf(test.sum(Integer.parseInt(a), Integer
//                    .parseInt(b)));
//            if (resultData != null && resultData.length() > 0) {
//                sr.setResponseData("结果是："+resultData, null);
//                sr.setDataType(SampleResult.TEXT);
//            }
	        if (ip==null||ip.equals("")) {
				ip="127.0.0.1";
				
	        }
//			SqlHelper.url="bolt://"+ip+":7687";
            Neo4jUtilServiceImpl.url="bolt://"+ip+":7687";
	        MobileRelationServiceImpl savemobile=new MobileRelationServiceImpl(isInsert);
	        savemobile.saveMobileToNeo4j();
	        ApplyInfoRelationServiceImpl saveapply=new ApplyInfoRelationServiceImpl(isInsert);
	        saveapply.saveNeo4j();
	        sr.setSuccessful(true);
        } catch (Throwable e) {
            sr.setSuccessful(false);
            e.printStackTrace();
        } finally {
            sr.sampleEnd();// jmeter 结束统计响应时间标记
        }
        return sr;
    }

    //测试结束时调用；
    public void teardownTest(JavaSamplerContext arg0) {
        // System.out.println(end);
        // System.out.println("The cost is"+(end-start)/1000);
    }
    
    // main只是为了调试用，最后打jar包的时候注释掉。
    
/*      public static void main(String[] args) 
      { 
          Arguments params = new Arguments(); 
          params.addArgument("num1", "1");//设置参数，并赋予默认值1 
          params.addArgument("num2", "2");//设置参数，并赋予默认值2
          JavaSamplerContext arg0 = new JavaSamplerContext(params); 
          RadomWriteNeo4jService test = new RadomWriteNeo4jService(); 
          test.setupTest(arg0); 
          test.runTest(arg0);
          test.teardownTest(arg0); 

          System.out.println("++++++++++++++++++++++++++++++==============================ao98ew90rqw9ueroasjdl;fkjlkas;ldjf;lasdf;ljkas;ljdfasa");
      }
     */
}

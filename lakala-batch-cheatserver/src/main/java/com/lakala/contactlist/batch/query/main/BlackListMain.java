package com.lakala.contactlist.batch.query.main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.lakala.contactlist.batch.query.service.impl.BlacklistImportImpl;

public class BlackListMain {

	private  final static Logger logger = LoggerFactory.getLogger(BlackListMain.class);
	
	private static ClassPathXmlApplicationContext context;
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext(new String[] {"appcontext.xml","customer.xml" });
		context.start();
		logger.info("容器启动成功");
		Thread kafkaStartThead = new Thread(new Runnable() {
			@Override
			public void run() {
				BlacklistImportImpl blackListImport = (BlacklistImportImpl)context.getBean("blacklistImportImpl");
				blackListImport.batchImport();
			}
		});
		kafkaStartThead.start();
	}

}

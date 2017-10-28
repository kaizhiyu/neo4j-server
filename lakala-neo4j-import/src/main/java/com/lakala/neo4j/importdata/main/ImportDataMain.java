package com.lakala.neo4j.importdata.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lakala.neo4j.importdata.dao.Neo4jHelperDao;
import com.lakala.neo4j.importdata.service.Neo4jBatchDataService;



public class ImportDataMain {

	public static void main(String[] args) {

//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//		String dateString =df.format(new Date());

		System.out.println("Start clean data format!");
		System.out.println("input args :"+args[0]);
		String[] argsarr=args[0].split(",");
		String ip=argsarr[2];
//		StringBuffer sql=new StringBuffer();

		Neo4jHelperDao.url="bolt://"+ip+":7687";
		
		traverseFolder(argsarr[0],argsarr[1]);//递归查找文件
//		traverseFolder(argsarr[1]);//递归查找文件
		//打包时候取消以下两个注释
		Neo4jBatchDataService.handFile(argsarr[0],listfile);
	}

//
// /** 
//  * 判断文件夹是否存在 不存在则创建
//  * @param file
//  */
//	private static void judeDirExists(File file) {
//	
//		try {
//			   if (!file.exists()) {
//				   file.mkdirs();
//			   }
//		} catch (Exception e) {
//			
//		}
//
//	
//	}
	
	private static List<String> listfile=new ArrayList<String>();
	/**
	 * 递归获取当前文件夹下所有文件
	 * @param path
	 */
	public static void traverseFolder(String type,String path) {
		
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(type,file2.getAbsolutePath());
                    } else {
                    	if(type.equals("call")||type.equals("contact")){
                    		if(file2.getAbsolutePath().contains("part")){
	                    		listfile.add(file2.getAbsolutePath());
	                    		System.out.println("文件:" + file2.getAbsolutePath());
                    		}
                    	}
                    	else {
                    		if(file2.getAbsolutePath().contains("events")){
                        		listfile.add(file2.getAbsolutePath());
                        		System.out.println("文件:" + file2.getAbsolutePath());
                    		}
						}
                        
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	
	/**
	 * 递归获取当前文件夹下所有文件
	 * @param path
	 */
	public static void traverseFolder(String path) {
		
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                		listfile.add(file2.getAbsolutePath());
                		System.out.println("文件:" + file2.getAbsolutePath());
                    	
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	
//	private static void removeOutputfile(File file){
//		
//		if(!file.exists()){
//			System.out.println("文件不存在");
//		}
//		else{
//			System.out.println("存在文件");
//			System.out.println(file.delete());//删除文件
//		}
//	}
//	
	
}

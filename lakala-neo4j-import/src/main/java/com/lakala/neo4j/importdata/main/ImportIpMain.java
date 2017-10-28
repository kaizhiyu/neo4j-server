package com.lakala.neo4j.importdata.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lakala.neo4j.importdata.dao.Neo4jHelperDao;

public class ImportIpMain {
	private static List<String> listfile=new ArrayList<String>();
	
	public static void main(String[] args) {
		
		System.out.println("Start handle data format!");
		System.out.println("input args :"+args[0]);
		//取得输入参数 
		String[] argsarr=args[0].split(",");
		//argsarr中第一个参数是文件路劲 第二个是ip地址
		Neo4jHelperDao.url="bolt://"+argsarr[1]+":7687";
		//通过文件路劲读取数据
		updateIp(argsarr[0]);		
	}
	
	/**
	 * 将进件用户数据导入到neo4j
	 * @param paths
	 * @param outputfile
	 */
	private static void updateIp(String filepath) {
		try {
			traverseFolder(filepath);
			long t1=System.currentTimeMillis();
			listfile.parallelStream().forEach(p->{
				System.out.println("read path:"+p);
				BufferedReader br=null;
				try {
					br = new BufferedReader(new FileReader(p));//构造一个BufferedReader类来读取文件
					String str = null;
					long idex=0;
					while((str = br.readLine())!=null){
						try {
							idex++;
							System.out.println(Thread.currentThread().getName()+":Start import data!");
							saveIps(str,idex);
							System.out.println(Thread.currentThread().getName()+":End import data!");							
						} catch (Exception e) {
							System.err.println(Thread.currentThread()+":ImportIpMain updateIp br:"+str+" [err:"+e.getMessage()+"]");
						}
					}
					System.out.println(Thread.currentThread()+":import success!");
					System.out.println(Thread.currentThread()+":ImportIpMain updateIp cost milliseconds time:"+(System.currentTimeMillis()- t1)+"ms");
					
				} catch (Exception e) {
					System.err.println(Thread.currentThread()+":handleApply error:"+e.getMessage());
				}
				finally{
					if(br!=null){
						try {
							br.close();
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
						
					}
				}
//				
			});
			
		} catch (Exception e) {
			System.out.println(Thread.currentThread()+":ImportIpMain updateIp Executing error:"+e.getMessage());
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
	
	/**
	 * 获取用户表的字段对应值
	 * @param line
	 * @param idex
	 * @return
	 */
	public static void saveIps(String line,long idex) {
		
		
		String[] arr=line.split(" ");
		Map<String, String> map=new HashMap<String, String>();
		if(arr[0]!=null&&!arr[0].equals("")&&!arr[0].equals("null")){
			map.put("ipv4",arr[0]);
			System.out.println("index:"+idex+",ip:"+arr[0]);
			Neo4jHelperDao.executeInsert("MERGE (p:IPV4 {content:'"+arr[0]+"'}) set p.modelname='IPV4',p.content='"+arr[0]+"',p.starttime='"+arr[2]+"',p.endtime='"+arr[3]+"',p.logintimes='"+arr[1]+"',p.group='14',p.operation='artificial'");
		}
		else {
			return;
		}
	}
	


	

}

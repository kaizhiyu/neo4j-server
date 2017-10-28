package com.lakala.neo4j.importdata.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.lakala.neo4j.importdata.constant.MajorCompany;

public class SplitTextFileMain {

	/**
	 * 传入文件路径和按多少行切分 例如：/home/graphdb/data/s_c_apply_user/,300000
	 * @param args
	 */
	public static void main(String[] args) {
		String[] argarr=args[0].split(",");
		int rowcount=Integer.parseInt(argarr[1]);
		traverseFolder(argarr[0]);
		Splittxt(argarr[0]+"/output",rowcount);
	}
	private static List<String> listfile=new ArrayList<String>();
	
	/**
	 * 读取进件的数据并进行格式转换
	 * @param paths
	 * @param outputfile
	 */
	private static void Splittxt(String outputfile,int rowcount) {
		try {
			judeDirExists(new File(outputfile));
			for(String path:listfile){
				System.out.println("read path:"+path);
				BufferedReader br=null;
				try {
					br = new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件
					String str = null;
//					String lineStr="";
					int count=0;
					int filecount=0;
					String savefile=outputfile+"/part-000"+filecount+"_"+Thread.currentThread().getName();
					
					while((str = br.readLine())!=null){
						if(!str.equals("")){
							count++;
							writeDataToTxt(str,savefile);
						}
						
						if(count==rowcount){
							count=0;
							filecount++;
							savefile=outputfile+"/part-000"+filecount+"_"+Thread.currentThread().getName();
						}
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				finally{
					if(br!=null){
						try {
							br.close();
						} catch (Exception e2) {
							System.err.println("Splittxt error:"+e2.getMessage());
						}
					}
				}
				
				
				
			}
		} catch (Exception e) {
			System.err.println("handleApply error:"+e.getMessage());
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
	  * 判断文件夹是否存在 不存在则创建
	  * @param file
	  */
		private static void judeDirExists(File file) {
		
			try {
				   if (!file.exists()) {
					   file.mkdirs();
				   }
			} catch (Exception e) {
				
			}

		
		}
	
	/**
	 * 读取的数据写入文件
	 * @param content
	 * @param path
	 */
	private static void writeDataToTxt(String content,String path) {
		FileWriter fw = null;
		try {
			//如果文件存在，则追加内容；如果文件不存在，则创建文件
			
			File f=new File(path);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		pw.flush();
		try {
				fw.flush();
				pw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	

}

package com.lakala.neo4j.importdata.constant;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class DoTagOrders {

	public static List<String> CertNoList;
	public static List<String> MobileList;
	public static List<String> EmailList;
	public static List<String> BlackOrderNoList;
	
	static{
		CertNoList=new ArrayList<String>();
		MobileList=new ArrayList<String>();
		EmailList=new ArrayList<String>();
		BlackOrderNoList=new ArrayList<String>();
    	try {
    		BufferedReader br = new BufferedReader(new FileReader("./blackID.txt"));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            	System.out.println(s);
            	CertNoList.add(s);
            }
            br.close(); 
            BufferedReader brmobile = new BufferedReader(new FileReader("./blackMobile.txt"));//构造一个BufferedReader类来读取文件
            String smobile = null;
            while((smobile = brmobile.readLine())!=null){//使用readLine方法，一次读一行
            	System.out.println(smobile);
            	MobileList.add(smobile);
            }
            brmobile.close(); 
            BufferedReader bremail = new BufferedReader(new FileReader("./blackEmail.txt"));//构造一个BufferedReader类来读取文件
            String semail = null;
            while((semail = bremail.readLine())!=null){//使用readLine方法，一次读一行
            	System.out.println(semail);
            	EmailList.add(semail);
            }
            bremail.close(); 
            
            BufferedReader brorder = new BufferedReader(new FileReader("./blackOrder.txt"));//构造一个BufferedReader类来读取文件
            String sorder = null;
            while((sorder = brorder.readLine())!=null){//使用readLine方法，一次读一行
            	System.out.println(sorder);
            	BlackOrderNoList.add(sorder);
            }
            brorder.close(); 
        	
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{

		}

	}
//	public static void main(String[] args) {
//		try {
//    		BufferedReader br = new BufferedReader(new FileReader("./TagOrders.txt"));//构造一个BufferedReader类来读取文件
//            String s = null;
//            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//            	System.out.println(s);
//            }
//            System.out.println(list.size());
//            br.close(); 
//        	
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}finally{
//
//		}
//	}
}

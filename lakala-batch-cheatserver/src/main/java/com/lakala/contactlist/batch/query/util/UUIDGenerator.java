/*******************************************************************************
 * Project Key : MERCHANTSERVICE
 * Create on 2013-11-12 上午11:42:02
 * Copyright (c) 2004 - 2013. 拉卡拉支付有限公司版权所有. 京ICP备12007612号
 * 注意：本内容仅限于拉卡拉支付有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.lakala.contactlist.batch.query.util;

import java.util.UUID;


/**
 * UUID生成器
 * 
 * @author 胡义平 2013-11-12 上午11:42:02
 */
public class UUIDGenerator {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString().toUpperCase();
		return str.replace("-", "");
	}

	// 获得指定数量的UUID
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}

	public static void main(String[] args) {
		String[] ss = getUUID(10);
		for (int i = 0; i < ss.length; i++) {
			System.out.println("ss[" + i + "]=====" + ss[i]);
		}
		System.out.println(getUUID());
	}

}

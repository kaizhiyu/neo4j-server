package com.lakala.cheatservice.models;

import java.io.Serializable;

public class BaseModel  implements Serializable {
	//��Ӧ����ɫ�������ݿ��о��Ǻ��������ǷǺ�����0��1��ʾ
	private String group;
	
	private String id;

	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}

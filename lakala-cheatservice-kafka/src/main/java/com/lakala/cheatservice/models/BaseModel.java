package com.lakala.cheatservice.models;

import java.io.Serializable;

public class BaseModel  implements Serializable {
	//对应的颜色，在数据库中就是黑明单还是非黑名单0，1表示
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

package com.lakala.cheatservice.models;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * 显示名称
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机号码集合
     */
    private List<String> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
}

package com.lakala.cheatservice.constant;

/**  
 * <P>操作类型枚举</P>
 * @author niezhili 2016年11月23日 上午9:41:42
 * @since 1.0.0.000 
 */
public enum OperateTypeEnum {
	
	OperateTypeEnum_ADD("ADD", "新增"),
	OperateTypeEnum_MODIFY("MODIFY", "修改"),
	OperateTypeEnum_DELETE("DELETE", "删除"),
	OperateTypeEnum_QUERY("QUERY", "查询"),
	OperateTypeEnum_OTHER("OTHER", "其他操作");
	/**
	 * 操作id
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 构造方法
	 * 
	 * @param id
	 *            操作id
	 * @param name
	 *            名称
	 */
	private OperateTypeEnum(String id, String name) {
		this.name = name;
		this.id = id;
	}

	/**
	 * 获取名称
	 * 
	 * @param id
	 *            编号
	 * @return 名称
	 */
	public static String getName(String id) {
		for (OperateTypeEnum c : OperateTypeEnum.values()) {
			if (c.getId().equals(id)) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.id + "_" + this.name;
	}

	public boolean equals(String id) {
		return this.getId().equals(id);
	}
}


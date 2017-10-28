package com.lakala.cheatservice.constant;


/**  
 * <P>业务类型枚举</P>
 * @author tianhuaxing 2016年10月1日 下午4:03:01
 * @since 1.0.0.000 
 */
public enum BizTypeEnum {
	//业务类型
	BizTypeEnum_FLOW("FLOW", "流程日志类型"),
	BizTypeEnum_INTERFACE("INTERFACE", "接口日志类型"),
	BizTypeEnum_OTHER("OTHER", "其他类型");
	/**
	 * 渠道id
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
	 *            渠道id
	 * @param name
	 *            名称
	 */
	private BizTypeEnum(String id, String name) {
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
		for (BizTypeEnum c : BizTypeEnum.values()) {
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

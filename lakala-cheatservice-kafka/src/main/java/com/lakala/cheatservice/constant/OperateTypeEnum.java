package com.lakala.cheatservice.constant;

/**  
 * <P>��������ö��</P>
 * @author niezhili 2016��11��23�� ����9:41:42
 * @since 1.0.0.000 
 */
public enum OperateTypeEnum {
	
	OperateTypeEnum_ADD("ADD", "����"),
	OperateTypeEnum_MODIFY("MODIFY", "�޸�"),
	OperateTypeEnum_DELETE("DELETE", "ɾ��"),
	OperateTypeEnum_QUERY("QUERY", "��ѯ"),
	OperateTypeEnum_OTHER("OTHER", "��������");
	/**
	 * ����id
	 */
	private String id;

	/**
	 * ����
	 */
	private String name;

	/**
	 * ���췽��
	 * 
	 * @param id
	 *            ����id
	 * @param name
	 *            ����
	 */
	private OperateTypeEnum(String id, String name) {
		this.name = name;
		this.id = id;
	}

	/**
	 * ��ȡ����
	 * 
	 * @param id
	 *            ���
	 * @return ����
	 */
	public static String getName(String id) {
		for (OperateTypeEnum c : OperateTypeEnum.values()) {
			if (c.getId().equals(id)) {
				return c.name;
			}
		}
		return null;
	}

	// get set ����
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


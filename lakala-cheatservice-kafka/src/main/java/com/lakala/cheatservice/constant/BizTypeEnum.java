package com.lakala.cheatservice.constant;


/**  
 * <P>ҵ������ö��</P>
 * @author tianhuaxing 2016��10��1�� ����4:03:01
 * @since 1.0.0.000 
 */
public enum BizTypeEnum {
	//ҵ������
	BizTypeEnum_FLOW("FLOW", "������־����"),
	BizTypeEnum_INTERFACE("INTERFACE", "�ӿ���־����"),
	BizTypeEnum_OTHER("OTHER", "��������");
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
	private BizTypeEnum(String id, String name) {
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
		for (BizTypeEnum c : BizTypeEnum.values()) {
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

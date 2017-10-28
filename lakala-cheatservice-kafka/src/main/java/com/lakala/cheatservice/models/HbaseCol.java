package com.lakala.cheatservice.models;

public class HbaseCol{
	private String family;
	private String qualifer;
	private String value;

	public HbaseCol(String family, String qualifer) {
		super();
		this.family = family;
		this.qualifer = qualifer;
	}
	public HbaseCol(String family, String qualifer, String value) {
		super();
		this.family = family;
		this.qualifer = qualifer;
		this.value = value;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getQualifer() {
		return qualifer;
	}
	public void setQualifer(String qualifer) {
		this.qualifer = qualifer;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((family == null) ? 0 : family.hashCode());
		result = prime * result + ((qualifer == null) ? 0 : qualifer.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HbaseCol other = (HbaseCol) obj;
		if (family == null) {
			if (other.family != null)
				return false;
		} else if (!family.equals(other.family))
			return false;
		if (qualifer == null) {
			if (other.qualifer != null)
				return false;
		} else if (!qualifer.equals(other.qualifer))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HbaseCol [family=" + family + ", qualifer=" + qualifer + ", value=" + value + "]";
	}
	
}
package com.lakala.neo4j.importdata.models;



import java.io.Serializable;


public class IMEIDeviceV  extends BaseModel implements Serializable{

	public IMEIDeviceV(){
		super();
	}
	
	public IMEIDeviceV(String id,String deviceid,String group,String type){
		super.setId(id);
		this.deviceid=deviceid;
		super.setGroup(group);
		this.type=type;
	}
	//设备号
	private String deviceid;
	
	//如果黑明单存在还需要设置type
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	

}

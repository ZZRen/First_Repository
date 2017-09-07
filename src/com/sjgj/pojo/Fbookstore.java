package com.sjgj.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Fbookstore implements Serializable{
private int stid;  			//商城主键
private String storename;	//商城名字
private String storeUrl;	//商城链接
public int getStid() {
	return stid;
}
public void setStid(int stid) {
	this.stid = stid;
}
public String getStorename() {
	return storename;
}
public void setStorename(String storename) {
	this.storename = storename;
}
public String getStoreUrl() {
	return storeUrl;
}
public void setStoreUrl(String storeUrl) {
	this.storeUrl = storeUrl;
}


}

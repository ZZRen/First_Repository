package com.sjgj.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Fuserbuy implements Serializable{
private int buid;  		 //购买记录主键
private int bookid;  	 //图书的主键
private int fuid;  		 //购书者的主键
private String buystore; //导入时间类型
private String buytime;	 //导入时间
public int getBuid() {
	return buid;
}
public void setBuid(int buid) {
	this.buid = buid;
}
public int getBookid() {
	return bookid;
}
public void setBookid(int bookid) {
	this.bookid = bookid;
}

public int getFuid() {
	return fuid;
}
public void setFuid(int fuid) {
	this.fuid = fuid;
}
public String getBuystore() {
	return buystore;
}
public void setBuystore(String buystore) {
	this.buystore = buystore;
}
public String getBuytime() {
	return buytime;
}
public void setBuytime(String buytime) {
	this.buytime = buytime;
}




}

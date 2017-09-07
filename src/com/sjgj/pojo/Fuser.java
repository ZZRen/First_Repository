package com.sjgj.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Fuser implements Serializable{
private int fuid;  			//买家主键
private String tname;	//买家昵称
private String phone;	//手机
private String email;	//邮箱
private String area;	//地区
private String examyear;	//考研年份
private String englishtype;	//考生英语类型
public int getFuid() {
	return fuid;
}
public void setFuid(int fuid) {
	this.fuid = fuid;
}
public String getTname() {
	return tname;
}
public void setTname(String tname) {
	this.tname = tname;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getExamyear() {
	return examyear;
}
public void setExamyear(String examyear) {
	this.examyear = examyear;
}
public String getEnglishtype() {
	return englishtype;
}
public void setEnglishtype(String englishtype) {
	this.englishtype = englishtype;
}


}

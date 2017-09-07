package com.sjgj.pojo;

import java.io.Serializable;
import java.util.Date;

//用户实体类
@SuppressWarnings("serial")
public class User implements Serializable{
private int uid;                //用户id
private String account;         //用户注册的手机号
private String password;        //账户密码
private Date registerTime;      //注册时间
private String faceImgUrl; 		//头像图片地址
private String nickName; 		//昵称
private String examyear; 		//考研年份
private String examidentity; 	//考研身份
private String phone ;			//电话号码
private String province ;		//省份
private String email ;			//邮箱
private String incollege ;		//本学校
private String wantcollege ;	//目标院校
private int signcount;			//签到次数
private String returnmsg;		//用户反馈
private int remaincpscount;		//用户剩余批改次数
private int point;				//用户积分
private int level;				//用户等级
private String iflogin;			//用户是否已登录
private String type;			//用户是安卓还是ios


public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getIflogin() {
	return iflogin;
}
public void setIflogin(String iflogin) {
	this.iflogin = iflogin;
}
public int getPoint() {
	return point;
}
public void setPoint(int point) {
	this.point = point;
}
public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public int getRemaincpscount() {
	return remaincpscount;
}
public void setRemaincpscount(int remaincpscount) {
	this.remaincpscount = remaincpscount;
}
public String getReturnmsg() {
	return returnmsg;
}
public void setReturnmsg(String returnmsg) {
	this.returnmsg = returnmsg;
}
public int getSigncount() {
	return signcount;
}
public void setSigncount(int signcount) {
	this.signcount = signcount;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Date getRegisterTime() {
	return registerTime;
}
public void setRegisterTime(Date registerTime) {
	this.registerTime = registerTime;
}
public String getFaceImgUrl() {
	return faceImgUrl;
}
public void setFaceImgUrl(String faceImgUrl) {
	this.faceImgUrl = faceImgUrl;
}
public String getNickName() {
	return nickName;
}
public void setNickName(String nickName) {
	this.nickName = nickName;
}
public String getExamyear() {
	return examyear;
}
public void setExamyear(String examyear) {
	this.examyear = examyear;
}
public String getExamidentity() {
	return examidentity;
}
public void setExamidentity(String examidentity) {
	this.examidentity = examidentity;
}

public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}

public String getIncollege() {
	return incollege;
}
public void setIncollege(String incollege) {
	this.incollege = incollege;
}
public String getWantcollege() {
	return wantcollege;
}
public void setWantcollege(String wantcollege) {
	this.wantcollege = wantcollege;
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

}


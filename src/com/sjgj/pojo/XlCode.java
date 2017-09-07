package com.sjgj.pojo;

public class XlCode {
private int xid;  			//序列号的主键
private String typename;	//书的类型
private String bname;		//书名字
private String xlnumber;	//序列号
private String xlcard;		//卡号
private String password;	//密码
private int uid;			//用户的id
private String account;     //用户注册的手机号
private String xlstatus;	//序列号启用状态
private String registertime;	//登记书的时间

public int getXid() {
	return xid;
}
public String getRegistertime() {
	return registertime;
}
public void setRegistertime(String registertime) {
	this.registertime = registertime;
}
public void setXid(int xid) {
	this.xid = xid;
}
public String getTypename() {
	return typename;
}
public void setTypename(String typename) {
	this.typename = typename;
}
public String getBname() {
	return bname;
}
public void setBname(String bname) {
	this.bname = bname;
}
public String getXlnumber() {
	return xlnumber;
}
public void setXlnumber(String xlnumber) {
	this.xlnumber = xlnumber;
}
public String getXlcard() {
	return xlcard;
}
public void setXlcard(String xlcard) {
	this.xlcard = xlcard;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
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
public String getXlstatus() {
	return xlstatus;
}
public void setXlstatus(String xlstatus) {
	this.xlstatus = xlstatus;
}

}

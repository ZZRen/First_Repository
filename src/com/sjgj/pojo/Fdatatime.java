package com.sjgj.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Fdatatime implements Serializable{
private int id;  			//导入时间主键
private String datatype;	//导入时间类型
private String datatime;	//导入时间
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDatatype() {
	return datatype;
}
public void setDatatype(String datatype) {
	this.datatype = datatype;
}
public String getDatatime() {
	return datatime;
}
public void setDatatime(String datatime) {
	this.datatime = datatime;
}


}

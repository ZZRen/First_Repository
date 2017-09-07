package com.sjgj.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Fbook implements Serializable{
private int bookid;  			//推送书的主键
private String booktype;	//类型
private String bookname;		//书名字
private String bookyear;		//书年份
public int getBookid() {
	return bookid;
}
public void setBookid(int bookid) {
	this.bookid = bookid;
}
public String getBooktype() {
	return booktype;
}
public void setBooktype(String booktype) {
	this.booktype = booktype;
}
public String getBookname() {
	return bookname;
}
public void setBookname(String bookname) {
	this.bookname = bookname;
}
public String getBookyear() {
	return bookyear;
}
public void setBookyear(String bookyear) {
	this.bookyear = bookyear;
}

}

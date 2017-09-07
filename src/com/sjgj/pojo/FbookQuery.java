package com.sjgj.pojo;

import java.io.Serializable;
@SuppressWarnings("serial")
public class FbookQuery implements Serializable {
	private int bookid;  			//书的主键
	private String booktype;	//类型
	private String bookname;		//书名字
	private String bookyear;		//书年份
	
	private Integer pageNo = 1; // 页码
	private Integer pageSize =10; // 每页显示的条数
	private Integer startRow; // 起始行  startRow = (pageNo-1) * pageSize
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.startRow = (pageNo-1) * pageSize;
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.startRow = (pageNo-1) * pageSize;
		this.pageSize = pageSize;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
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

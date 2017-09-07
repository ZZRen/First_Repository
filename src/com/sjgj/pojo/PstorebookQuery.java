package com.sjgj.pojo;

import java.io.Serializable;
@SuppressWarnings("serial")
public class PstorebookQuery implements Serializable {
	private int id;  			//主键
	private String pid;			//商品编号
	private String buystore;	//商城名字
	private int bookid; 		//图书名字
	private String bookname;	//图书名字

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBuystore() {
		return buystore;
	}
	public void setBuystore(String buystore) {
		this.buystore = buystore;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	
	
}

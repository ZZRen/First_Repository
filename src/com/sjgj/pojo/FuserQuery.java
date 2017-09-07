package com.sjgj.pojo;

import java.io.Serializable;
@SuppressWarnings("serial")
public class FuserQuery implements Serializable {
	private int fuid;  			//买家主键
	private String tname;	//导入时间类型
	private String phone;	//导入时间
	private String email;	//导入时间类型
	private String area;	//导入时间
	private String examyear;	//导入时间类型
	private String englishtype;	//导入时间
	private Integer bookid;	    //图书的id
	private String buystore;	//购书的商城

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
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getBuystore() {
		return buystore;
	}
	public void setBuystore(String buystore) {
		this.buystore = buystore;
	}

	
	
}

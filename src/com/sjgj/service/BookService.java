package com.sjgj.service;

import java.util.List;

import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.page.Pagination;

public interface BookService{

	/**
	 * 
	 * @Title: insertUser
	 * @Description: 注册用户
	 * @param User
	 * @return int 
	 * @throws
	 */
	public Integer insertUser(Fbook book);
	/**
	 * 
	 * @Title: validateUser
	 * @Description: 查询列表
	 * @param account
	 * @return User
	 * @throws
	 */
	public Pagination selectBrandListByObjectQuery(String bookname, String booktype, Integer pageNo);
	//根据id查询图书信息并回显
	public Fbook selectBookByid(int bookid);
	//根据id更新图书信息
	public void updateBookById(Fbook fbook);
	/**
	 * 
	 * @Title: deleteBatch
	 * @Description: 根据id批量删除
	 * @param ids
	 * @return void
	 * @throws
	 */
	
	//根据id删除单个图书
	public void deleteByid(int bookid);
	
	//查询所有图书信息
		public List<Fbook> selectfBook(int bookid);
	
	}

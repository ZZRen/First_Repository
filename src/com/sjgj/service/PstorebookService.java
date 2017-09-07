package com.sjgj.service;

import java.util.List;

import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.Pstorebook;
import com.sjgj.pojo.page.Pagination;

public interface PstorebookService{

	/**
	 * 
	 * @Title: insertUser
	 * @Description: 添加
	 * @param User
	 * @return int 
	 * @throws
	 */
	public Integer insertPstorebook(Pstorebook pstorebook);
	/**
	 * 
	 * @Title: validateUser
	 * @Description: 查询列表
	 * @param account
	 * @return User
	 * @throws
	 */
	public Pagination selectpstorebookListByObjectQuery(String pid, String buystore,Integer pageNo);
	//根据id查询图书信息并回显
	public Pstorebook selectPstorebookByid(int id);
	//根据id更新图书信息
	public void updatePstorebookById(Pstorebook pstorebook);
	/**
	 * 
	 * @Title: deleteBatch
	 * @Description: 根据id批量删除
	 * @param ids
	 * @return void
	 * @throws
	 */
	
	//根据id删除单个
	public void deletePstorebookByid(int id);
	//根据pid和商城名字查询所有信息
	public List<Pstorebook> selectPstorebookBypidandstore(String pid,String buystore);
	
	}



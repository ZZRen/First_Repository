package com.sjgj.mapper;

import java.util.List;

import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.FbookQuery;
import com.sjgj.pojo.Pstorebook;
import com.sjgj.pojo.PstorebookQuery;

public interface PstorebookMapper {
/**
	 * 
	 * @Title: insertBook
	 * @Description: 商品编号
	 * @param User
	 * @return
	 * @throws
	 */
	public Integer insertPstorebook(Pstorebook pstorebook);
	/**
	 * 
	 * @Title: validateUser
	 * @Description: 根据条件查询并展示列表
	 * @param account
	 * @return User
	 * @throws
	 */
public List<Pstorebook> selectPstorebookListByObjectQuery(PstorebookQuery pstorebookQuery);
//查询总数
public int selectPstorebookCountByObjectQuery(PstorebookQuery pstorebookQuery);
//根据id查询图书信息并回显
public Pstorebook selectPstorebookByid(int id);

//根据id删除单个图书
public void deletePstorebookByid(int id);


//根据id更新图书信息
public void updatePstorebookById(Pstorebook pstorebook);

//根据pid和商城名字查询所有信息
public List<Pstorebook> selectPstorebookBypidandstore(String pid,String buystore);

}

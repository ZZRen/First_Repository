package com.sjgj.mapper;

import java.util.List;

import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.FbookQuery;

public interface BookMapper {
/**
	 * 
	 * @Title: insertBook
	 * @Description: 添加图书
	 * @param User
	 * @return
	 * @throws
	 */
	public Integer insertBook(Fbook fbook);
	/**
	 * 
	 * @Title: validateUser
	 * @Description: 根据条件查询并展示图书列表
	 * @param account
	 * @return User
	 * @throws
	 */
public List<Fbook> selectBookListByObjectQuery(FbookQuery fbookQuery);
//查询总数
public int selectBookCountByObjectQuery(FbookQuery fbookQuery);
//根据id查询图书信息并回显
public Fbook selectBookByid(int bookid);

//根据id删除单个图书
public void deleteByid(int bookid);


//根据id更新图书信息
public void updateBookById(Fbook fbook);

//根据年份和名字查询图书信息并回显
public Fbook selectBookByyearandname(Fbook fbook);

public Fbook selectBookByname(String bookname);

//查询所有图书信息
		public List<Fbook> selectfBook();

}

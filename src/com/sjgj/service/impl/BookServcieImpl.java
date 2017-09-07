package com.sjgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjgj.mapper.BookMapper;
import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.FbookQuery;
import com.sjgj.pojo.page.Pagination;
import com.sjgj.service.BookService;
@Service
@Transactional
public class BookServcieImpl implements BookService {
@Resource
private BookMapper bookMapper;
	//添加图书
	public Integer insertUser(Fbook book) {
		// TODO Auto-generated method stub
		Integer num = bookMapper.insertBook(book);
		return num;
	}
	//查询列表
	public Pagination selectBrandListByObjectQuery(String bname, String typename, Integer pageNo) {
		FbookQuery bookQuery = new FbookQuery(); // 封装查询条件的query对象
		StringBuilder params = new StringBuilder(); // 封装分页工具栏参数
		if(bname != null){
			bookQuery.setBookname(bname);
			params.append("bookname=").append(bname);
		}
		if(typename != null){
			bookQuery.setBooktype(typename);
			params.append("&booktype=").append(typename);  // 
		}
		bookQuery.setPageSize(10); // 设置每页显示条数
		bookQuery.setPageNo(Pagination.cpn(pageNo)); // 设置页码
		List<Fbook> list = bookMapper.selectBookListByObjectQuery(bookQuery); // 结果集
		int totalCount = bookMapper.selectBookCountByObjectQuery(bookQuery); // 总条数
		Pagination pagination = new Pagination(bookQuery.getPageNo(), bookQuery.getPageSize(), totalCount, list);
		
		// 设置分页工具栏
		String url = "/sjgjsend/book/list.action";
		pagination.pageView(url, params.toString());
		return pagination;
	}
	
	//根据id查询单本图书信息
	public Fbook selectBookByid(int bid) {
		Fbook book = bookMapper.selectBookByid(bid);
		return book;
	}
	//根据id更新图书信息
	public void updateBookById(Fbook book) {
		bookMapper.updateBookById(book);
		
	}

	public void deleteByid(int bid) {
		bookMapper.deleteByid(bid);
		
	}
	//查询所有图书信息
			public List<Fbook> selectfBook(int bookid){
				
				return bookMapper.selectfBook();
			}

}

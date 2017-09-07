package com.sjgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjgj.mapper.BookMapper;
import com.sjgj.mapper.PstorebookMapper;
import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.FbookQuery;
import com.sjgj.pojo.Pstorebook;
import com.sjgj.pojo.PstorebookQuery;
import com.sjgj.pojo.page.Pagination;
import com.sjgj.service.PstorebookService;
@Service
@Transactional
public class PstorebookServcieImpl implements PstorebookService {
@Resource
private PstorebookMapper pstorebookMapper;
	
	//查询列表
	public Pagination selectpstorebookListByObjectQuery(String pid, String buystore, Integer pageNo){
		PstorebookQuery pstorebookQuery = new PstorebookQuery(); // 封装查询条件的query对象
		StringBuilder params = new StringBuilder(); // 封装分页工具栏参数
		if(pid != null){
			pstorebookQuery.setPid(pid);
			params.append("pid=").append(pid);
		}
		if(buystore != null){
			pstorebookQuery.setBuystore(buystore);
			params.append("&buystore=").append(buystore);  // 
		}
		pstorebookQuery.setPageSize(10); // 设置每页显示条数
		pstorebookQuery.setPageNo(Pagination.cpn(pageNo)); // 设置页码
		List<Pstorebook> list = pstorebookMapper.selectPstorebookListByObjectQuery(pstorebookQuery); // 结果集
		int totalCount = pstorebookMapper.selectPstorebookCountByObjectQuery(pstorebookQuery); // 总条数
		Pagination pagination = new Pagination(pstorebookQuery.getPageNo(), pstorebookQuery.getPageSize(), totalCount, list);
		
		// 设置分页工具栏
		String url = "/sjgjsend/pstorebook/list.action";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	@Override
	public Integer insertPstorebook(Pstorebook pstorebook) {
		// TODO Auto-generated method stub
		return pstorebookMapper.insertPstorebook(pstorebook);
	}

	@Override
	public Pstorebook selectPstorebookByid(int id) {
		// TODO Auto-generated method stub
		return pstorebookMapper.selectPstorebookByid(id);
	}

	@Override
	public void updatePstorebookById(Pstorebook pstorebook) {
		// TODO Auto-generated method stub
		pstorebookMapper.updatePstorebookById(pstorebook);
	}

	@Override
	public void deletePstorebookByid(int id) {
		// TODO Auto-generated method stub
		pstorebookMapper.deletePstorebookByid(id);
	}

	@Override
	public List<Pstorebook> selectPstorebookBypidandstore(String pid, String buystore) {
		// TODO Auto-generated method stub
		return pstorebookMapper.selectPstorebookBypidandstore(pid, buystore);
	}
	
	
}

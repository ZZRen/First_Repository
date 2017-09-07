package com.sjgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjgj.mapper.FuserMapper;
import com.sjgj.pojo.Fuser;
import com.sjgj.pojo.FuserQuery;
import com.sjgj.pojo.page.Pagination;
import com.sjgj.service.FuserService;
@Service
@Transactional
public class FuserServiceImpl implements FuserService {
	@Resource
	private FuserMapper fuserMapper;
	//添加买家信息
	@Override
	public int insertfuser(Fuser fuser) {
		// TODO Auto-generated method stub
		return fuserMapper.insertfuser(fuser);
	}
	//根据手机号查询用户是否存在
	@Override
	public List<Fuser> selectfuserByphone(String phone) {
		// TODO Auto-generated method stub
		return fuserMapper.selectfuserByphone(phone);
	}
	//更新买家 用户信息
	@Override
	public void updatfuserByfuid(Fuser fuser) {
		// TODO Auto-generated method stub
		fuserMapper.updatfuserByfuid(fuser);
	}
	//买家信息列表展示
	@Override
	public Pagination selectFuserListByObjectQuery(String phone, String englishtype, String examyear, String area, Integer bookid,String buystore
			,Integer pageNo) {
		
		FuserQuery fuserQuery = new FuserQuery(); // 封装查询条件的query对象
		StringBuilder params = new StringBuilder(); // 封装分页工具栏参数
		if(phone != null){
			fuserQuery.setPhone(phone);
			params.append("phone=").append(phone);
		}
		if(englishtype != null){
			fuserQuery.setEnglishtype(englishtype);
			params.append("&englishtype=").append(englishtype);  // 
		}
		if(examyear != null){
			fuserQuery.setExamyear(examyear);
			params.append("&examyear=").append(examyear);  // 
		}
		if(area != null){
			fuserQuery.setArea(area);
			params.append("&area=").append(area);  // 
		}
		if(bookid != null){
			fuserQuery.setBookid(bookid);
			params.append("&bookid=").append(bookid);  // 
		}
		if(buystore != null){
			fuserQuery.setBuystore(buystore);
			params.append("&buystore=").append(buystore);  // 
		}
		fuserQuery.setPageSize(10); // 设置每页显示条数
		fuserQuery.setPageNo(Pagination.cpn(pageNo)); // 设置页码
		List<Fuser> list = fuserMapper.selectfuserListByObjectQuery(fuserQuery) ;// 结果集
		int totalCount =fuserMapper.selectfuserCountByObjectQuery(fuserQuery);// 总条数
		Pagination pagination = new Pagination(fuserQuery.getPageNo(), fuserQuery.getPageSize(), totalCount, list);
		
		// 设置分页工具栏
		String url = "/sjgjsend/data/list.action";
		pagination.pageView(url, params.toString());
		return pagination;
		
	}
	@Override
	public void updatallfuserByfuid(Fuser fuser) {
		// TODO Auto-generated method stub
		fuserMapper.updatallfuserByfuid(fuser);
	}
	@Override
	public Fuser selectlastfuser() {
		// TODO Auto-generated method stub
		return fuserMapper.selectlastfuser();
	}
	@Override
	public void deleteByid(int fuid) {
		fuserMapper.deleteByid(fuid);
		
	}
	
	
}

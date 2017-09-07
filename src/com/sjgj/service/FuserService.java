package com.sjgj.service;

import java.util.List;

import com.sjgj.pojo.Fuser;
import com.sjgj.pojo.page.Pagination;

public interface FuserService{
	//添加买家信息
	public int insertfuser(Fuser fuser);
	//根据手机号查询用户是否存在
	public List<Fuser> selectfuserByphone(String phone);

	//更新买家 用户信息

	public void updatfuserByfuid(Fuser fuser);
	
	
	//买家信息列表展示
	public Pagination selectFuserListByObjectQuery(String phone, String englishtype, String examyear, String area, Integer bookid,String buystore,Integer pageNo);
	
	//更新买家 用户所有信息

	public void updatallfuserByfuid(Fuser fuser);
	
	//查询最后一次导入用户的信息
	public Fuser selectlastfuser();
	
	//删除买家信息
	public void deleteByid(int fuid);
	}

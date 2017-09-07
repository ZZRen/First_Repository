package com.sjgj.mapper;

import java.util.List;

import com.sjgj.pojo.Fuserbuy;

public interface FuserbuyMapper {
	
	//添加用户购书记录
	public void insertfuserbuy(Fuserbuy fuserbuy);
	//根据图书id和用户id用户购书信息
	public List<Fuserbuy> selectfuserbuyBybookidandfuid(Fuserbuy fuserbuy);

	//根据购买记录id更新购买记录
	public void updatefuserbuyBybuid(Fuserbuy fuserbuy);
	
	//根据买家fuid查询购买记录
	public List<Fuserbuy> selectfuserbuyByfuid(int fuid);
	
	//根据买家fuid删除购书记录
	public void deleteuserbuyByfuid(int fuid);
}

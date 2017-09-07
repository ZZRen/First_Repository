package com.sjgj.mapper;

import com.sjgj.pojo.Fdatatime;

public interface FdatatimeMapper {

	//更新数据导入时间
	
	public void updateapptimeById(Fdatatime fdatetime);
	
	//查询最后一次导入数据时间
	public Fdatatime selectdatatimeByid(int id);
}

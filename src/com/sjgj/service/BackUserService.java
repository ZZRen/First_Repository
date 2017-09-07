package com.sjgj.service;

import com.sjgj.pojo.BackUser;

public interface BackUserService{
	//根据用户名查询后台用户
	public BackUser selectBuyerByUserName(String backusername);
	}

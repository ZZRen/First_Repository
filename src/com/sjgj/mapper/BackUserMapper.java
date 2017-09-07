package com.sjgj.mapper;

import com.sjgj.pojo.BackUser;

public interface BackUserMapper {
	//根据用户名查询后台用户
public BackUser selectBackUserByname(String backusername);
}

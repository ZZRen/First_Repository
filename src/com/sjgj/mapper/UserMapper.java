package com.sjgj.mapper;

import java.util.List;

import com.sjgj.pojo.User;

public interface UserMapper {

	//查询用户信息
	public List<User> selectUser(int uid);
	
	// 查询是否存在用户
	public int selectUserbyphone(String phone);
}

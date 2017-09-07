package com.sjgj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjgj.mapper.BackUserMapper;
import com.sjgj.pojo.BackUser;
import com.sjgj.service.BackUserService;
@Service
@Transactional
public class BackUserServcieImpl implements BackUserService {
@Resource
private BackUserMapper backUserMapper;
//根据用户名查询后台用户
public BackUser selectBuyerByUserName(String backusername) {
	
	return backUserMapper.selectBackUserByname(backusername);
}

}

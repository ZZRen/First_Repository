package com.sjgj.mapper;

import java.util.List;

import com.sjgj.pojo.Fbookstore;

public interface FbookstoreMapper {
	
	public void insertBookstore(Fbookstore fbookstore);
	
	public Fbookstore selectBookstoreByname(String storename);
	//查询所有商城名字
	public List<Fbookstore> selectBookstore();
}

package com.sjgj.mapper;

import java.util.List;

import com.sjgj.pojo.Fuser;
import com.sjgj.pojo.FuserQuery;

public interface FuserMapper {
//添加买家信息
public int insertfuser(Fuser fuser);
//根据手机号查询用户是否存在
public List<Fuser> selectfuserByphone(String phone);

//更新买家 地区用户信息

public void updatfuserByfuid(Fuser fuser);

//更新买家 用户所有信息

public void updatallfuserByfuid(Fuser fuser);

//列表展示
public List<Fuser> selectfuserListByObjectQuery(FuserQuery fuserQuery);

//查询数量

public int selectfuserCountByObjectQuery(FuserQuery fuserQuery);

//查询最后一次导入用户的信息
public Fuser selectlastfuser();
//删除买家信息
public void deleteByid(int fuid);

}

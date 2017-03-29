package com.hmybatis.dao;

import java.util.Map;

import com.hmybatis.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //Map传参
    int insertMap(Map<String, Object> map);
    
    //插入获取自增长主键
    int insertkey1(User record);
    int insertkey2(User record);
    
    //使用$符号实例
    int selectLike(String name);
}
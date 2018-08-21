package com.dazhi.demo.dazhi.dao;

import com.dazhi.demo.dazhi.bean.QueryUserInfo;
import com.dazhi.demo.dazhi.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectSelectiveUserList(QueryUserInfo QueryUserInfo);

    int countSelectiveUsers(QueryUserInfo QueryUserInfo);
}
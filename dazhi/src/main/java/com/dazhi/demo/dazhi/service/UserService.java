package com.dazhi.demo.dazhi.service;

import com.dazhi.demo.dazhi.bean.QueryUserInfo;
import com.dazhi.demo.dazhi.bean.User;

import java.util.List;

public interface UserService {
    public void add(User user);

    public User queryIdCached(int userId);

    public User queryIdNocache(int userId);

    public  User queryIdMaster(int userId);

    public List<User> query(int pageNum, int pageSize, QueryUserInfo queryUserInfo);

    public int queryCount(QueryUserInfo queryUserInfo);

    public void update(User user);

    public void delete(int userId);

    public void deleteCache(int userId);
}

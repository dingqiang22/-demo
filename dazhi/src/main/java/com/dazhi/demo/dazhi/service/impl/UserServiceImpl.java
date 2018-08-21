package com.dazhi.demo.dazhi.service.impl;

import com.dazhi.demo.dazhi.bean.QueryUserInfo;
import com.dazhi.demo.dazhi.bean.User;

import com.dazhi.demo.dazhi.dao.UserMapper;
import com.dazhi.demo.dazhi.service.UserService;
import io.shardingjdbc.core.api.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public User queryIdCached(int userId) {
        User user = (User) redisTemplate.opsForValue().get("dq_user" + userId);
        if (user == null) {
            user = userMapper.selectByPrimaryKey(userId);
            redisTemplate.opsForValue().set("dq_user" + userId, user);
        }
        return user;
    }

    @Override
    public User queryIdNocache(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public User queryIdMaster(int userId) {
        HintManager hintManager = this.routerMaster();
        User user = userMapper.selectByPrimaryKey(userId);
        this.closeHintManger(hintManager);
        return user;
    }

    @Override
    public List<User> query(int pageNum, int pageSize, QueryUserInfo queryUserInfo) {
        int start=(pageNum-1)*pageSize;
        int step=pageSize;
        queryUserInfo.setStart(start);
        queryUserInfo.setStep(step);
        HintManager hintManager = this.routerMaster();
        List<User> users = userMapper.selectSelectiveUserList(queryUserInfo);
        this.closeHintManger(hintManager);
        return users;
    }

    @Override
    public int queryCount(QueryUserInfo queryUserInfo) {
        HintManager hintManager = this.routerMaster();
        int count= userMapper.countSelectiveUsers(queryUserInfo);
        this.closeHintManger(hintManager);
        return count;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(int userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void deleteCache(int userId) {
        redisTemplate.delete("dq_user" + userId);
    }

    private HintManager routerMaster() {
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
        return hintManager;
    }

    private void closeHintManger(HintManager hintManager) {
        hintManager.close();
    }
}


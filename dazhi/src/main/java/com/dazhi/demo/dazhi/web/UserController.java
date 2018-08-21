package com.dazhi.demo.dazhi.web;

import com.dazhi.demo.dazhi.bean.QueryUserInfo;
import com.dazhi.demo.dazhi.bean.ResultCode;
import com.dazhi.demo.dazhi.bean.User;
import com.dazhi.demo.dazhi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/db")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(value = "deleteCache")
    public String deleteCache(int id) {
        userService.deleteCache(id);
        return "删除缓存成功";
    }

    @GetMapping(value = "queryCache")
    public User queryCache(int id) {
        return userService.queryIdCached(id);
    }

    @GetMapping(value = "queryNocache")
    public User queryNocache(int id) {
        return userService.queryIdNocache(id);
    }

    @GetMapping(value = "queryMaster")
    public User queryMaster(int id) {
        return userService.queryIdMaster(id);
    }

    @GetMapping(value = "insert")
    public ResultCode<String> insert(String username, String pwd, String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(pwd);
        user.setUserName(username);
        ResultCode<String> stringResultCode = new ResultCode<>();
        try {
            userService.add(user);
            stringResultCode.setCode(0);
            stringResultCode.setData("插入成功");
        } catch (Exception e) {
            stringResultCode.setCode(500);
            stringResultCode.setData("插入失败");
        }
        return stringResultCode;
    }

    @GetMapping(value = "update")
    public ResultCode<String> update(int userId,String username, String pwd, String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(pwd);
        user.setUserName(username);
        user.setUserId(userId);
        ResultCode<String> stringResultCode = new ResultCode<>();
        try {
            userService.update(user);
            stringResultCode.setCode(0);
            stringResultCode.setData("插入成功");
        } catch (Exception e) {
            stringResultCode.setCode(500);
            stringResultCode.setData("插入失败");
        }
        return stringResultCode;
    }

    @GetMapping(value = "list")
    public List<User> list(String username, String phone) {
        QueryUserInfo queryUserInfo = new QueryUserInfo();
        queryUserInfo.setPhone(phone);
        queryUserInfo.setUserName(username);
        List<User> list = userService.query(0, 0, queryUserInfo);
        return list;
    }

}

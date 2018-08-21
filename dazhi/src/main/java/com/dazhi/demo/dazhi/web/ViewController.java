package com.dazhi.demo.dazhi.web;

import com.dazhi.demo.dazhi.bean.QueryUserInfo;
import com.dazhi.demo.dazhi.bean.User;
import com.dazhi.demo.dazhi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "view")
public class ViewController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("name", "Dear");
        return "index";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String form(Model model,int userId) {
        User user = userService.queryIdMaster(userId);
        model.addAttribute("user", user);
        return "edit";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String edit(Model model) {
        return "form";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(name = "username",required = false,defaultValue = "") String userName,
                       @RequestParam(name = "phone",required = false,defaultValue = "") String phone,
                       @RequestParam(name = "page", defaultValue = "1") int page,
                       @RequestParam(name = "size", defaultValue = "10") int pageSize,Model model) {
        QueryUserInfo queryUserInfo = new QueryUserInfo();
        queryUserInfo.setUserName(userName);
        queryUserInfo.setPhone(phone);
        List<User> users = userService.query(page, pageSize,queryUserInfo);
        int queryCount = userService.queryCount(queryUserInfo);
        int totalPages = (queryCount + pageSize - 1) / pageSize;
        model.addAttribute("users", users);
        model.addAttribute("number", page);
        model.addAttribute("offset", 0);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("url", "list?size=" + pageSize+"&username="+userName+"&phone="+phone);
        return "listing";
    }
    @RequestMapping(value = "remove",method = RequestMethod.GET)
    public String  remove(int userId){
        userService.delete(userId);
        return "redirect:/view/list";
    }
}

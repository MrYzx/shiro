package com.yzx.shiro.controller;

import com.yzx.shiro.beans.User;
import com.yzx.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/com/yzx/cache")
public class CacheCOntroller {

    @Autowired
    UserService userService;
    @Resource
    CacheManager cacheManager;

    @RequestMapping("/testCache")
    @ResponseBody
    public String getUser(){
        User user = userService.findUser("1");
        Cache cache = cacheManager.getCache("myCache");
        User user1 = cache.get("id",User.class);
        return "cache success";
    }
}

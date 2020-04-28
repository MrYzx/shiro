package com.yzx.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户的授权Controller
 *
 * @author:yzx
 * @date:2019/11/15 23:42
 * @description:xxxx
 **/
@Controller
@RequestMapping("/yzx/com/authorization")
public class AuthorizationController {

    @RequestMapping("/think")
    public String think() {

        return "";
    }

}

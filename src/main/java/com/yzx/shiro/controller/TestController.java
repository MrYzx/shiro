package com.yzx.shiro.controller;

import com.yzx.shiro.beans.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:yzx
 * @date:2019/11/14 23:39
 * @description:xxxx
 **/
@Controller
@Api(tags = "测试管理控制器")
@RequestMapping("/yzx/com/test")
public class TestController {

    @RequestMapping("/main")
    @ApiOperation(value = "测试详情", notes = "用测试注意事项", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getUserToMainPage(Model model) {
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("user", ((User) subject.getPrincipal()).getName());
        return "page/main";
    }
}

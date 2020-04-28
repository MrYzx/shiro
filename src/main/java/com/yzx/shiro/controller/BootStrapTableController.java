package com.yzx.shiro.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/com/yzx/bootstrapTable")
public class BootStrapTableController {

    @ApiOperation(value = "跳转到table 页面")
    @RequestMapping("/toTablePage")
    public String toTablePage(Model model) {
        return "bootstrap-table/table-demo1";
    }

    @ApiOperation(value = "跳转到table 页面")
    @RequestMapping("/toTablePage2")
    public String toTablePage2(Model model) {
        return "bootstrap-table/table-demo2";
    }
}

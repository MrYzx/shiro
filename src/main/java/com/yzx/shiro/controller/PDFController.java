package com.yzx.shiro.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yzx
 * @date: 2020/01/16
 * @desc: PDF 的基本操作控制器
 */
@Controller
@RequestMapping("/com/yzx/pdf")
@Api("PDF 的基本操作")
public class PDFController {

    @RequestMapping("/showPdf")
    public String pdfShow(){
        System.out.println("pdf 的显示");
        return "";
    }

}

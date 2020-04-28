package com.yzx.shiro.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import com.yzx.shiro.utils.ValidateCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共的 Controller 类
 */
@Controller
@RequestMapping("/yzx/common/")
public class CommonController {

    /**
     * 验证码的获取
     *
     * @param request
     * @param response
     * @param type     验证码的类型
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response, String type) throws Exception {
        String codeString = "";
        //设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //验证码的生成
        if ("1".equals(type)) {//默认的
            CaptchaUtil.out(request, response);//自带验证码工具类的使用
        } else if ("2".equals(type)) {//计算形式的验证码
            ArithmeticCaptcha captcha = ValidateCodeUtil.getArithmeticCode();
            request.getSession().setAttribute("captcha", captcha.text()); // 验证码存入session
            captcha.out(response.getOutputStream()); // 输出图片流
        } else {//字母数字型的验证码
            SpecCaptcha specCaptcha = ValidateCodeUtil.getNumOrWordCode();
            request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase()); // 验证码存入session
            specCaptcha.out(response.getOutputStream()); // 输出图片流
        }
    }

}

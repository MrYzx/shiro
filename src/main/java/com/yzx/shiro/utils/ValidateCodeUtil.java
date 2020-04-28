package com.yzx.shiro.utils;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.shiro.crypto.hash.Md5Hash;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

/**
 * 验证码工具类，用于生成验证码
 */
public class ValidateCodeUtil {

    /**
     * 字母数字类型的验证码
     * 验证码字符类型
     * TYPE_DEFAULT	数字和字母混合
     * TYPE_ONLY_NUMBER	纯数字
     * TYPE_ONLY_CHAR	纯字母
     * TYPE_ONLY_UPPER	纯大写字母
     * TYPE_ONLY_LOWER	纯小写字母
     * TYPE_NUM_AND_UPPER	数字和大写字母
     *
     * @return specCaptcha
     */
    public static SpecCaptcha getNumOrWordCode() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);// 三个参数分别为宽、高、位数
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        //specCaptcha.setFont(Captcha.FONT_8);// 设置字体
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);// 设置类型，纯数字、纯字母、字母数字混合
        return specCaptcha;
    }

    /**
     * 计算类型的验证码
     *
     * @return
     */
    public static ArithmeticCaptcha getArithmeticCode() {
        // 2.算术类型的验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(3);  // 几位数运算，默认是两位
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        //captcha.text();  // 获取运算的结果：5
        return captcha;
    }
}

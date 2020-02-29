package com.yzx.shiro.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试拦截器
 */
public class MyIntorception implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyIntorception.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //进入controller 前进行拦截 ，返回 true时继续进行下一步，反之不能继续进行
        logger.info("进入controller 前进行拦截。。。");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染
        logger.info("拦截器拦截完。。。。。。。。。。话没有进入视图渲染。。。。");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可以做一些清理的工作了
        logger.info("拦截器拦截完成。。。。。。。。。");
    }
}

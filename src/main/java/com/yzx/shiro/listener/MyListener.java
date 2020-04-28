package com.yzx.shiro.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * application对象是服务器启动时创建， 服务器关闭时销毁。
 * reqeust.getSession();它是用于获取session
 * 请求发生，request对象创建，响应产生request对象销毁
 */
//@WebListener  java web 中监听器的注解
public class MyListener implements ServletRequestListener {

    public static int online = 0;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request 监听器 销毁。。。。。。。。。");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        //String contextPath = request.getRequestURI();
        //System.out.println(contextPath);
        System.out.println("request 监听器 创建。。。。。。。。");
    }
}

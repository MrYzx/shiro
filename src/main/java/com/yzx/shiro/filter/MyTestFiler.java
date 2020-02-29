package com.yzx.shiro.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyTestFiler implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化。。。。。。。。。");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁。。。。。。。。。");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;
        System.out.println("过滤器开始工作。。。。。。。。。");
        // 获得请求方式
        String getRequestURI =  rq.getRequestURI();
        System.out.println("getRequestURI:" + getRequestURI);
        filterChain.doFilter(rq,rp);
    }
}

package com.yzx.shiro.config;

import com.yzx.shiro.filter.MyTestFiler;
import com.yzx.shiro.interceptor.MyIntorception;
import com.yzx.shiro.listener.MyListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 环境中监听器，过滤器，拦截器的综合配置
 * 启动顺序  监听器 > 过滤器 > 拦截器
 */
//@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    /**
     * 过滤器的配置
     */
    @Bean
    public FilterRegistrationBean consumerLoginFilterRegistration() {
        FilterRegistrationBean<MyTestFiler> registration = new FilterRegistrationBean<>();
        //注册自定义过滤器
        registration.setFilter(myTestFiler());
        //设置拦截的请求
        //registration.addUrlPatterns("/*");
        registration.addUrlPatterns("/com/yzx/register");
        //设置过滤器的名称
        registration.setName("myTestFiler");
        //设置过滤器的排序
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public MyTestFiler myTestFiler() {
        //通过构造方法的方式注入对象
        return new MyTestFiler();
    }

    /**
     * 拦截器信息的注册
     * 1.通过 implements WebMvcConfigurer 的方式
     * 2.通过 extends WebMvcConfigurationSupport 这种方式会导致静态资源无法直接访问
     * 解决办法  重写 addResourceHandlers 方法；
     * registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //实现 WebMvcConfigurer 不会导致静态资源被拦截
        registry.addInterceptor(new MyIntorception())
                //拦截指定的 web 路径
                .addPathPatterns("/com/yzx/register");
    }

    /**
     * 监听器的配置
     * 用于监听servletContext、HttpSession和servletRequest等域对象的创建和销毁事件
     * 1、统计在线人数和在线用户
     * 2、系统启动时加载初始化信息
     * 3、统计网站访问量
     * 4、记录用户访问路径。
     */
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyListener());
        return srb;
    }
}

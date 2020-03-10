package com.yzx.shiro.design;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class StudentCglib implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //执行织入的日志
        if (method.getName().equals("save")) {
            System.out.println("方法前调用。。。。。。。");
            //执行原有逻辑，注意这里是invokeSuper
            Object rev = methodProxy.invokeSuper(o, objects);
            System.out.println("方法后调用。。。。。。。");
            return rev;
        }else{
            Object rev = methodProxy.invokeSuper(o, objects);
            return rev;
        }

    }
}

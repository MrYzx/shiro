package com.yzx.shiro.design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StudentInvocationHandler implements InvocationHandler {

    private Object target;

    public StudentInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("payMoney".equals(method.getName())){
            System.out.println("调用方法前。。");
            Object invoke = method.invoke(target,args);
            System.out.println("调用方法后。。");
            return invoke;
        }else{
            Object invoke = method.invoke(target, args);
            return invoke;
        }
    }
}

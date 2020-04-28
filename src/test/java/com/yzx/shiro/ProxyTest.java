package com.yzx.shiro;

import com.yzx.shiro.design.*;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class ProxyTest {

    //cglib代理的设置
    @Test
    public void test3() {
        //创建一个织入器
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(Student2.class);
        //设置需要织入的逻辑
        enhancer.setCallback(new StudentCglib());
        //使用织入器创建子类
        Student2 proxy = (Student2) enhancer.create();
        proxy.save();
        proxy.update();
    }

    //动态代理的设置
    @Test
    public void test2() {
        Student student = new Student("李思");
        //student.getClass().getInterfaces()  获取类的实现的接口
        //student.getClass().getClassLoader() 取得该Class对象的类装载器
        StudentInvocationHandler studentInvocationHandler = new StudentInvocationHandler(student);
        Person PersonProxy = (Person) Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(), studentInvocationHandler);
        PersonProxy.payMoney();
    }

    //静态代理的设置
    @Test
    public void test() {
        Student student = new Student("张三");
        StudentProxy proxy = new StudentProxy(student);
        proxy.payMoney();
    }
}

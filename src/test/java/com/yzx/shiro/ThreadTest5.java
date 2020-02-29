package com.yzx.shiro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest5 implements Runnable{

    private static ThreadLocal threadLocal = new ThreadLocal();
    public static void main(String[] args) {
        ThreadTest5 threadTest5 = new ThreadTest5();
        Thread thread = new Thread(threadTest5,"线程A：");
        Thread thread2 = new Thread(threadTest5,"线程2：");
        thread.start();
        thread2.start();
    }

    @Override
    public void run() {
        threadLocal.set(Thread.currentThread().getName());
        System.out.println("Thread  Name =="+threadLocal.get());
    }
}

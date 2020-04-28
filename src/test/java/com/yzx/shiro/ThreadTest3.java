package com.yzx.shiro;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadTest3 implements Callable {

    private int j;

    public static void main(String[] args) {
        ThreadTest3 threadTest2 = new ThreadTest3();
        FutureTask task1 = new FutureTask(threadTest2);
        FutureTask task2 = new FutureTask(threadTest2);
        Thread thread = new Thread(task1, "线程A");
        Thread thread2 = new Thread(task2, "线程B");
        thread.start();
        thread2.start();

        try {
            System.out.println("1 回调---" + thread.getName());
            System.out.println("2 回调---" + thread2.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 10; i++) {
            synchronized (ThreadTest3.class) {
                j++;
                System.out.println(Thread.currentThread().getName() + ".." + i);
            }
        }

        Thread.sleep(10000);
        return Thread.currentThread().getName();
    }
}

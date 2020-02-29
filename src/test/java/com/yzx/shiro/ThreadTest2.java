package com.yzx.shiro;

public class ThreadTest2 implements Runnable {

    private int j;

    public static void main(String[] args) {
        ThreadTest2 threadTest2 = new ThreadTest2();
        Thread thread = new Thread(threadTest2,"线程A");
        Thread thread2 = new Thread(threadTest2,"线程B");
        thread.start();
        thread2.start();

    }

    @Override
    public void run() {
        for(int i = 0; i<10; i++){
           synchronized (ThreadTest2.class){
                System.out.println(Thread.currentThread().getName()+"--"+i);
           }
        }
    }
}

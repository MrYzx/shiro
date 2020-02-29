package com.yzx.shiro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest4 extends Thread{

    private int j;
    public static void main(String[] args) {
        try{
           ThreadTest4 thread = new ThreadTest4();
           ThreadTest4 thread2 = new ThreadTest4();
           thread.setName("线程A");
           thread2.setName("线程B");
           thread.start();
           thread2.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try{
            System.out.println("上锁成功！");
            for(int a = 0; a<10; a++){
                System.out.println(Thread.currentThread().getName()+"---"+Thread.currentThread().getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("解锁成功！");
        }
    }
}

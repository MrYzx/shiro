package com.yzx.shiro;

public class ThreadTest extends Thread {

    private int j;

    public static void main(String[] args) {
        ThreadTest threadTest1 = new ThreadTest();
        ThreadTest threadTest2 = new ThreadTest();
        threadTest1.setName("线程A");
        threadTest2.setName("线程B");
        threadTest1.start();
        threadTest2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (ThreadTest.class) {
                System.out.println(getName() + ".." + i);
            }
        }
    }
}

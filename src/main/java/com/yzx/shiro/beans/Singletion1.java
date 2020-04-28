package com.yzx.shiro.beans;

/**
 * java 中的单列模式 饿汉式
 */
public class Singletion1 {

    private static Singletion1 singletion1 = null;

    private Singletion1() {
    }

    public static Singletion1 getInstance() {
        if (singletion1 == null) {
            singletion1 = new Singletion1();
        }
        return singletion1;
    }
}

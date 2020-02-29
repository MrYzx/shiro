package com.yzx.shiro.beans;

import java.io.Serializable;

/**
 * 创建用于测试的克隆类
 */
public class CloneTest implements Cloneable, Serializable {

    private int price;
    private String name;
    private String addr;
    private CloneTest2 cloneTest2;

    public CloneTest2 getCloneTest2() {
        return cloneTest2;
    }

    public void setCloneTest2(CloneTest2 cloneTest2) {
        this.cloneTest2 = cloneTest2;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CloneTest cloneTest = (CloneTest) super.clone();
        cloneTest.setCloneTest2((CloneTest2) cloneTest2.clone());
        return cloneTest;
    }

    @Override
    public String toString() {
        return "CloneTest{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", cloneTest2=" + cloneTest2 +
                '}';
    }
}

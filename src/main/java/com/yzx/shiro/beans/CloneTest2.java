package com.yzx.shiro.beans;

import java.io.Serializable;

public class CloneTest2 implements Cloneable, Serializable {

    private int num;
    private String cloneName;

    public CloneTest2() {
    }

    public CloneTest2(int num, String cloneName) {
        this.num = num;
        this.cloneName = cloneName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCloneName() {
        return cloneName;
    }

    public void setCloneName(String cloneName) {
        this.cloneName = cloneName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "CloneTest2{" +
                "num=" + num +
                ", cloneName='" + cloneName + '\'' +
                '}';
    }
}

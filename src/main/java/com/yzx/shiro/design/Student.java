package com.yzx.shiro.design;

public class Student implements Person {

    private String stuName;

    public Student(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public void payMoney() {
        System.out.println(stuName + "交学费50！");
    }
}

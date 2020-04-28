package com.yzx.shiro.design;

public class StudentProxy implements Person {

    private Student student;

    public StudentProxy(Student student) {
        if (student.getClass() == Student.class) {
            this.student = student;
        }
    }

    @Override
    public void payMoney() {
        student.payMoney();
    }
}

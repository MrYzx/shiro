package com.yzx.shiro.design;

public class ToosImpl implements Tools.Function, Tools {

    @Override
    public void price() {
        MyTest2 myTest2 = new MyTest2();
        myTest2.setAge("111");
        System.out.println("价格。。。。");
        System.out.println("age===" + myTest2.getAge());
    }

    @Override
    public void size() {
        System.out.println("大小。。。。");
    }

    @Override
    public void howUse() {
        System.out.println("如何使用。。。。");
    }

    class MyTest2 {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}

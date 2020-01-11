package com.yzx.shiro.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类的使用
 */
public class Book<T> {

    private String name;
    private String age;
    private T like;
    private List<T> list = new ArrayList<>(16);

    //通过泛型创建指定的对象
    public <T> T getBooks(Class<T> t) throws IllegalAccessException, InstantiationException {
        T c = t.newInstance();
        return c;
    }

    public void add(T like){
        list.add(like);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

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

    public T getLike() {
        return like;
    }

    public void setLike(T like) {
        this.like = like;
    }
}

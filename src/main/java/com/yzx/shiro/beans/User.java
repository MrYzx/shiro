package com.yzx.shiro.beans;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String id;
    private String name;
    private String sex;
    private String age;
    private String tel;
    private boolean avaliable;
    private String password;
    private String role;
    private List<String> permissions;
    private String salt; //盐值

    /**
     * 返回盐值
     * @return
     */
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isAvaliable() {
        return avaliable;
    }

    public User(String id, String name, String age, boolean avaliable, String password,
                String role, List<String> permissions, String salt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.avaliable = avaliable;
        this.password = password;
        this.role = role;
        this.permissions = permissions;
        this.salt = salt;
    }

    public User(String userId, String name, String age, boolean avaliable,
                String password, String role, List<String> permissions) {
        this.avaliable = avaliable;
        this.id = userId;
        this.name = name;
        this.age = age;
        this.password = password;
        this.role = role;
        this.permissions = permissions;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public User() {
    }

    public User(String userId, String name, String age, boolean avaliable, String password) {
        this.id = userId;
        this.name = name;
        this.age = age;
        this.avaliable = avaliable;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", tel='" + tel + '\'' +
                ", avaliable='" + avaliable + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

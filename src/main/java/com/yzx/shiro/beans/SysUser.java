package com.yzx.shiro.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统用户表
 */
@Entity
@Table
public class SysUser implements Serializable {
    private String id;
    @Column(name = "userId")
    private String userId;
    @Column(name = "userName")
    private String userName;
    private String password;
    private String salt;
    private String tel;
    private boolean locked;
    @Column(name = "roleId")
    private String roleId;
    private boolean avaliable;

    public SysUser() {
    }

    public SysUser(String userId, String userName, String password, String salt,
                             String tel, boolean locked, String roleId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.tel = tel;
        this.locked = locked;
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAvaliable() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

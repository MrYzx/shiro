package com.yzx.shiro.beans;

import java.io.Serializable;

/**
 * 用户角色表
 */
public class UserAndRole implements Serializable {

    private String urId;
    private String userId;
    private String roleId;

    public String getUrId() {
        return urId;
    }

    public void setUrId(String urId) {
        this.urId = urId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

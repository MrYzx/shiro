package com.yzx.shiro.beans;

/**
 * 角色权限表
 */
public class PermissionAndRole {

    private String prId;
    private String roleId;
    private String permissionId;

    public PermissionAndRole() {
    }

    public PermissionAndRole(String prId, String roleId, String permissionId) {
        this.prId = prId;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}

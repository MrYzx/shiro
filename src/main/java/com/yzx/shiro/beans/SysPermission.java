package com.yzx.shiro.beans;

/**
 * 系统权限表
 */
public class SysPermission {

    private String pId;
    private String permissionId;
    private String description;
    private String roleId;
    private boolean avaliabled;

    public SysPermission() {
    }

    public SysPermission(String pId, String permissionId, String description,
                         String roleId, boolean avaliabled) {
        this.pId = pId;
        this.permissionId = permissionId;
        this.description = description;
        this.roleId = roleId;
        this.avaliabled = avaliabled;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public boolean isAvaliabled() {
        return avaliabled;
    }

    public void setAvaliabled(boolean avaliabled) {
        this.avaliabled = avaliabled;
    }
}

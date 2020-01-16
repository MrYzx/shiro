package com.yzx.shiro.service;

import com.yzx.shiro.beans.SysUser;

import java.util.Map;

/**
 * 系统用户信息查询接口
 */
public interface SysUserService {
    public void addUser(SysUser sysUser);

    Map<String,Object> userList (int page, int limit);

    int deleteUser(SysUser sysUser);

    SysUser findUser(SysUser sysUser);
}

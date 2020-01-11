package com.yzx.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzx.shiro.beans.SysUser;
import com.yzx.shiro.mapper.SysUserMapper;
import com.yzx.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户的实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public void addUser(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }

    @Override
    public Map<String,Object> userList(int page, int limit) {
        Map<String,Object> map = new HashMap<>(16);
        int count = sysUserMapper.selectCount(new SysUser());
        PageHelper.startPage(page , limit);
        List<SysUser> list =sysUserMapper.selectAll();
        //得到分页的结果对象
        map.put("list",list);
        map.put("total",count);
        return map;
    }

    @Override
    public int deleteUser(SysUser sysUser) {
        return sysUserMapper.delete(sysUser);
    }
}

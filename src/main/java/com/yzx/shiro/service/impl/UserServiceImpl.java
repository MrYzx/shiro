package com.yzx.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzx.shiro.beans.User;
import com.yzx.shiro.mapper.UserMapper;
import com.yzx.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author:yzx
 * @date:2019/11/16 23:11
 * @description:xxxx
 **/
@Service
@CacheConfig(cacheNames = {"myCache"})
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> userList() {
        return userMapper.userList();
    }

    @Override
    public User getCallUser(Map<String, Object> map) {
        return userMapper.getUserByCall(map);
    }

    @CachePut(key = "user")
    @Override
    public Integer addUser(User user) {
        userMapper.addUser(user);
        return null;
    }

    @Override
    public Integer deleteUser(User user) {
        userMapper.deleteUser(user.getId());
        return null;
    }

    @Override
    public Integer updateUser(User user) {
        userMapper.updateUser(user);
        return null;
    }

    @Override
    public List<User> tkUserList(PageInfo pageInfo,User user) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        Example example =new Example(User.class);
        Example.Criteria criteria= example.createCriteria();
        //criteria.andEqualTo("aac002",cca4Temp.getAac002());
        //条件查询
       /* if(!"".equals(cca4Temp.getAaa00y()) && cca4Temp.getAaa00y()!=null){//申请时间
            criteria.andEqualTo("aaa00y",cca4Temp.getAaa00y());
        }
        if(!"".equals(cca4Temp.getSaa036()) && cca4Temp.getSaa036() != null){//申报编号
            criteria.andEqualTo("saa036",cca4Temp.getSaa036());
        }*/
        example.setOrderByClause(" id desc");                          //按照申请时间排序
        List<User> list = userMapper.selectByExample(example);
        return list;
    }

    @CachePut(key = "#id")
    @Override
    public User findUser(String id) {
        User user = new User();
        user.setId("111");
        user.setPassword("11111");
        user.setName("lisi");
        user.setAge("12");
        return user;
    }
}

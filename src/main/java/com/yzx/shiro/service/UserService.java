package com.yzx.shiro.service;

import com.github.pagehelper.PageInfo;
import com.yzx.shiro.beans.User;

import java.util.List;
import java.util.Map;

/**
 * @author:yzx
 * @date:2019/11/16 23:11
 * @description:xxxx
 **/
public interface UserService {

    List<User> userList();

    Integer addUser(User user);

    Integer deleteUser(User user);

    Integer updateUser(User user);

    List<User> tkUserList(PageInfo pageInfo, User user);

    User findUser(String id);

    User getCallUser(Map<String, Object> map);
}

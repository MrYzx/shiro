package com.yzx.shiro.mapper;

import com.yzx.shiro.beans.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author:yzx
 * @date:2019/11/16 22:59
 * @description:xxxx
 **/
@Repository
public interface UserMapper extends Mapper<User> {

    List<User> userList();

    void deleteUser(String userId);

    void addUser(User user);

    void updateUser(User user);

    User getUserByCall(Map<String,Object> map);
}

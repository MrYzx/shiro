package com.yzx.shiro.shiro.realm;

import com.yzx.shiro.beans.SysUser;
import com.yzx.shiro.beans.User;
import com.yzx.shiro.mapper.SysUserMapper;
import com.yzx.shiro.utils.MD5Util;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:yzx
 * @date:2019/11/13 22:56
 * @description:用户验证器，用于验证用户安全信息
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        List<String> allList = new ArrayList<>();
        allList.add("yonghu");
        allList.add("juese");
        allList.add("shenpi");
        //获取当前的用户信息
        SysUser user = (SysUser) principal.getPrimaryPrincipal();
        //通过simpleAuthorizationInfo 做授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //设置角色信息
        simpleAuthorizationInfo.addRole("admin");
        //设置权限信息
        simpleAuthorizationInfo.addStringPermissions(allList);
        System.out.println("开始授权认证。。。");
        return simpleAuthorizationInfo;
    }

    @Override
    /*
     * 认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //1.获取用户输入的账号
        String username = (String) token.getPrincipal();
        //2.通过username从数据库中查找到user实体
        //User user = getUserByUserName(username);
        SysUser sysUser1 = new SysUser();
        sysUser1.setUserName(username);
        SysUser sysUser = sysUserMapper.selectOne(sysUser1);
        sysUser.setAvaliable(true);
        if (sysUser == null) {
            return null;
        }
        //3.通过SimpleAuthenticationInfo做身份处理
        //SimpleAuthenticationInfo simpleAuthenticationInfo =
        //       new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),getName());

        //3.通过SimpleAuthenticationInfo做身份处理
        // ByteSource.Util.bytes(user.getSalt()) 可以将字符串转换为对应的盐值信息
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),
                        ByteSource.Util.bytes(sysUser.getUserName() + sysUser.getSalt()), getName());
        //4.用户账号状态验证等其他业务操作
        if (!sysUser.isAvaliable()) {
            throw new AuthenticationException("该账号已经被禁用");
        }
        //5.返回身份处理对象
        return simpleAuthenticationInfo;
    }

    /**
     * 模拟通过username从数据库中查找到user实体
     *
     * @param username
     * @return
     */
    private User getUserByUserName(String username) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 模拟数据库数据
     *
     * @return
     */
    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<String> allList = new ArrayList<>();
        String salt1 = Long.toString(System.currentTimeMillis());
        System.out.println("salt1====" + salt1);
        List<String> sectionList = new ArrayList<>();
        allList.add("yonghu");
        allList.add("juese");
        allList.add("shenpi");
        sectionList.add("yonghu");
        String password1 = MD5Util.getPassword("zhangsan", "222222", salt1, 2);
        users.add(new User("1", "zhangsan", "23", true, password1, "admin", allList, salt1));
        String salt2 = Long.toString(System.currentTimeMillis());
        String password2 = MD5Util.getPassword("zhangsan2", "333333", salt2, 2);
        users.add(new User("2", "zhangsan2", "23", true, password2, "employment", sectionList));
        return users;
    }
}

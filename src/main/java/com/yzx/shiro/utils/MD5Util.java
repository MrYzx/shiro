package com.yzx.shiro.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 使用 MD5 进行加密
 */
public class MD5Util {

    private MD5Util() throws Exception {
        throw new Exception("不允许实例化！");
    }

    /**
     * 对登录的用户密码进行MD5方式的盐值加密
     *
     * @param username  用户名
     * @param password  用户密码
     * @param salt      盐
     * @param hashTimes 时间
     * @return 加密后的密码
     */
    public static String getPassword(String username, String password, String salt, int hashTimes) {
        Md5Hash md5Hash = new Md5Hash(password, username + salt, hashTimes);
        return md5Hash.toString();
    }
}

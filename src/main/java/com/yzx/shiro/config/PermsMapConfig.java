package com.yzx.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author:yzx
 * @date:2019/11/16 18:21
 * @description:xxxx
 **/
@Component
@ConfigurationProperties(prefix = "permission-config")
public class PermsMapConfig {
    private List<Map<String,String>> perms;

    public List<Map<String, String>> getPerms() {
        return perms;
    }

    public void setPerms(List<Map<String, String>> perms) {
        this.perms = perms;
    }
}

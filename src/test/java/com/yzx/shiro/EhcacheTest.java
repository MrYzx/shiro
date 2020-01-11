package com.yzx.shiro;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ShiroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EhcacheTest {
    @Resource
    private CacheManager cacheManager;

    @Test
    public void testCache() {
        // 显示所有的Cache空间
        System.out.println(StringUtils.join(cacheManager.getCacheNames(), ","));
        Cache cache = cacheManager.getCache("myCache");
        cache.put("key", "123");
        System.out.println("缓存成功");
        String res = cache.get("key", String.class);
        System.out.println(res);
    }

    @Test
    public void testUtils(){
        List list = new ArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");

        String str = StringUtils.join(list,"-");
        System.out.println(str);

        String str2 = String.join("*",list);
        System.out.println(str2);
    }
}

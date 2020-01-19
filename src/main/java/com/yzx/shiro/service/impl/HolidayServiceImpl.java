package com.yzx.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.yzx.shiro.beans.SysHoliday;
import com.yzx.shiro.beans.SysUser;
import com.yzx.shiro.mapper.HolidayMapper;
import com.yzx.shiro.mapper.SysUserMapper;
import com.yzx.shiro.service.HolidayService;
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
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    HolidayMapper holidayMapper;

    @Override
    public int addHoliday(SysHoliday holiday) {
        holidayMapper.addHoliday(holiday);
        int id = holiday.getHid();
        return holiday.getHid();
    }

    @Override
    public SysHoliday findHolidayById(int hid) {
        SysHoliday holiday1 = holidayMapper.findHolidayById(hid);
        return holiday1;
    }
}

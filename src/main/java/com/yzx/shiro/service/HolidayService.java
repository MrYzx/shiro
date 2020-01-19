package com.yzx.shiro.service;

import com.github.pagehelper.PageInfo;
import com.yzx.shiro.beans.SysHoliday;
import com.yzx.shiro.beans.User;

import java.util.List;
import java.util.Map;

/**
 * @author:yzx
 * @date:2020/01/18 23:11
 * @description:请假service
 **/
public interface HolidayService {

    public int addHoliday(SysHoliday holiday);

    SysHoliday findHolidayById(int hid);

}

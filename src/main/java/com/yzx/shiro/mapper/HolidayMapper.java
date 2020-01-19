package com.yzx.shiro.mapper;

import com.yzx.shiro.beans.SysHoliday;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author:yzx
 * @date:2020/01/18 22:59
 * @description:xxxx
 **/
@Repository
public interface HolidayMapper extends Mapper<SysHoliday> {
    //添加请假信息并返回主键
    int addHoliday(SysHoliday holiday);

    //通过主键查询信息
    SysHoliday findHolidayById(int hid);
}

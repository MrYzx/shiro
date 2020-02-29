package com.yzx.shiro.beans;

import java.io.Serializable;

/**
 * 请假信息类
 */
public class SysHoliday implements Serializable {
    //主键
    private int hid;
    //标题
    private String head;
    //时长
    private Integer htime;
    //请假类型
    private String htype;
    //请假原因
    private String reason;
    //提交时间
    private String apptime;

    public String getApptime() {
        return apptime;
    }

    public void setApptime(String apptime) {
        this.apptime = apptime;
    }

    public Integer getHtime() {
        return htime;
    }

    public void setHtime(Integer htime) {
        this.htime = htime;
    }

    public String getHtype() {
        return htype;
    }

    public void setHtype(String htype) {
        this.htype = htype;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

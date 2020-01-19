package com.yzx.shiro.beans;

/**
 * 请假信息类
 */
public class SysHoliday {
    //主键
    private int hid;
    //标题
    private String head;
    //时长
    private String htime;
    //请假类型
    private String htype;
    //请假原因
    private String reason;

    public String getHtime() {
        return htime;
    }

    public void setHtime(String htime) {
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

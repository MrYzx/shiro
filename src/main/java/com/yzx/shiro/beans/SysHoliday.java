package com.yzx.shiro.beans;

/**
 * 请假信息类
 */
public class SysHoliday {
    //主键
    private String hid;
    //标题
    private String head;
    //时长
    private String time;
    //请假类型
    private String type;
    //请假原因
    private String reason;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

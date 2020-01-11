package com.yzx.shiro.constant;

/**
 * 定义枚举的颜色常量
 */
public enum ColorEnum {
    SUNDAY("SUN"),
    MONDAY("MON"),
    TUESDAY("TUE"),
    WEDNESDAY("WED"),
    THURSDAY("THU"),
    FRIDAY("FRI"),
    SATURDAY("SAT");

    private String abbr;

    //枚举的方法中默认是 private 的形式的
    ColorEnum(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() {
        return abbr;
    }
}

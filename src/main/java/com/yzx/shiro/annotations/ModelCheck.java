package com.yzx.shiro.annotations;

import com.yzx.shiro.constant.BookEnum;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelCheck {

    //定义注解中的属性变量，类型是BookEnum, 默认值是 BookEnum.BOOK1
    BookEnum[] value() default BookEnum.BOOK1;

    //定义一个其它类型的变量
    String meaning() default "";

    //定义注解中的常量值，可以在其注解中直接使用
    String NAME = "name";

}

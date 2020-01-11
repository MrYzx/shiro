package com.yzx.shiro.beans;

import com.yzx.shiro.annotations.ModelCheck;
import com.yzx.shiro.constant.BookEnum;

public class Order {

    @ModelCheck({BookEnum.BOOK,BookEnum.BOOK1,BookEnum.BOOK2})
    private String id;
    @ModelCheck(value = BookEnum.BOOK1,meaning = "名字")
    private String name;
    @ModelCheck(value = BookEnum.BOOK2,meaning = ModelCheck.NAME)
    private String price;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

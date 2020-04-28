package com.yzx.shiro.beans;

public class SubBook<T, E, h> extends Book<String> {

    private T book;
    private T[] arr;
    private E price;

    public E getPrice() {
        return price;
    }

    public void setPrice(E price) {
        this.price = price;
    }

    public T getBook() {
        return book;
    }

    public void setBook(T book) {
        this.book = book;
    }

    public T[] getArr() {
        return arr;
    }

    public void setArr(T[] arr) {
        this.arr = arr;
    }
}

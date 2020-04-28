package com.yzx.shiro;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComTest {


    public static void main(String[] args) {
        Map map = new HashMap();
        List<String> list = new ArrayList();
        list.add("bb");
        list.add("aa");
        String[] str2 = list.toArray(new String[list.size()]);
        List<String> list1 = Arrays.asList(str2);
        //list1.add("ccc");
        System.out.println(list1);
        System.out.println(Arrays.asList(str2));
        Collection<String> clist = Collections.unmodifiableList(list);
        //clist.add("aa");
        System.out.println("clist====" + clist);
        List list2 = Stream.of(str2).collect(Collectors.toList());
        System.out.println("lsit2===" + list2);

        List list3 = new ArrayList<>();
        Collections.addAll(list3, str2);
        System.out.println("list3==" + list3);
    }
}

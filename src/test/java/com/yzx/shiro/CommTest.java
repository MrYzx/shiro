package com.yzx.shiro;

import com.yzx.shiro.annotations.ModelCheck;
import com.yzx.shiro.beans.Book;
import com.yzx.shiro.beans.Order;
import com.yzx.shiro.beans.SubBook;
import com.yzx.shiro.beans.SysUser;
import com.yzx.shiro.constant.BookEnum;
import com.yzx.shiro.constant.ColorEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import sun.net.www.content.text.Generic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class CommTest {

    //map 中覆盖的使用
    @Test
    public void test3(){
        Map map = new HashMap();
        List list = new ArrayList();
        for(int i = 0;i<=2;i++){
            System.out.println("map==="+map);
            map.put("name"+i,"lisi"+i);
            map.put("age"+i,i);
            System.out.println("map2==="+map);
            list.add(map);
        }
        map.remove("name1");
        System.out.println("list==="+list);
    }


/*    // A 是 B 的子类 但是 List<A> 不是 List<B> 的子类
    //List<A> ,List<B> ... 是List<?> 的子类
    @Test
    public void testComm3(){
        List<Object> list = null;
        List<?>list4 = null;
        List<Integer> list1 = new ArrayList<>();
        List<Number> list2 = new ArrayList<>();
        List<?> list3 = new ArrayList<>();
        //Number 和 他的子类 都是 list5 的子类
        List<? extends Number> list5 = new ArrayList<>();
        //Number 和 他的父类 都是 list6 的子类
        List<? super Number> list6 = new ArrayList<>();

        //报错，list1 不是 list 的子类
        //list = list1;
        //list3 不是 list 的子类
        //list = list3;
        //list 是list4 的子类
        list4 = list;
        list5 = list1;
        list5 = list2;
        list6 = list;
    }


    //1.当实例化泛型类时，制定了泛型类的类型时，当前实体类中的所有的泛型类的位置都变为了指定的泛型类型
    //2.当泛型类没有指定时，默认的泛型类的类型为Object
    @Test
    public void testComm2() throws InstantiationException, IllegalAccessException {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("张三");
        Book<SysUser> book1 = new Book<>();
        book1.setLike(sysUser);
        System.out.println(book1.getLike().getUserName());

        //1.一个类中可以定义多个泛型类
        //2.当子类继承父类时，默认父类的泛型可以和子类的都为 Object
        SubBook<Integer,String,Integer> subBook = new SubBook<>();
        //3.通过泛型方法实例化对象
        Order order = book1.getBooks(Order.class);
        order.setName("aaaa");
        System.out.println(order.getName());

    }

    @Test
    public void testComm1(){

        ColorEnum colorEnum = ColorEnum.FRIDAY;
        String name = colorEnum.getAbbr();//获取枚举属性中括号中的内容
        System.out.println("name==="+name);
        System.out.println("颜色==="+colorEnum);
    }

    @Test
    public void testBB() throws IllegalAccessException, InstantiationException {
      //通过反射的方式获取数据
      Class<?> clazz= Order.class;
      //通过反射获取实例话对象【注意：该类中必须有默认的构造方法】
      Order order = (Order) clazz.newInstance();
      System.out.println("dd==="+order.getName());
      //通过反射获取所有的方法
      Method [] methods = clazz.getMethods();
      //通过反射获取所有 public 的字段
      Field [] fields = clazz.getDeclaredFields();
      //通过反射的方式获取类的所有注解
      Annotation[] annotation = clazz.getAnnotations();

      for(Field field : fields){
          //设置私有属性是否可以被反射到
          field.setAccessible(true);
          //查看注解ModelCheck 是否在 field 上
          if(field.isAnnotationPresent(ModelCheck.class)){
              //field 获取指定的注解ModelCheck
              ModelCheck modelCheck = field.getAnnotation(ModelCheck.class);
              //注解 ModelCheck 获取 所有的value 属性
              BookEnum[] bookEnums = modelCheck.value();
              for(BookEnum bookEnum : bookEnums){
                  System.out.println("bookEnum=="+bookEnum);
              }
              //获取字段的名称
              System.out.println(field.getName());
          }
      }

      for(Method method : methods){
          //获取指定方法上的注解
          ModelCheck modelCheck = method.getAnnotation(ModelCheck.class);
          modelCheck.meaning();
          System.out.println(method.getName()); //获取定义的method 中的名称
      }
    }

    public static void main(String[] args) {
// TODO Auto-generated method stub
        String transMessage = "<?xml version=\"1.0\" encoding=\"GBK\"?><message>" + "<body>" + "<ticketNotify>"
                + "<ticket id=\"6000012007051000000231\" dealTime=\"20070510165423\" status=\"0000\" message=\"成功,系统处理正常\"/>"
                + "<ticket id=\"6000012007051000000232\" dealTime=\"20070510165424\" status=\"2012\" message=\"禁止倍投\"/>"
                + "</ticketNotify>" + "</body></message>";
        try {
            Document document= DocumentHelper.parseText(transMessage);
            Element root=document.getRootElement();
            Iterator iterator=root.element("body").element("ticketNotify").elementIterator();
            while(iterator.hasNext()) {
                Element element=(Element) iterator.next();
                System.out.print(element.attributeValue("id")+" ");
                System.out.print(element.attributeValue("dealTime")+" ");
                System.out.println(element.attributeValue("status")+" ");
            }
        } catch (DocumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
}

package com.yzx.shiro;

import com.yzx.shiro.annotations.ModelCheck;
import com.yzx.shiro.beans.*;
import com.yzx.shiro.constant.BookEnum;
import com.yzx.shiro.constant.ColorEnum;
import com.yzx.shiro.design.Tools;
import com.yzx.shiro.design.ToosImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import sun.net.www.content.text.Generic;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CommTest {

    @Test
    public void test15() {
        Map map = new HashMap();
        Map map1 = new ConcurrentHashMap();
        Map map2 = new Hashtable();

    }

    //java 中list 集合的遍历
    @Test
    public void test14() {
        List<String> list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        //ListIterator 可以向在遍历的过程中向list 中添加元素
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String s = listIterator.next();
            System.out.println("s====" + s);
            //修改 list 中的数据
            listIterator.set("eeee");
        }
        System.out.println("size===" + list.size());
        System.out.println(list);
    }

    //java 中 map 集合的遍历
    @Test
    public void test13() {
        Map<String, Object> map = new HashMap<>();
        map.put("a1", "a");
        map.put("b1", "b");
        map.put("c1", "c");
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            System.out.println("s===" + s);
        }
        Collection collection = map.values();
        Iterator iterator1 = collection.iterator();
        while (iterator1.hasNext()) {
            String s2 = (String) iterator1.next();
            System.out.println("s2===" + s2);
        }

        //通过Map.Entry 的方式实现list 的遍历
        Set<Map.Entry<String, Object>> entry = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator2 = entry.iterator();
        while (iterator2.hasNext()) {
            Map.Entry<String, Object> map1 = iterator2.next();
            if ("c1".equals(map1.getKey())) {
                //map.remove(map1.getKey());//ConcurrentModificationException
                iterator2.remove();//使用迭代的方式删除map 中集合的元素
            }
            System.out.println(map1.getKey() + "=====" + map1.getValue());
        }
        System.out.println(map.size());
    }

    //java 中的接口中还可以定义接口，接口中不可以使用 final修饰，也不能使用 static
    //java 类中还可以定义类，只是定义的内部类只能在当前类中使用，不能在类外使用
    @Test
    public void test12() {
        Tools tools = new ToosImpl();
        tools.howUse();
        Tools.Function function = new ToosImpl();
        function.price();
    }

    @Test
    public void test11() {
        //单例模式的测试
        Singletion1 singletion1 = Singletion1.getInstance();
        Singletion1 singletion12 = Singletion1.getInstance();
        if (singletion1.equals(singletion12)) {
            System.out.println("单例对象");
        } else {
            System.out.println("不是单例对象");
        }
    }

    @Test
    public void test10() {

        int i = 0;
        i = ++i;
        System.out.println(i);
        int j = i++;
        System.out.println(j);
        System.out.println(i);
    }

    //测试java中对象的序列化和非序列化
    @Test
    public void test9() throws IOException, ClassNotFoundException {
        //java 对象的序列化
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/test.out"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(new CloneTest2(1, "44"));
        objectOutputStream.flush();
        objectOutputStream.close();
        //java 对象的反序列化
        FileInputStream fileInputStream = new FileInputStream(new File("d:/test.out"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        CloneTest2 cloneTest2 = (CloneTest2) objectInputStream.readObject();
        System.out.println(cloneTest2.getCloneName());
        System.out.println(cloneTest2.getNum());
    }

    //克隆类的使用
    @Test
    public void test8() throws CloneNotSupportedException {
        CloneTest cloneTest = new CloneTest();
        cloneTest.setName("aa");
        cloneTest.setPrice(12);
        cloneTest.setAddr("bbb");
        cloneTest.setCloneTest2(new CloneTest2(1, "wewe"));
        CloneTest cloneTest1 = (CloneTest) cloneTest.clone();

        cloneTest1.setAddr("bbbcc");
        cloneTest.getCloneTest2().setCloneName("DDDD");
        System.out.println("clone===" + cloneTest.toString());
        System.out.println("clone2===" + cloneTest1.toString());
        System.out.println(cloneTest1.getAddr() == cloneTest.getAddr());
    }

    //异常信息的使用
    @Test
    public void test7() {
        try {
            System.out.println("ccc");
            int a = 1 / 0;
        } catch (Exception e) {
            System.out.println("bbb");
            return;
        } finally {
            System.out.println("aaaa");
        }
    }

    //Files 的使用
    @Test
    public synchronized void test6() {
    }


    @Test
    public void test5() {
        List list = new ArrayList();
        Vector vector = new Vector();
        HashMap hashMap = new HashMap();
        hashMap.put("s", "d");
        HashSet hashSet = new HashSet();
        List list2 = new LinkedList();
        System.err.println("PDFUtil 不允许实例化！");
        Stack stack = new Stack();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        int size = stack.size();
        System.out.println("size===" + size);
        String a = (String) stack.pop();
        String c = (String) stack.peek();
        System.out.println(c);
        System.out.println(stack.size());


        List list3 = new ArrayList();
        list3.add("2");
        list3.add("3");
        list3.add("4");
        Iterator iterator = list3.iterator();
        while (iterator.hasNext()) {
            //list3.add("4");  添加修改元素时报错
            System.out.println(iterator.next());
        }

        Queue queue = new LinkedList();
        queue.add("a");
        queue.add("b");
        System.out.println("size==" + queue.size());
        System.out.println(queue.peek());
        System.out.println("size==" + queue.size());
        System.out.println(queue.poll());
        System.out.println("size==" + queue.size());
        System.out.println(queue.remove());
        System.out.println("size==" + queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.remove());

        ListIterator listIterator = list.listIterator();
    }

    @Test
    public void test4() {

        final int DF = 4;
        String aa = "aaa";
        String bb = "aaa";
        System.out.println(aa == bb);
        System.out.println("aaHash=" + aa.hashCode());
        System.out.println("bbHash=" + bb.hashCode());
        aa.equals(bb);
        StringBuilder builder = new StringBuilder();
        StringBuffer buffer = new StringBuffer();
        Object object = new Object();
        object.hashCode();
        Integer a = new Integer(2);
        a.equals(0);
        System.out.println(Math.round(-1.5));
    }

    //map 中覆盖的使用
    @Test
    public void test3() {
        Map map = new HashMap();
        List list = new ArrayList();
        for (int i = 0; i <= 2; i++) {
            System.out.println("map===" + map);
            map.put("name" + i, "lisi" + i);
            map.put("age" + i, i);
            System.out.println("map2===" + map);
            list.add(map);
        }
        map.remove("name1");
        System.out.println("list===" + list);
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

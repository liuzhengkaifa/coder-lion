package com.coder.lion.demo.tips;

import java.util.Optional;

/**
 * @author liuzheng
 * @date 2022年12月13日 16:20
 * @Description jdk8 optional 类使用
 */
public class OptionalUse {

    public static void main(String[] args) {

        Optional<String> test = Optional.ofNullable(null);
        if(test.isEmpty()){
            System.out.println("test为空 = ");
            return;
        }

        String s = test.get();
        String[] split = s.split(";");
        System.out.println("split = " + split);


        //创建一个值为张三的String类型的Optional
        Optional ofOptional14 = Optional.of("张三");
        //如果我们用of方法创建Optional对象时，所传入的值为null，则抛出NullPointerException如下图所示
       // Optional nullOptional15 = Optional.of(null);


        //为指定的值创建Optional对象，不管所传入的值为null不为null，创建的时候都不会报错
        Optional nullOptional5 = Optional.ofNullable(null);
        Optional nullOptional6 = Optional.ofNullable("lisi");

        //创建一个空的String类型的Optional对象
        Optional<String> emptyOptional7 = Optional.empty();

        //输出张三
        Optional stringOptional = Optional.of("张三");
        System.out.println(stringOptional.get());
        //抛出NoSuchElementException异常
        Optional emptyOptional8 = Optional.empty();
       // System.out.println(emptyOptional8.get());

        /**
         * 含义：如果Optional对象不为空，则返回值，如果为空，则返回指定值，如果此处使用的是函数，则无论 Optional 对象为不为空都将出发方法的调用
         */
        //输出张三
        Optional stringOptional9 = Optional.of("张三");
        System.out.println(stringOptional9.orElse("liuzhegn"));
        //输出李四
        Optional emptyOptional11 = Optional.empty();
        System.out.println(emptyOptional11.orElse("李四"));


        /**
         * orElseGet
         * 含义：如果Optional对象不为空，则返回值，如果为空，则返回由传入的Supplier函数式接口生成的值。
         */
        Optional stringOptional12 = Optional.of("张三");
        System.out.println(stringOptional12.orElseGet(() -> "zhangsan"));
        Optional emptyOptional13 = Optional.empty();
        System.out.println(emptyOptional13.orElseGet(() -> "orElseGet"));

        
        /**
         * filter
         * 如果创建的Optional中的值满足filter中的条件，则返回包含该值的Optional对象，否则返回一个空的Optional对象
         * 注意Optional中的filter方法和Stream中的filter方法是有点不一样的，Stream中的filter方法是对一堆元素进行过滤，而Optional中的filter方法只是对一个元素进行过滤，可以把Optional看成是最多只包含一个元素的Stream。
         */
        Optional stringOptional16 = Optional.of("zhangsan");
        System.out.println(stringOptional16.filter(e -> e.equals("zhangsan")).orElse("王五"));
        stringOptional = Optional.empty();
        System.out.println(stringOptional.filter(e -> e.equals("zhangsan")).orElse("lisi"));

        /**
         * ifPresent方法的参数是一个Consumer的实现类，Consumer类包含一个抽象方法，该抽象方法对传入的值进行处理，只处理没有返回值。
         */
        Optional stringOptional2 = Optional.of("zhangsan");
        stringOptional2.ifPresent(e-> System.out.println("我被处理了。。。"+e));

        /**
         * isPresent 如果有值返回true，否则返回false
         */
        Optional stringOptional3 = Optional.of("zhangsan");
        boolean present = stringOptional3.isPresent();
        System.out.println("present = " + present);

        /**
         * map方法执行传入的lambda表达式参数对Optional实例的值进行修改,修改后的返回值仍然是一个Optional对象
         */
        Optional<String> stringOptional4 = Optional.of("zhangsan");
        System.out.println(stringOptional4.map(e -> e.toUpperCase()).orElse("失败"));
        stringOptional4 = Optional.empty();
        System.out.println(stringOptional4.map(e -> e.toUpperCase()).orElse("失败"));

    }

}

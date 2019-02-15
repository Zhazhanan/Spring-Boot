package com.example.functionalprogramming;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author WangKun
 * @create 2019-02-15
 * @desc
 **/
public class FunctionalLearn {

    public static void fun() {
        Function<Integer, Integer> f = s -> ++s;
        Function<Integer, Integer> g = s -> s * 2;
        /**
         * 先执行G，并且执行F时使用G的输出当作输入
         **/
        System.out.println(f.compose(g).apply(2));
        /**
         * 表示执行F的Apply后使用其返回的值当作输入再执行G的Apply；
         **/
        System.out.println(f.andThen(g).apply(1));
        /**
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
        System.out.println(Function.identity().apply("a"));
    }

    public static void predicate() {
        Predicate<String> p = o -> o.equals("test");
        Predicate<String> g = o -> o.startsWith("t");
        /**
         * negate: 用于对原来的Predicate做取反处理；
         * 如当调用p.test("test")为True时，调用p.negate().test("test")就会是False；
         */
        System.out.println(p.negate().test("test"));
        /**
         * and: 针对同一输入值，多个Predicate均返回True时返回True，否则返回False；
         */
        System.out.println(p.and(g).test("test"));

        /**
         * or: 针对同一输入值，多个Predicate只要有一个返回True则返回True，否则返回False
         */
        System.out.println(p.or(g).test("ta"));
    }

    public static void con() {
        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        };

        Consumer consumer1 = (o) -> {
            System.out.println(o);
        };
        Consumer c = (o) -> System.out.println("c:" + o);
        Consumer c1 = System.out::println;

//        consumer.accept("123");
//        consumer1.accept("1234");
//        c.accept("321");
        c1.andThen(c).accept("3210");
    }

    public static void main(String[] args) {
//        fun();
        predicate();
    }
}

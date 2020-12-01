package org.github.yibing.spring.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    List<Employee> emps = Arrays.asList(new Employee(101, "张三", 28, 9999d),
            new Employee(101, "李四", 49, 666d),
            new Employee(102, "王五", 38, 333d),
            new Employee(103, "赵六", 12, 7777d),
            new Employee(104, "田七", 6, 222d)
    );

    public static void main(String[] args) {
        /**
         * stream 的创建方式
         */

        // 集合创建
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        // 数组创建
        Integer[] integers = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(integers);
        // Stream静态方法传概念
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);
        // 创建无线流
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 1);


    }

    // 中间操作-过滤
    @Test
    public void test1() {
        emps.stream()
                .filter((e) -> e.getAge() > 20)
                .limit(2)  // limit(long n) :截取前面 n 条  ， skip(long n):跳过前面 n 条
                .forEach(System.out::println);
    }

    // 中间操作-映射
    @Test
    public void test2() {
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        Stream<String> stream = strList.stream().map(String::toUpperCase);
        stream.forEach(System.out::println);

//        Stream<Stream<Character>> stream1 = strList.stream().map(StreamDemo::filterCharacter);
//        stream1.forEach((sm) -> {
//            sm.forEach(System.out::print);
//        });
        Stream<Character> stream2 = strList.stream().flatMap(StreamDemo::filterCharacter);
        stream2.forEach(System.out::print);

    }

    // 中间操作-排序
    @Test
    public void test3() {
        emps.stream()
                .map(Employee::getName)
                .sorted() // 排序
                .forEach(System.out::println);

        emps.stream()
                .sorted((x, y) -> {
                    if (x.getAge() == y.getAge()) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }) // 排序
                .forEach(System.out::println);
    }

    // 查找与匹配
    @Test
    public void test4() {
        // 查看每个名字中是否都包含"五" ,这里返回false
        boolean b = emps.stream().allMatch((e) -> e.getName().contains("五"));
        System.out.println(b);
        // 返回第一个
        Optional<Employee> first = emps.stream().findFirst();
        System.out.println(first.get());
    }

    // 归约
    @Test
    public void test5() {
        // 从0开始加 ，加到10
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        // 求所有员工的薪水总和
        Optional<Double> op = emps.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(op.get());
    }

    //收集 ： 将流转换成其他形式，collect(Collectors.（））
    @Test
    public void test6() {
        List<String> list = emps.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);
        Set<String> set = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);

        // 薪水求和
        Double sum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        //求平均薪水
        Double avg = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
    }

    public static Stream<Character> filterCharacter(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
}

package org.github.yibing.spring.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo2 {
    static List<Employee> emps = Arrays.asList(new Employee(101, "张三", 28, 9999d),
            new Employee(101, "李四", 49, 666d),
            new Employee(102, "王五", 38, 333d),
            new Employee(103, "赵六", 12, 7777d),
            new Employee(104, "田七", 6, 222d)
    );

    public static void main(String[] args) {
        /*
         * 创建stream的三种方式
         */
        //1.集合   list.stream(),
        Stream<Employee> stream = emps.stream();
        Employee[] array = (Employee[]) emps.toArray();
        // 2.数组  Arrays.stream(array)
        Stream<Object> stream1 = Arrays.stream(array);
        // 3.Stream.of(T)
        Stream<List<Employee>> stream2 = Stream.of(StreamDemo2.emps);


    }

    /**
     * 中间操作：
     * 筛选  filter()
     * 去重 distinct()
     * 取前面几条/跳过前面几条  limit()/skip()
     */

    @Test
    public void test1() {
        //中间操作------筛选
        emps.stream().filter((e) -> e.getAge() > 30).forEach(e -> System.out.println(e.getAge()));
        // 取前面几条
        emps.stream().filter(e -> e.getId() > 101).limit(3).forEach(System.out::println);
        // 需要重写hashcode()和equals 才能去重
        emps.stream().distinct().forEach(System.out::println);
    }

    /**
     * 中间操作：
     * 映射，把集合中每个元素按照function映射为对应的类型
     */
    @Test
    public void test2() {
        // 把每个元素按照function转换成相应类型，输出  还有mapToInteger,mapToLong等方法
        emps.stream().mapToDouble(Employee::getId).forEach(System.out::println);
        List<String> strList = Arrays.asList("a", "b", "c", "d");
        /*
            flapMap和map，传入同一个返回流的function,flatMap可以把所有流连成一个流
         */
        Stream<Character> rStream = strList.stream().flatMap(StreamDemo2::filterCharacter);
        rStream.forEach(System.out::println);

        Stream<Stream<Character>> stream = strList.stream().map(StreamDemo2::filterCharacter);
        stream.forEach(sm -> sm.forEach(System.out::print));
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 中间操作：
     * sorted()  默认顺序排序，引用类型要实现Comparable
     * sorted(Comparator comp)  带参数，指定排序规则
     */
    @Test
    public void test3() {
        // 把每个emps映射为属性:name,组成一个新流，然后按name排序输出
        emps.stream().map(Employee::getName).sorted().forEach(System.out::println);
        // 带参数的sorted,按照年龄排序，若相等就比较名字
        emps.stream().sorted((x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        }).forEach(System.out::println);
    }


    /**
     * 终止操作: 查找与匹配,返回boolean
     * allMatch(),所有元素都匹配才返回true
     * anyMatch(),只要有一个或多个元素匹配就返回true
     * noneMatch(),都不匹配就返回true,否则false
     */
    @Test
    public void test4() {
        // 每个员工的名字都包含三 ，才返回true
        boolean b = emps.stream().allMatch(e -> e.getName().contains("三"));
        System.out.println(b);
        boolean b1 = emps.stream().noneMatch(e -> e.getName().contains("九"));
        System.out.println(b1);

    }

    /**
     * 终止操作：查找与匹配：返回元素
     * findFirst:返回第一个Optional<>
     * findAny:返回任意一个元素Optional<>
     * count  返回元素个数
     * max 返回最大值
     * min 返回最小值
     */

    @Test
    public void test5() {
        Optional<Employee> first = emps.stream().findFirst();
        System.out.println(first.get());
        long count = emps.stream().count();
        System.out.println(count);
    }


    /**
     * 终止操作：规约reduce，将每个元素结合起来，得到一个值
     * reduce(BinaryOperator b)
     * reduce(T iden,BinaryOperator b) 返回Optional
     */
    @Test
    public void test6() {
        // 规约，先转化成年龄集合，然后给他们求和
        Optional<Integer> sum = emps.stream().map(Employee::getAge).reduce(Integer::sum);
        System.out.println(sum.get());
        // 从 1 相加，相当于该集合多了一个 1求和
        Integer sum1 = emps.stream().map(Employee::getAge).reduce(1, Integer::sum);
        System.out.println(sum1);
    }

    /**
     * 终止操作：收集
     * 转换成lit、set、Collection
     * 求和，求平均数
     */
    @Test
    public void test7() {
        Set<Employee> set = emps.stream().collect(Collectors.toSet());
        System.out.println(set);
        List<Employee> list = emps.stream().collect(Collectors.toList());
        Double salarySum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));

    }
}

package org.github.yibing.spring.ioc.autowire;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InstantiateDemo {

    public static void main(String[] args) {
//        javaconfig();
//        xmlconfig();
    }


    private static void xmlconfig() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:instantiate-type.xml");
        Teacher teacher = (Teacher) beanFactory.getBean("teacher");
        System.out.println(teacher);
    }

    private static void javaconfig() {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(AppConfig.class);
        Student student = (Student) beanFactory.getBean("student");
        System.out.println(student);
    }
}

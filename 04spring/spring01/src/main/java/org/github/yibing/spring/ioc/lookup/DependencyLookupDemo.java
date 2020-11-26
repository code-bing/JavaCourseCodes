package org.github.yibing.spring.ioc.lookup;

import org.github.yibing.spring.ioc.annotation.Super;
import org.github.yibing.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {
    public static void main(String[] args) {
        BeanFactory context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        lookupInRealTime(context);
//        lookupInLazy(context);
//        lookupByType(context);
//        lookupCollectionByType(context);
//        lookupByAnnotation(context);
    }

    private static void lookupByAnnotation(BeanFactory context) {
        if(context instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) context;
            Map<String, Object> users = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("按注解查找集合："+users);
        }
    }

    private static void lookupCollectionByType(BeanFactory context) {
        if(context instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) context;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("按类型查找集合："+users);
        }
    }

    private static void lookupByType(BeanFactory context) {
        User user = context.getBean(User.class);
        System.out.println("按类型查找："+user);
    }

    private static void lookupInLazy(BeanFactory context) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) context.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找："+user);
    }

    private static void lookupInRealTime(BeanFactory context) {
        User user = (User) context.getBean("user");
        System.out.println("实时查找："+user);
    }
}

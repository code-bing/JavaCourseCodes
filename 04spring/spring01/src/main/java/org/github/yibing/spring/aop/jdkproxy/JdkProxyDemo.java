package org.github.yibing.spring.aop.jdkproxy;

import java.lang.reflect.Proxy;

public class JdkProxyDemo {
    public static void main(String[] args) {
        Animal bird = new Bird();
        BirdProxy birdProxy = new BirdProxy(bird);
        Animal instance = (Animal) Proxy.newProxyInstance(bird.getClass().getClassLoader(), bird.getClass().getInterfaces(), birdProxy);
        System.out.println("代理的类型：" + bird.getClass().getName());
        instance.breathe();
    }
}

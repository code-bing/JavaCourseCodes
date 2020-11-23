package org.github.yibing.spring.aop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BirdProxy implements InvocationHandler {

    private Animal bird;

    public BirdProxy(Animal bird) {
        this.bird = bird;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke ........");
        Object obj = method.invoke(bird, args);
        System.out.println("after invoke ....");
        return obj;
    }
}

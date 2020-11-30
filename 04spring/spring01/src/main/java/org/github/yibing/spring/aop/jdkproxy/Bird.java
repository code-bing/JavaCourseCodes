package org.github.yibing.spring.aop.jdkproxy;

public class Bird implements Animal {
    @Override
    public void breathe() {
        System.out.println("animal can breathe..");
    }
}

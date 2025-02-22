package org.github.yibing.spring.strategy;

public class StrategyDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10+6=" + context.executeStrategy(10, 6));
        context = new Context(new OperationSubtract());
        System.out.println("10-6=" + context.executeStrategy(10, 6));
    }
}

package com.github.thread;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();
        // 异步执行 下面方法
        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程

    }

    /**
     * 使用join,子线程通过join确保执行
     */
    @Test
    public void test01() {
        AtomicInteger integer = new AtomicInteger();

        Thread thread = new Thread(() -> integer.set(sum()));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + integer.get());
    }

    // 主线程sleep 1秒钟，把cpu让给子线程执行
    @Test
    public void test02() {
        AtomicInteger integer = new AtomicInteger();

        Thread thread = new Thread(() -> integer.set(sum()));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + integer.get());
    }

    // futureTask,获取线程返回值，但是会阻塞
    @Test
    public void test03() {
        int result = 0;
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });

        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            // futureTask.get()会阻塞
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为：" + result);
    }

    // 主线程wait让给子线程执行，完了之后再唤醒
    @Test
    public void test04() {
        AtomicInteger atomicInteger = new AtomicInteger();
        new Thread(() -> atomicInteger.set(sum())).start();
        synchronized (this) {
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("异步计算结果为：" + atomicInteger.get());
        synchronized (this) {
            this.notify();
        }
    }

    //CountDownLatch
    @Test
    public void test05() {
        AtomicInteger atomicInteger = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            atomicInteger.set(sum());
            countDownLatch.countDown();
        }).start();

        try {
            countDownLatch.await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("异步计算结果为：" + atomicInteger.get());

    }

    //CyclicBarrier
    @Test
    public void test06() {
        AtomicInteger atomicInteger = new AtomicInteger();
        CyclicBarrier barrier = new CyclicBarrier(2);
        new Thread(() -> {
            atomicInteger.set(sum());
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("异步计算结果为：" + atomicInteger.get());

    }

    //CyclicBarrier
    @Test
    public void test07() {

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}

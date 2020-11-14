package com.github.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool01 {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                6,
                0,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                },
                new ThreadPoolExecutor.AbortPolicy());
        MyTask task = new MyTask();
        for (int i = 0; i < 100; i++) {
            try {
                poolExecutor.execute(task);
            } catch (Exception e) {
                e.printStackTrace();
                poolExecutor.shutdown();
            }
        }
        poolExecutor.shutdown();
    }

}

class MyTask implements Runnable {
    private int count = 0;
    private static final int MAXVALUE = 1000;

    @Override
    public void run() {
        for (int i = 0; i < MAXVALUE; i++) {
            System.out.println(++count);
        }
    }
}

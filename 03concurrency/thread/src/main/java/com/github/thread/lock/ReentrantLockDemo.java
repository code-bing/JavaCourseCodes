package com.github.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Task01 task01 = new Task01();
        new Thread(task01).start();
        new Thread(task01).start();
    }
}

class Task01 implements Runnable {
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "运行 ------------" + i);
        }
        lock.unlock();
    }
}

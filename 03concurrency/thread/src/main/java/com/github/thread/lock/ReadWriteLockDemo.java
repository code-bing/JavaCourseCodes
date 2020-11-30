package com.github.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        Task02 task02 = new Task02(readLock);
        Task02 task03 = new Task02(writeLock);
        for (int i = 0; i < 5; i++) {
//            new Thread(task02).start();
            new Thread(task03).start();
        }

    }
}

class Task02 implements Runnable {

    Lock lock;

    public Task02(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(lock.getClass().getName() + " " + Thread.currentThread().getName());
        lock.unlock();
    }
}


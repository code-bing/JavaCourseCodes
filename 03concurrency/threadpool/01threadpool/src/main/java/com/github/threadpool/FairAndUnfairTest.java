package com.github.threadpool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {
    private static  Lock fairLock = new ReentrantLock2(true);
    private static  Lock unfairLock = new ReentrantLock2(false);

    @Test
    public void fair(){
        testLock(fairLock);
    }

    @Test
    public void unfair(){
        testLock(unfairLock);
    }

    private void testLock(Lock lock){
        for (int i = 0; i < 5; i++) {
            new Job(lock).start();
        }
    }
    private static class Job extends Thread{
        private ReentrantLock2 lock;
        public Job(Lock lock){
            this.lock = (ReentrantLock2) lock;
        }

        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"，"+lock.getQueuedTreads());
            System.out.println(Thread.currentThread().getName()+"，"+lock.getQueuedTreads());
            lock.unlock();
        }
    }

    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair){
            super(fair);
        }

        public Collection<Thread> getQueuedTreads(){
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}

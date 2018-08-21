package com.dazhi.demo.dazhi.thread;

import java.util.concurrent.CountDownLatch;

public class TaskRunnable implements Runnable{
        private CountDownLatch countDownLatch;
        private int a;
    public TaskRunnable(CountDownLatch countDownLatch,int a ) {
        this.countDownLatch=countDownLatch;
        this.a=a;
    }

    @Override
    public void run() {
        System.out.println(a);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();

    }
}
package com.dazhi.demo.dazhi.task;

import com.dazhi.demo.dazhi.thread.TaskRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class QuartzService {

    protected static final Logger logger = LoggerFactory.getLogger(QuartzService.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    //    每分钟启动
    @Scheduled(cron = "0 0/1 * * * ?")
    public void timerToNow() {
        logger.info("执行了定时任务");
        logger.info("开始执行定时任务now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new TaskRunnable(countDownLatch, i));
        }
        try {
            countDownLatch.await();
            logger.info("线程全部执行完成 往下走");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
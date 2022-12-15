package com.coder.lion.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author liuzheng
 * @date 2022年12月15日 11:46
 * @Description 自定义ThreadPoolTaskExecutor
 */
public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    Logger logger = LoggerFactory.getLogger(MyThreadPoolTaskExecutor.class);

    @Override
    public void execute(Runnable task) {
        logThreadPoolStatus();
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        logThreadPoolStatus();
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        logThreadPoolStatus();
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        logThreadPoolStatus();
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        logThreadPoolStatus();
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        logThreadPoolStatus();
        return super.submitListenable(task);
    }

    /**
     * 在线程池运行的时候输出线程池的基本信息
     */
    private void logThreadPoolStatus() {
        logger.info("核心线程数:{}, 最大线程数:{}, 当前线程数: {}, 活跃的线程数: {}",
                getCorePoolSize(), getMaxPoolSize(), getPoolSize(), getActiveCount());
    }
}
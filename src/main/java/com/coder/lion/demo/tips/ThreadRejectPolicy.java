package com.coder.lion.demo.tips;

import java.util.concurrent.*;

/**
 * @author liuzheng
 * @date 2022年12月14日 19:16
 * @Description TODO
 */
public class ThreadRejectPolicy {

    public static void main(String[] args) {

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
        ThreadFactory factory = r -> new Thread(r, "test-thread-pool");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.SECONDS, queue, factory);
        while (true) {
            executor.submit(() -> {
                try {
                    System.out.println(queue.size());
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

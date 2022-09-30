package com.coder.lion.demo.service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.coder.lion.demo.config.SpringJobBeanFactory;
import com.coder.lion.demo.mapper.DeadManMapper;
import com.coder.lion.demo.model.entity.DeadMan;
import com.coder.lion.demo.model.excel.DeadManExcelData;
import com.coder.lion.demo.service.thread.DeadManThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzheng
 * @date 2022年09月30日 10:51
 * @Description TODO
 */
@Service
@Slf4j
public class DeadManExcelDataListener extends AnalysisEventListener<DeadManExcelData> {

    /**
     * 多线程保存集合，使用线程安全集合
     */
    private List<DeadManExcelData> list = Collections.synchronizedList(new ArrayList<>());

    //核心线程数
    private static final int CORE_POOL_SIZE = 5;
    //最大线程数
    private static final int MAX_POOL_SIZE = 10;
    //队列大小
    private static final int QUEUE_CAPACITY = 100;
    //存储时间
    private static final long KEEP_ALIVE_TIME = 1L;

    public List<DeadManExcelData> getData() {
        return list;
    }

    public void setData(List<DeadManExcelData> deadManExcelDataList) {
        this.list = deadManExcelDataList;
    }

    public DeadManExcelDataListener() {

    }


    @Override
    public void invoke(DeadManExcelData deadManExcelData, AnalysisContext analysisContext) {
        //log.info("接收到 {}", deadManExcelData);
        if (deadManExcelData != null) {
            list.add(deadManExcelData);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("解析结束，开始处理数据,共 {} 条",list.size());
        //创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());

        //指定每个线程需要处理的导入数量，假设每个线程处理1000条
        int singleThreadDealCount = 50;

        //根据假设每个线程需要处理的数量以及总数，计算需要提交到线程池的线程数量
        int threadSize = (list.size()/singleThreadDealCount)+1;

        //计算需要导入的数据总量，用于拆分时线程需要处理数据时使用
        int rowSize = list.size() + 1;

        //测试开始时间
        long startTime = System.currentTimeMillis();

        //声明该线程需要处理数据的开始位置
        int startPosition = 0;

        //声明该线程需要处理数据的结束位置
        int endPosition = 0;

        //为了让每个线程执行完回到当前线程，使用countDownLatch,值为线程数，每次线程执行完就会执行countDown方法减1，为0时回到主线程
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        //计算每个线程要处理的数据
        for(int i = 0;i<threadSize;i++){
            //如果是最后一个线程，为保证程序不发生空指针异常，特使判断结束位置
            if((i+1)==threadSize){
                //计算开始位置
                startPosition = (i * singleThreadDealCount);
                //当前线程为划分的最后一个线程，则取总书记的最后为此线程的结束位置
                endPosition = rowSize -1;
            }else{
                //计算开始位置
                startPosition = (i* singleThreadDealCount);
                //计算结束位置
                endPosition = (i+1)* singleThreadDealCount;
            }
            DeadManMapper deadManMapper = SpringJobBeanFactory.getBean(DeadManMapper.class);
            DeadManThread deadManThread = new DeadManThread(countDownLatch, deadManMapper, list, startPosition, endPosition);
            threadPoolExecutor.execute(deadManThread);
        }
        try{
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }

        //逻辑处理完，关闭线程池
        threadPoolExecutor.shutdown();

        // 测试开始时间
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时 = " + (endTime - startTime));
    }
}

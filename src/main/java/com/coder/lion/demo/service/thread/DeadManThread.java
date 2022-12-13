package com.coder.lion.demo.service.thread;

import com.coder.lion.demo.mapper.DeadManMapper;
import com.coder.lion.demo.model.entity.DeadMan;
import com.coder.lion.demo.model.excel.DeadManExcelData;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author liuzheng
 * @date 2022年09月30日 15:39
 * @Description
 */
public class DeadManThread implements Runnable {

    private int startPosition;

    private int endPosition;

    private List<DeadManExcelData> list =  Collections.synchronizedList(new ArrayList<>());

    private CountDownLatch count;

    private DeadManMapper deadManMapper;

    public DeadManThread(){

    }

    public DeadManThread(CountDownLatch count,DeadManMapper deadManMapper,List<DeadManExcelData> list,int startPosition,int endPosition){
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.deadManMapper = deadManMapper;
        this.list = list;
        this.count = count;
    }


    @Override
    public void run() {
        try {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            List<DeadMan> deadManList = new ArrayList<>();
            List<DeadManExcelData> newList = list.subList(startPosition,endPosition);
            //将EasyExcel对象和实体对象进行一个转换
            for (DeadManExcelData deadManExcelData : newList) {
                DeadMan deadMan = new DeadMan();
                BeanUtils.copyProperties(deadManExcelData,deadMan);
                deadManList.add(deadMan);
            }
            //deadManMapper.insert();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //当一个线程执行完了，计数要减一下，不然这个线程会一直挂起
            System.out.println("Thread.currentThread().getName()+\"------------------\" = " + Thread.currentThread().getName() + "------------------");
            count.countDown();
        }
    }
}

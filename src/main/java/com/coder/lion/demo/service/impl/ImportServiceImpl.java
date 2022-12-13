package com.coder.lion.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.coder.lion.demo.model.excel.DeadManExcelData;
import com.coder.lion.demo.service.ImportService;
import com.coder.lion.demo.service.listener.DeadManExcelDataListener;
import com.coder.lion.demo.utils.MultipartFileToFileUtils;
import com.coder.lion.demo.utils.RedisUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @author liuzheng
 * @date 2022年09月30日 10:19
 * @Description
 */
@Service
public class ImportServiceImpl implements ImportService {

    @Resource
    RedisUtils redisUtils;

    @Override
    public String importDeadManList(MultipartFile file) {
        EasyExcel.read(MultipartFileToFileUtils.multipartFileToFile(file), DeadManExcelData.class,new DeadManExcelDataListener()).sheet().doRead();
        return "导入成功";
    }

    @Override
    public String saveRedisVale() {
        redisUtils.set("liuzheng", "yyds");
        return null;
    }

    @Async
    @Override
    public Future addUserByAsync(String str) throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("ImportServiceImpl.addUserByAsync--start" + str + Thread.currentThread());
        Thread.sleep(3000);
        System.out.println("ImportServiceImpl.addUserByAsync--end");
        long end = System.currentTimeMillis();
        long use = end- start;
        System.out.println("耗时 = " + use + "ms");
        return null;
    }

    @Async
    @Override
    public void updateByAsync(String str) throws InterruptedException {
        System.out.println("str = " + str);
        long start = System.currentTimeMillis();
        System.out.println("ImportServiceImpl.updateByAsync--start" + str + Thread.currentThread());
        Thread.sleep(2000);
        System.out.println("ImportServiceImpl.updateByAsync--end");
        long end = System.currentTimeMillis();
        long use = end- start;
        System.out.println("耗时 = " + use + "ms");
    }
}

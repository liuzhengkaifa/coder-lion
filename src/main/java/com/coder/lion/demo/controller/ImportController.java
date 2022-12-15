package com.coder.lion.demo.controller;

import com.coder.lion.demo.service.ImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.concurrent.Future;

/**
 * @author liuzheng
 * @date 2022年09月30日 10:18
 * @Description TODO
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/import")
@Slf4j
public class ImportController {
    @Autowired
    private ImportService importService;

    @PostMapping("/import-deadman-list")
    public String importDeadManList(@RequestParam("file") MultipartFile file) throws InterruptedException {
        return importService.importDeadManList(file);
    }

    @PostMapping("/async-test")
    public void aSyncTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        Future result =  importService.addUserByAsync("1");
        importService.updateByAsync("2");
        Thread.sleep(600);
        System.out.println("Thread.currentThread() = " + Thread.currentThread());
        long end = System.currentTimeMillis();
        long use = end- start;
        System.out.println("耗时 = " + use + "ms");
    }

    @ApiOperation(value = "测试线程")
    @PostMapping("/testThread")
    @Async()
    public void testThread() throws InterruptedException {
        log.info("测试线程开始");
        log.info("当前线程名称 {}",Thread.currentThread().getName());
        Thread.sleep(8000);
        log.info("测试线程结束");
    }
}

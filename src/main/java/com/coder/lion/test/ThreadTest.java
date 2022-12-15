package com.coder.lion.test;

import com.coder.lion.CoderLionApplication;
import com.coder.lion.demo.service.ImportService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liuzheng
 * @date 2022年12月15日 11:21
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoderLionApplication.class)
public class ThreadTest {


    @Resource
    ImportService importService;

    @Test
    public void test1(){
        importService.testThread();
    }
}

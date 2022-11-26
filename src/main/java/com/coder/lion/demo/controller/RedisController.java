package com.coder.lion.demo.controller;

import com.coder.lion.demo.service.ImportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试redis使用相关接口")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private ImportService importService;

    @PostMapping("/saveVale")
    public String importDeadManList() {
        return importService.saveRedisVale();
    }

}


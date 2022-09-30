package com.coder.lion.demo.controller;

import com.coder.lion.demo.service.ImportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuzheng
 * @date 2022年09月30日 10:18
 * @Description TODO
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/import")
public class ImportController {
    @Autowired
    private ImportService importService;

    @PostMapping("/import-deadman-list")
    public String importDeadManList(@RequestParam("file") MultipartFile file) {
        return importService.importDeadManList(file);
    }
}

package com.coder.lion.demo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuzheng
 * @date 2022年09月30日 10:19
 * @Description TODO
 */
public interface ImportService {

    String importDeadManList(MultipartFile file);
}

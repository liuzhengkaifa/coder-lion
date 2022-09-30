package com.coder.lion.demo.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author liuzheng
 * @date 2022年09月30日 15:53
 * @Description TODO
 */

@Component
public class MultipartFileToFileUtils {

    public static File multipartFileToFile(MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename();
        String prefix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = null;
        try{
             file = File.createTempFile(originalFilename, prefix);
             multipartFile.transferTo(file);
             return file;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

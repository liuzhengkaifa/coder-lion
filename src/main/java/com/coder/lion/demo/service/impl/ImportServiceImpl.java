package com.coder.lion.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.coder.lion.demo.model.excel.DeadManExcelData;
import com.coder.lion.demo.service.ImportService;
import com.coder.lion.demo.service.listener.DeadManExcelDataListener;
import com.coder.lion.demo.utils.MultipartFileToFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuzheng
 * @date 2022年09月30日 10:19
 * @Description TODO
 */
@Service
public class ImportServiceImpl implements ImportService {

    @Override
    public String importDeadManList(MultipartFile file) {
        EasyExcel.read(MultipartFileToFileUtils.multipartFileToFile(file), DeadManExcelData.class,new DeadManExcelDataListener()).sheet().doRead();
        return "导入成功";
    }
}

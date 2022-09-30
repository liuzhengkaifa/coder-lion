package com.coder.lion.demo.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzheng
 * @date 2022年09月30日 10:20
 * @Description TODO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(15)
@HeadRowHeight(20)
public class DeadManExcelData {
    @ExcelProperty(value = "身份证",index = 0)
    private String idCard;

    @ExcelProperty(value = "用户姓名",index = 1)
    private String userName;

    @ExcelProperty(value = "性别",index = 2)
    private String sex;

    @ExcelProperty(value = "年龄",index = 3)
    private String age;

    @ExcelProperty(value = "原因",index = 4)
    private String reason;

    @ExcelProperty(value = "房子层数",index = 5)
    private String house;
}

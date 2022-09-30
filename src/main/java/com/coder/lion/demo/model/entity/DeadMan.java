package com.coder.lion.demo.model.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzheng
 * @date 2022年09月30日 10:22
 * @Description TODO
 */
@Data
@TableName(value = "deadman")
public class DeadMan implements Serializable {

    private String uid;

    private String idCard;

    private String userName;

    private String sex;

    private String age;

    private String reason;

    private String house;
}

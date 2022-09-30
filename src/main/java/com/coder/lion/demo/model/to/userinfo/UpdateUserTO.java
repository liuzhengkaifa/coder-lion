package com.coder.lion.demo.model.to.userinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzheng
 * @date 2022年09月29日 22:18
 * @Description
 */
@Data
@ApiModel("修改用户信息传入TO类")
public class UpdateUserTO {
    @ApiModelProperty(value = "用户ID")
    private String uid;

    @ApiModelProperty(value = "用户密码")
    private String password;
}

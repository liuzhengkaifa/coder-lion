package com.coder.lion.demo.controller;

import com.coder.lion.demo.model.response.BaseResponse;
import com.coder.lion.demo.model.response.RespGenerator;
import com.coder.lion.demo.model.to.userinfo.UpdateUserTO;
import com.coder.lion.demo.service.user.service.ITUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuzheng
 * @date 2022年09月29日 22:12
 * @Description
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Resource
    ITUserService itUserService;

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateUserMessage")
    public BaseResponse<Integer> updateUserMessage(@RequestBody UpdateUserTO updateUserTO) {
        return RespGenerator.returnOK("成功");
    }

    @ApiOperation(value = "获取所有用户信息")
    @PostMapping("/getAllUser")
    public void getAllUser(){
        itUserService.getAllUser();
    }
}

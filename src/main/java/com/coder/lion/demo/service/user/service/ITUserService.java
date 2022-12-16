package com.coder.lion.demo.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.lion.demo.model.entity.TUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuzheng
 * @since 2022-12-16
 */
public interface ITUserService extends IService<TUser> {

    void getAllUser();
}

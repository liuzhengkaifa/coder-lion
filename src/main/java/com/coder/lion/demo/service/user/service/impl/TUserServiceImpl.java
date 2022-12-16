package com.coder.lion.demo.service.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.lion.demo.mapper.TUserMapper;
import com.coder.lion.demo.model.entity.TUser;
import com.coder.lion.demo.service.user.service.ITUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2022-12-16
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    @Override
    public void getAllUser() {
        List<TUser> list = list();
        System.out.println("list = " + list);
    }
}

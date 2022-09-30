package com.coder.lion.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface SpiceBaseMapper<T> extends BaseMapper<T> {
}

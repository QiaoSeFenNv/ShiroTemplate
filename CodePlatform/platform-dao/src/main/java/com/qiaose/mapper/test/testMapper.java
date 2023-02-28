package com.qiaose.mapper.test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiaose.entity.testEntity.test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface testMapper extends BaseMapper<test> {
    List<test> getTest();
}

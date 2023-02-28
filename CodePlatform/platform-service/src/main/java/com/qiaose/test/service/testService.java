package com.qiaose.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiaose.entity.testEntity.test;

import java.util.List;


public interface testService extends IService<test> {
    List<test> getTest();

//    Object insert(test t1);
//
//    Object updateByKeyId(test t1);
//
//    Object deleteById(Integer id);

}

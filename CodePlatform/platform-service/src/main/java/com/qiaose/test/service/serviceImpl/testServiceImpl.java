package com.qiaose.test.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiaose.entity.testEntity.test;

import com.qiaose.test.service.testService;
import com.qiaose.mapper.test.testMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: testServiceImpl
 * @Description:
 * @Author qiaosefennv
 * @Date 2022/6/11
 * @Version 1.0
 */
@Service
public class testServiceImpl extends ServiceImpl<testMapper,test> implements testService {

    @Override
    public List<test> getTest() {

        return this.baseMapper.getTest();
    }

//    @Override
//    public Object insert(test t1) {
//        return testMapper.insert(t1);
//    }
//
//    @Override
//    public Object updateByKeyId(test t1) {
//         return this.baseMapper.updateById(t1);
//    }
//
//    @Override
//    public Object deleteById(Integer id) {
//        return  this.baseMapper.deleteById(id);
//    }

}

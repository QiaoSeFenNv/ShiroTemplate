package com.qiaose.controller.test;


import com.qiaose.entity.Result;
import com.qiaose.entity.testEntity.test;



import com.qiaose.test.service.testService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.Min;



/**
 * @ClassName: Testcontroller
 * @Description:
 * @Author qiaosefennv
 * @Date 2022/6/11
 * @Version 1.0
 */

@RestController
@RequestMapping("/test")
@Api(tags = "测试")
@Validated
public class TestController {

    @Autowired
    private testService testService;


    @GetMapping("/test")
    public Result<?> test(){
        return Result.success("hello world");
    }

    @GetMapping("/test1")
    public Result<?> getTest(){
        return Result.success(testService.getTest());
    }

//    @PostMapping("/insert")
//    @ApiOperation("插入测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> insert(@RequestBody @Valid test t1){
//        return Result.success(testService.getBaseMapper().insert(t1));
//    }
//
//    @PutMapping("/update")
//    @ApiOperation("更新测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> update(@RequestBody @Valid test t1){
//        return Result.success(testService.getBaseMapper().updateById(t1));
//    }
//
//    @DeleteMapping("/delete")
//    @ApiOperation("删除测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> delete(@RequestParam @Min(message = "小于5",value = 5) Integer id){
//        return Result.success(testService.getBaseMapper().deleteById(id));
//    }

//    @PostMapping("/insert")
//    @ApiOperation("插入测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> insert(@RequestBody @Valid test t1){
//        return Result.success(testService.save(t1));
//    }
//
//    @PutMapping("/update")
//    @ApiOperation("更新测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> update(@RequestBody @Valid test t1){
//        return Result.success(testService.updateById(t1));
//    }
//
//    @DeleteMapping("/delete")
//    @ApiOperation("删除测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> delete(@RequestParam @Min(message = "小于5",value = 5) Integer id){
//        return Result.success(testService.removeById(id));
//    }

}

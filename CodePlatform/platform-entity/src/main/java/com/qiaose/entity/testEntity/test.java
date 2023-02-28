package com.qiaose.entity.testEntity;




import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: test
 * @Description:
 * @Author qiaosefennv
 * @Date 2022/6/11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("测试基本信息")
public class test implements Serializable {

    private static final long serialVersionUID = -692161779288898093L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Integer test_id;

    @ApiModelProperty("姓名")
    @NotNull(message = "姓名不能为空")
    private String name;

    @ApiModelProperty("属性")

    private String value;

}

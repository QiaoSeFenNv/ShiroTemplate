package com.qiaose.entity.shiro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="`role`")
@Data
public class Role {
    @ApiModelProperty(value="")
    private Long id;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private String description;
}
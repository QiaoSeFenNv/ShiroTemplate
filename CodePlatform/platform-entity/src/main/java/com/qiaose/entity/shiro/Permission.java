package com.qiaose.entity.shiro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="permission")
@Data
public class Permission {
    @ApiModelProperty(value="")
    private Long id;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private String description;
}
package com.qiaose.entity.shiro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value="user_role")
@Data
@Builder
public class UserRole {
    @ApiModelProperty(value="")
    private Long userId;

    @ApiModelProperty(value="")
    private Long roleId;
}
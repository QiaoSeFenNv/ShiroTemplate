package com.qiaose.entity.shiro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="role_permission")
@Data
public class RolePermission {
    @ApiModelProperty(value="")
    private Long roleId;

    @ApiModelProperty(value="")
    private Long permissionId;
}
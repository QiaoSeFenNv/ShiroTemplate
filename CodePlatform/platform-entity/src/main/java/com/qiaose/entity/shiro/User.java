package com.qiaose.entity.shiro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value="`user`")
@Data
@Builder
public class User {
    @ApiModelProperty(value="")
    private Long id;

    @ApiModelProperty(value="")
    private String username;

    @ApiModelProperty(value="")
    private String password;

    @ApiModelProperty(value="")
    private String email;

    @ApiModelProperty(value="")
    private Integer status;
}
package com.zhx.project.model.vo.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author yupi
 */
@Data
@ApiModel(value = "用户注册请求体", description = "用户注册请求体")
public class UserRegisterRequest implements Serializable {


    private static final long serialVersionUID = 3191241716373120793L;


    @ApiModelProperty(value = "用户账号", required = true)
    private String userAccount;

    @ApiModelProperty(value = "用户密码", required = true)
    private String userPassword;

    @ApiModelProperty(value = "用户确认密码", required = true)
    private String checkPassword;
}

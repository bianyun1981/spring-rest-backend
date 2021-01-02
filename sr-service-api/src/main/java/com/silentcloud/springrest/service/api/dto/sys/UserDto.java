package com.silentcloud.springrest.service.api.dto.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.model.enums.Gender;
import com.silentcloud.springrest.service.ValidationGroups.Create;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户")
public class UserDto extends BaseDto<Long, User> {
    public static final String DEFAULT_PASSWORD = "111111";
    public static final String PREDEFINED_USER_SYSADMIN = "sysadmin";

    @Unique
    @NotBlank
    @Size(min = 2, max = 18, message = "长度必须为{min}到{max}个字符")
    @ApiModelProperty(position = 1, value = "用户名", example = "user")
    private String username;

    @JsonBackReference("password")
    @NotBlank(groups = Create.class)
    @ApiModelProperty(position = 2, value = "密码", example = "password")
    private String password;

    @Unique
    @ApiModelProperty(position = 3, value = "显示名称", example = "测试用户")
    private String displayName;

    @Unique
    @Email
    @ApiModelProperty(position = 4, value = "Email", example = "user@qq.com")
    private String email;

    @Unique
    @ApiModelProperty(position = 5, value = "手机号", example = "13812345678")
    private String mobile;

    @ApiModelProperty(position = 6, value = "性别")
    private Gender gender = Gender.UNSPECIFIED;

    @ApiModelProperty(position = 7, value = "头像图片地址")
    private String pictureUrl;

    @ApiModelProperty(position = 8, value = "是否启用")
    private boolean active = true;

}
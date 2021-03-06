package com.silentcloud.springrest.service.api.dto.sys;

import com.silentcloud.springrest.model.entity.sys.Button;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.PermType;
import com.silentcloud.springrest.service.api.dto.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * 按钮权限 DTO
 *
 * @author bianyun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("按钮权限")
public class ButtonDto extends BaseDto<Long, Button> {

    @ApiModelProperty(position = 1, value = "权限名", example = "添加用户", required = true)
    @NotBlank
    @Unique(scope = "parentMenuId")
    private String name;

    @ApiModelProperty(position = 2, value = "权限值", example = "button:sys:user:add", required = true)
    @Unique
    @NotBlank
    private String value;

    @ApiModelProperty(position = 3, value = "父亲菜单ID", example = "1")
    private Long parentMenuId;

    @ApiModelProperty(position = 4, value = "父亲菜单名", example = "用户管理")
    private String parentMenuName;

    @ApiModelProperty(position = 5, value = "父亲菜单权限值", example = "1", required = true)
    @NotBlank
    private String parentMenuValue;

    @ApiModelProperty(position = 6, value = "显示顺序")
    private Integer showOrder;

    @ApiModelProperty(position = 7, value = "关联的接口权限值集合")
    private Set<String> apiPermValues;

    @ApiModelProperty(hidden = true)
    private PermType permType = PermType.BUTTON;

}

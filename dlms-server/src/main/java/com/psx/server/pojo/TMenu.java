package com.psx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author psx
 * @since 2021-04-06
 */
@Data
@Accessors(chain = true)
@TableName("t_menu")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="T_menu对象", description="")
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否需要验证",example = "1")
    private Integer requireAuth;

    @ApiModelProperty(value = "父ID",example = "2")
    private Integer parentId;

    @ApiModelProperty(value = "状态",example = "1")
    private Integer enabled;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<TMenu> children;


}

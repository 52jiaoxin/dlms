package com.psx.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author psx
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Admin对象", description="")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    private Integer enable;


}

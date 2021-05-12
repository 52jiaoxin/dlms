package com.psx.server.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author psx
 * @date 2021/5/10 23:25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="统计对象", description="")
public class Sta {
    private Integer num;
    private String name;


}

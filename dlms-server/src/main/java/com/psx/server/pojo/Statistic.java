package com.psx.server.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author psx
 * @date 2021/5/10 23:23
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="统计对象", description="")
public class Statistic {

    //    书籍名
    private Integer total;
    //    数据list
    private List<Sta> data;

    private List<String> data1;
}

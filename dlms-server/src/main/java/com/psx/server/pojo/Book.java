package com.psx.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author psx
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Book对象", description="")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "书名")
    private String name;

    @ApiModelProperty(value = "ISBN")
    private String isbn;

    @ApiModelProperty(value = "分类号")
    private String category_id;

    @ApiModelProperty(value = "分类名称")
    private String category_name;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "出版社")
    private String pulisher;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "书本数")
    private Integer total_number;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "简介")
    private String description;


}

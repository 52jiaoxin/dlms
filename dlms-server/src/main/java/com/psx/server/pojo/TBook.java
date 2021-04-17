package com.psx.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_book")
@Accessors(chain = true)
@ApiModel(value="Book对象", description="")
public class TBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "书名")
    @Excel(name = "书名",width = 30)
    private String name;

    @ApiModelProperty(value = "ISBN")
    @Excel(name = "ISBN",width = 30)
    private String isbn;

    @ApiModelProperty(value = "分类号")
    @Excel(name = "分类号")
    private String category_id;

    @ApiModelProperty(value = "分类名称")
    @Excel(name = "分类名称",width = 20)
    private String category_name;

    @ApiModelProperty(value = "作者")
    @Excel(name = "作者")
    private String author;

    @ApiModelProperty(value = "出版社")
    @Excel(name = "出版社",width = 25)
    private String pulisher;

    @ApiModelProperty(value = "价格")
    @Excel(name = "价格")
    private Double price;

    @ApiModelProperty(value = "书本数",example = "12")
    private Integer total_number;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "简介")
    @Excel(name = "简介")
    private String description;


}

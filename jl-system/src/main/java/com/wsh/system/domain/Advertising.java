package com.wsh.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jl.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 广告对象 advertising
 * 
 * @author wsh
 * @date 2021-03-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("xyh_advertising")
public class Advertising implements Serializable {

private static final long serialVersionUID=1L;


    /** $column.columnComment */
    @TableId(value = "advertising_id")
    private Long advertisingId;

    /** 图片 */
    @Excel(name = "图片")
    @ApiModelProperty(value = "图片")
    private String picture;

    /** 标题 */
    @Excel(name = "标题")
    @ApiModelProperty(value = "标题")
    private String headline;

    /** 路径 */
    @Excel(name = "路径")
    @ApiModelProperty(value = "路径")
    private String path;

    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();
}

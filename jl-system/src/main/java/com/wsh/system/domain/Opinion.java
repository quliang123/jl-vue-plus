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
 * 意见对象 opinion
 * 
 * @author wsh
 * @date 2021-03-31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("xyh_opinion")
public class Opinion implements Serializable {

private static final long serialVersionUID=1L;


    /** ID */
    @TableId(value = "id")
    private Long id;

    /** 内容 */
    @Excel(name = "内容")
    @ApiModelProperty(value = "内容")
    private String content;

    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /** 图片 */
    @Excel(name = "图片")
    @ApiModelProperty(value = "图片")
    private String picture;

    /** 联系方式 */
    @Excel(name = "联系方式")
    @ApiModelProperty(value = "联系方式")
    private String phone;

    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();
}

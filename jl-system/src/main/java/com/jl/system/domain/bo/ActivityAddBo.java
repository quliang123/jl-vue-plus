package com.jl.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 募捐活动管理添加对象 activity
 *
 * @author jl
 * @date 2021-05-27
 */
@Data
@ApiModel("募捐活动管理添加对象")
public class ActivityAddBo {

    /** 活动id */
    @ApiModelProperty("活动id")
    private Long activityId;
    /** 活动名称 */
    @ApiModelProperty("活动名称")
    @NotBlank(message = "活动名称不能为空")
    private String activityName;
    /** 活动类别 */
    @ApiModelProperty("活动类别")
    @NotNull(message = "活动类别不能为空")
    private String activityType;
    /** 活动状态 */
    @ApiModelProperty("活动状态")
    @NotNull(message = "活动状态不能为空")
    private String activityStatus;
    /** 截止日期 */
    @ApiModelProperty("截止日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "截止日期不能为空")
    private Date dueDate;
    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 修改时间 */
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createBy;
    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateBy;
    /** 项目设置 */
    @ApiModelProperty("项目设置")
    @NotBlank(message = "项目设置不能为空")
    private String setUp;
    /** 图片 */
    @ApiModelProperty("图片")
    @NotBlank(message = "图片不能为空")
    private String logo;
    /** 活动简介 */
    @ApiModelProperty("活动简介")
    @NotBlank(message = "活动简介不能为空")
    private String description;
    /** 活动详情 */
    @ApiModelProperty("活动详情")
    @NotBlank(message = "活动详情不能为空")
    private String details;
}

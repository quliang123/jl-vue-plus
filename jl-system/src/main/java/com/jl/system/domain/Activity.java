package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.jl.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 募捐活动管理对象 activity
 * 
 * @author jl
 * @date 2021-05-27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("activity")
public class Activity implements Serializable {

private static final long serialVersionUID=1L;


    /** 活动id */
    @TableId(value = "activity_id")
    private Long activityId;

    /** 基金会id */
    private Long foundationId;

    /** 活动名称 */
    private String activityName;

    /** 活动类别 */
    private String activityType;

    /** 活动状态 */
    private String activityStatus;

    /** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 修改人 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** 项目设置 */
    private String setUp;

    /** 图片 */
    private String logo;

    /** 活动简介 */
    private String description;

    /** 活动详情 */
    private String details;

    /** 累计募捐金额 */
    @TableField(exist = false)
    private String totalAmount;

    /** 累计募捐次数 */
    @TableField(exist = false)
    private Integer totalDonateCount;

    /** 活动状态 */
    @TableField(exist = false)
    private String  activityState;

    /** 校友记录列表 */
    @TableField(exist = false)
    private List<Alumna> alumnaList;

    /** 活动记录管理对象 */
    @TableField(exist = false)
    private ActivityRecord activityRecord;
    /** 校友记录管理对象*/
    @TableField(exist = false)
    private Alumna alumna;
    /** 基金会管理对象*/
    @TableField(exist = false)
    private Foundation foundation;
}

package com.jl.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jl.common.core.domain.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 募捐活动管理分页查询对象 activity
 *
 * @author jl
 * @date 2021-05-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("募捐活动管理分页查询对象")
public class ActivityQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;

	/** 活动id */
	@ApiModelProperty("活动id")
	private Long activityId;

	/** 活动名称 */
	@ApiModelProperty("活动名称")
	private String activityName;
	/** 活动类别 */
	@ApiModelProperty("活动类别")
	private String activityType;
	/** 活动状态 */
	@ApiModelProperty("活动状态  ( 1、进行中 2、已结束)")
	private String activityStatus;
	/** 截止日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("截止日期")
	private Date dueDate;
	/** 项目设置 */
	@ApiModelProperty("项目设置")
	private String setUp;
	/** 图片 */
	@ApiModelProperty("图片")
	private String logo;
	/** 活动简介 */
	@ApiModelProperty("活动简介")
	private String description;
	/** 活动详情 */
	@ApiModelProperty("活动详情")
	private String details;



}

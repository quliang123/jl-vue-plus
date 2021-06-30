package com.jl.system.domain.vo;

import com.jl.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 募捐活动管理视图对象 mall_package
 *
 * @author jl
 * @date 2021-05-25
 */
@Data
@ApiModel("募捐活动管理视图对象")
public class ActivityVo {
	private static final long serialVersionUID = 1L;

	/** 活动id */
	@ApiModelProperty("活动id")
	private Long activityId;

	/** 活动名称 */
	@Excel(name = "活动名称")
	@ApiModelProperty("活动名称")
	private String activityName;
	/** 活动类别 */
	@Excel(name = "活动类别")
	@ApiModelProperty("活动类别")
	private String activityType;
	/** 活动状态 */
	@Excel(name = "活动状态")
	@ApiModelProperty("活动状态")
	private String activityStatus;
	/** 截止日期 */
	@Excel(name = "截止日期" , width = 30, dateFormat = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("截止日期")
	private Date dueDate;
	/** 项目设置 */
	@Excel(name = "项目设置")
	@ApiModelProperty("项目设置")
	private Integer setUp;
	/** 图片 */
	@Excel(name = "图片")
	@ApiModelProperty("图片")
	private String logo;
	/** 活动简介 */
	@Excel(name = "活动简介")
	@ApiModelProperty("活动简介")
	private String description;
	/** 活动详情 */
	@Excel(name = "活动详情")
	@ApiModelProperty("活动详情")
	private String details;

}

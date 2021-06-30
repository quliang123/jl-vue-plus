package com.jl.system.domain.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jl.common.core.domain.BaseEntity;

/**
 * 募捐记录管理分页查询对象 activity_record
 *
 * @author jl
 * @date 2021-05-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("募捐记录管理分页查询对象")
public class ActivityRecordQueryBo extends BaseEntity {

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

	/** 用户id */
	@ApiModelProperty("用户id")
	private Long userId;
	/** 捐赠编号 */
	@ApiModelProperty("捐赠编号")
	private String donateNumber;
	/** 活动id */
	@ApiModelProperty("活动id")
	private Long activityId;
	/** 校友人员id */
	@ApiModelProperty("校友人员id")
	private Long alumnaId;
	/** 捐赠金额 */
	@ApiModelProperty("捐赠金额")
	private String amount;
	/** 捐赠时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("捐赠时间")
	private Date donateTime;
	@ApiModelProperty("结束时间")
	@TableField(exist = false)
	private Date dueDate;
    /** 捐募项目名称 */
	@ApiModelProperty("捐募项目名称")
	@TableField(exist = false)
	private String activityName;
    /** 捐赠方 */
	@ApiModelProperty("捐赠方")
	@TableField(exist = false)
	private String name;

	/** 选择类型 */
	@ApiModelProperty("选择类型")
	@TableField(exist = false)
	private String selectType;
}

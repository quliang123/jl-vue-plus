package com.jl.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jl.common.core.domain.BaseEntity;

/**
 * 募捐分页查询对象 t_fundraising
 *
 * @author jl
 * @date 2021-05-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("募捐分页查询对象")
public class TFundraisingQueryBo extends BaseEntity {

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


	/** 活动名称 */
	@ApiModelProperty("活动名称")
	private String fdrName;
	/** 活动类别 */
	@ApiModelProperty("活动类别")
	private String fdrType;
	/** 活动状态 */
	@ApiModelProperty("活动状态")
	private String fdrStatus;
	/** 活动报名截止时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("活动报名截止时间")
	private Date fdrFinishTime;
	/** 创建后自动上架 */
	@ApiModelProperty("创建后自动上架")
	private Integer fdrAutoPush;
	/** 截止后自动下架 */
	@ApiModelProperty("截止后自动下架")
	private Integer fdrAutoFailure;
	/** 宣传图 */
	@ApiModelProperty("宣传图")
	private String fdrPics;
	/** 活动简介 */
	@ApiModelProperty("活动简介")
	private String fdrInfo;
	/** 活动详情 */
	@ApiModelProperty("活动详情")
	private String fdrContent;

}

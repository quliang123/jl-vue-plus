package com.jl.system.domain.vo;

import com.jl.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 募捐视图对象 mall_package
 *
 * @author jl
 * @date 2021-05-21
 */
@Data
@ApiModel("募捐视图对象")
public class TFundraisingVo {
	private static final long serialVersionUID = 1L;

	/** 活动主键id */
	@ApiModelProperty("活动主键id")
	private Long fdrId;

	/** 活动名称 */
	@Excel(name = "活动名称")
	@ApiModelProperty("活动名称")
	private String fdrName;
	/** 活动类别 */
	@Excel(name = "活动类别")
	@ApiModelProperty("活动类别")
	private String fdrType;
	/** 活动状态 */
	@Excel(name = "活动状态")
	@ApiModelProperty("活动状态")
	private String fdrStatus;
	/** 活动报名截止时间 */
	@Excel(name = "活动报名截止时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("活动报名截止时间")
	private Date fdrFinishTime;
	/** 创建后自动上架 */
	@Excel(name = "创建后自动上架")
	@ApiModelProperty("创建后自动上架")
	private Integer fdrAutoPush;
	/** 截止后自动下架 */
	@Excel(name = "截止后自动下架")
	@ApiModelProperty("截止后自动下架")
	private Integer fdrAutoFailure;
	/** 宣传图 */
	@Excel(name = "宣传图")
	@ApiModelProperty("宣传图")
	private String fdrPics;
	/** 活动简介 */
	@Excel(name = "活动简介")
	@ApiModelProperty("活动简介")
	private String fdrInfo;
	/** 活动详情 */
	@Excel(name = "活动详情")
	@ApiModelProperty("活动详情")
	private String fdrContent;

}

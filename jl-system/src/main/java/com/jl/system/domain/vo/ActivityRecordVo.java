package com.jl.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jl.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jl.common.annotation.Excels;
import com.jl.common.utils.DateUtils;
import com.jl.system.domain.Activity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 募捐记录管理视图对象 mall_package
 *
 * @author jl
 * @date 2021-05-25
 */
@Data
@ApiModel("募捐记录管理视图对象")
public class ActivityRecordVo {
	private static final long serialVersionUID = 1L;

	/** 记录id */
	@ApiModelProperty("记录id")
	private Long arId;

	/** 捐赠编号 */
	@Excel(name = "捐赠编号",sort = 1)
	@ApiModelProperty("捐赠编号")
	private String donateNumber;
	/** 活动id */
	//@Excel(name = "活动id")
	@ApiModelProperty("活动id")
	private Long activityId;
	/** 校友人员id */
	//@Excel(name = "校友人员id")
	@ApiModelProperty("校友人员id")
	private Long alumnaId;
	/** 捐赠金额 */
	@Excel(name = "捐赠金额",sort = 9)
	@ApiModelProperty("捐赠金额")
	private String amount;
	/** 捐赠时间 */
	@Excel(name = "捐赠时间" , width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss",sort = 10)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("捐赠时间")
	private Date donateTime;
	/** 募捐统计时间 */
	@ApiModelProperty("募捐统计时间")
	@TableField(exist = false)
	private String donationTime;
	/** 募捐统计金额 */
	@ApiModelProperty("募捐统计金额")
	@TableField(exist = false)
	private BigDecimal sumAmount;
    @TableField(exist = false)
    @Excel(name = "身份",sort = 3)
    private String activityIdentityType;
    @TableField(exist = false)
    @Excel(name = "活动类型",sort = 4)
    private String activityTypeName;

	@TableField(exist = false)
	/** 部门对象 */
	@Excels({
			//@Excel(name = "身份", targetAttr = "identity", type = Excel.Type.EXPORT,dictType = "sys_alumni_identity",sort = 3),
			@Excel(name = "捐赠护照编号", targetAttr = "idfNo", type = Excel.Type.EXPORT,sort = 5),
			@Excel(name = "捐赠方", targetAttr = "name", type = Excel.Type.EXPORT,sort = 6),
			@Excel(name = "联系方式", targetAttr = "mobile", type = Excel.Type.EXPORT,sort =7),
			@Excel(name = "身份证", targetAttr = "idCard", type = Excel.Type.EXPORT,sort = 8)
	})
	private AlumnaVo alumnaVo;
	@TableField(exist = false)
	/** 部门对象 */
	@Excels({
			@Excel(name = "募捐项目", targetAttr = "activityName", type = Excel.Type.EXPORT,sort = 2),
			//@Excel(name = "活动类型", targetAttr = "activityType", type = Excel.Type.EXPORT,dictType = "sys_activity_type",sort = 4),
	})
	private ActivityVo activityVo;
}

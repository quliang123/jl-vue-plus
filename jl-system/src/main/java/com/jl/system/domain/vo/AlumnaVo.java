package com.jl.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jl.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;


/**
 * 校友管理视图对象 mall_package
 *
 * @author jl
 * @date 2021-05-25
 */
@Data
@ApiModel("校友管理视图对象")
public class AlumnaVo {
	private static final long serialVersionUID = 1L;

	/** 校友id */
	@ApiModelProperty("校友id")
	private Long alumnaId;

	/** 护照编号 */
	@Excel(name = "护照编号")
	@ApiModelProperty("护照编号")
	private String idfNo;
	/** 校友身份 */
	@Excel(name = "校友身份")
	@ApiModelProperty("校友身份")
	private Integer identity;
	/** 校友名称 */
	@Excel(name = "校友名称")
	@ApiModelProperty("校友名称")
	private String name;
	/** 校友性别 */
	@Excel(name = "校友性别")
	@ApiModelProperty("校友性别")
	private Integer gender;
	/** 身份证号 */
	@Excel(name = "身份证号")
	@ApiModelProperty("身份证号")
	private String idCard;
	/** 生日 */
	@Excel(name = "生日" , width = 30, dateFormat = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("生日")
	private Date birthday;
	/** 毕业年份 */
	@Excel(name = "毕业年份" , width = 30, dateFormat = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("毕业年份")
	private Date graduateTime;
	/** 联系电话 */
	@Excel(name = "联系电话")
	@ApiModelProperty("联系电话")
	private String mobile;
	/** 微信昵称 */
	@Excel(name = "微信昵称")
	@ApiModelProperty("微信昵称")
	private String nickName;
	/** 联系地址 */
	@Excel(name = "联系地址")
	@ApiModelProperty("联系地址")
	private String address;
	/** 电子邮箱 */
	@Excel(name = "电子邮箱")
	@ApiModelProperty("电子邮箱")
	private String email;
	/** qq号 */
	@Excel(name = "qq号")
	@ApiModelProperty("qq号")
	private String qq;
	/** 所在行业 */
	@Excel(name = "所在行业")
	@ApiModelProperty("所在行业")
	private String industry;
	/** 是否工作 */
	@Excel(name = "是否工作")
	@ApiModelProperty("是否工作")
	private Integer itwork;
	/** 所在部门 */
	@Excel(name = "所在部门")
	@ApiModelProperty("所在部门")
	private String dept;
	/** 所属公司 */
	@Excel(name = "所属公司")
	@ApiModelProperty("所属公司")
	private String company;
	/** 就读大学 */
	@Excel(name = "就读大学")
	@ApiModelProperty("就读大学")
	private String university;
	/** 所处职务 */
	@Excel(name = "所处职务")
	@ApiModelProperty("所处职务")
	private String position;
	/** 所读专业 */
	@Excel(name = "所读专业")
	@ApiModelProperty("所读专业")
	private String profession;
	/** 学历 */
	@Excel(name = "学历")
	@ApiModelProperty("学历")
	private String education;

	@Excel(name = "捐款次数")
	@ApiModelProperty("捐款次数")
	@TableField(exist = false)
	private Integer doNate;

	@Excel(name = "捐款次数")
	@ApiModelProperty("捐款次数")
	@TableField(exist = false)
	private Integer doNateAmount;
	/** 创建时间 */
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**  总捐赠金额 */
	@ApiModelProperty("捐款金额")
	private String totalAmount;
	/** 总捐赠次数   */
	@ApiModelProperty("捐款次数")
	private Integer totalDonate;
}

package com.jl.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jl.common.core.domain.BaseEntity;

/**
 * 校友管理分页查询对象 alumna
 *
 * @author jl
 * @date 2021-05-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("校友管理分页查询对象")
public class AlumnaQueryBo extends BaseEntity {

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

	/** 基金id */
	@ApiModelProperty("基金id")
	private Integer foundationId;

	/** 护照编号 */
	@ApiModelProperty("护照编号")
	private String idfNo;
	/** 校友身份 */
	@ApiModelProperty("校友身份")
	private Integer identity;
	/** 校友名称 */
	@ApiModelProperty("校友名称")
	private String name;
	/** 校友性别 */
	@ApiModelProperty("校友性别")
	private Integer gender;
	/** 身份证号 */
	@ApiModelProperty("身份证号")
	private String idCard;
	/** 生日 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("生日")
	private Date birthday;
	/** 毕业年份 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("毕业年份")
	private Date graduateTime;
	/** 联系电话 */
	@ApiModelProperty("联系电话")
	private String mobile;
	/** 微信昵称 */
	@ApiModelProperty("微信昵称")
	private String nickName;
	/** 联系地址 */
	@ApiModelProperty("联系地址")
	private String address;
	/** 电子邮箱 */
	@ApiModelProperty("电子邮箱")
	private String email;
	/** qq号 */
	@ApiModelProperty("qq号")
	private String qq;
	/** 所在行业 */
	@ApiModelProperty("所在行业")
	private String industry;
	/** 是否工作 */
	@ApiModelProperty("是否工作")
	private Integer itwork;
	/** 所在部门 */
	@ApiModelProperty("所在部门")
	private String dept;
	/** 所属公司 */
	@ApiModelProperty("所属公司")
	private String company;
	/** 就读大学 */
	@ApiModelProperty("就读大学")
	private String university;
	/** 所处职务 */
	@ApiModelProperty("所处职务")
	private String position;
	/** 所读专业 */
	@ApiModelProperty("所读专业")
	private String profession;
	/** 学历 */
	@ApiModelProperty("学历")
	private String education;

}

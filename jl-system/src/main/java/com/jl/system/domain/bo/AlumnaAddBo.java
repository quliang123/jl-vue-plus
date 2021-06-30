package com.jl.system.domain.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 校友管理添加对象 alumna
 *
 * @author jl
 * @date 2021-05-25
 */
@Data
@ApiModel("校友管理添加对象")
public class AlumnaAddBo {
    /** 活动id */
    @ApiModelProperty("活动id")
    private Long activityId;
    /** 校友id */
    @TableId(value = "alumna_id")
    private Long alumnaId;
    /** 护照编号 */
    @ApiModelProperty("护照编号")
    @NotBlank(message = "护照编号不能为空")
    private String idfNo;
    /** 校友身份 */
    @ApiModelProperty("校友身份")
    @NotNull(message = "校友身份不能为空")
    private Integer identity;
    /** 校友名称 */
    @ApiModelProperty("校友名称")
    @NotBlank(message = "校友名称不能为空")
    private String name;
    /** 校友性别 */
    @ApiModelProperty("校友性别")
    @NotNull(message = "校友性别不能为空")
    private Integer gender;
    /** 身份证号 */
    @ApiModelProperty("身份证号")
    @NotBlank(message = "身份证号不能为空")
    private String idCard;
    /** 生日 */
    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "生日不能为空")
    private Date birthday;
    /** 毕业年份 */
    @ApiModelProperty("毕业年份")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "毕业年份不能为空")
    private Date graduateTime;
    /** 联系电话 */
    @ApiModelProperty("联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String mobile;
    /** 微信昵称 */
    @ApiModelProperty("微信昵称")
    @NotBlank(message = "微信昵称不能为空")
    private String nickName;
    /** 联系地址 */
    @ApiModelProperty("联系地址")
    @NotBlank(message = "联系地址不能为空")
    private String address;
    /** 电子邮箱 */
    @ApiModelProperty("电子邮箱")
    @NotBlank(message = "电子邮箱不能为空")
    private String email;
    /** qq号 */
    @ApiModelProperty("qq号")
    @NotBlank(message = "qq号不能为空")
    private String qq;
    /** 所在行业 */
    @ApiModelProperty("所在行业")
    @NotBlank(message = "所在行业不能为空")
    private String industry;
    /** 是否工作 */
    @ApiModelProperty("是否工作")
    @NotNull(message = "是否工作不能为空")
    private Integer itwork;
    /** 所在部门 */
    @ApiModelProperty("所在部门")
    @NotBlank(message = "所在部门不能为空")
    private String dept;
    /** 所属公司 */
    @ApiModelProperty("所属公司")
    @NotBlank(message = "所属公司不能为空")
    private String company;
    /** 就读大学 */
    @ApiModelProperty("就读大学")
    @NotBlank(message = "就读大学不能为空")
    private String university;
    /** 所处职务 */
    @ApiModelProperty("所处职务")
    @NotBlank(message = "所处职务不能为空")
    private String position;
    /** 所读专业 */
    @ApiModelProperty("所读专业")
    @NotBlank(message = "所读专业不能为空")
    private String profession;
    /** 学历 */
    @ApiModelProperty("学历")
    @NotBlank(message = "学历不能为空")
    private String education;
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
    /**  总捐赠金额 */
    @ApiModelProperty("总捐赠金额")
    private String totalAmount;
    /** 总捐赠次数   */
    @ApiModelProperty("总捐赠次数")
    private Integer totalDonate;
}

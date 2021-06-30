package com.jl.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 募捐添加对象 t_fundraising
 *
 * @author jl
 * @date 2021-05-21
 */
@Data
@ApiModel("募捐添加对象")
public class TFundraisingAddBo {

    /** 活动名称 */
    @ApiModelProperty("活动名称")
    @NotBlank(message = "活动名称不能为空")
    private String fdrName;
    /** 活动类别 */
    @ApiModelProperty("活动类别")
    @NotBlank(message = "活动类别不能为空")
    private String fdrType;
    /** 活动状态 */
    @ApiModelProperty("活动状态")
    @NotBlank(message = "活动状态不能为空")
    private String fdrStatus;
    /** 活动报名截止时间 */
    @ApiModelProperty("活动报名截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "活动报名截止时间不能为空")
    private Date fdrFinishTime;
    /** 创建后自动上架 */
    @ApiModelProperty("创建后自动上架")
    @NotNull(message = "创建后自动上架不能为空")
    private Integer fdrAutoPush;
    /** 截止后自动下架 */
    @ApiModelProperty("截止后自动下架")
    @NotNull(message = "截止后自动下架不能为空")
    private Integer fdrAutoFailure;
    /** 宣传图 */
    @ApiModelProperty("宣传图")
    @NotBlank(message = "宣传图不能为空")
    private String fdrPics;
    /** 活动简介 */
    @ApiModelProperty("活动简介")
    @NotBlank(message = "活动简介不能为空")
    private String fdrInfo;
    /** 活动详情 */
    @ApiModelProperty("活动详情")
    @NotBlank(message = "活动详情不能为空")
    private String fdrContent;
    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    @NotBlank(message = "删除标志（0代表存在 2代表删除）不能为空")
    private String delFlag;
    /** 创建者 */
    @ApiModelProperty("创建者")
    @NotBlank(message = "创建者不能为空")
    private String createBy;
    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "创建时间不能为空")
    private Date createTime;
    /** 更新者 */
    @ApiModelProperty("更新者")
    @NotBlank(message = "更新者不能为空")
    private String updateBy;
    /** 更新时间 */
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "更新时间不能为空")
    private Date updateTime;
}

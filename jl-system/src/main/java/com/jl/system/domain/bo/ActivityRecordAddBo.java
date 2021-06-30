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
 * 募捐记录管理添加对象 activity_record
 *
 * @author jl
 * @date 2021-05-25
 */
@Data
@ApiModel("募捐记录管理添加对象")
public class ActivityRecordAddBo {

    /** 捐赠编号 */
    @ApiModelProperty("捐赠id")
    private String arId;
    /** 捐赠编号 */
    @ApiModelProperty("捐赠编号")
    @NotBlank(message = "捐赠编号不能为空")
    private String donateNumber;
    /** 活动id */
    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    private Long activityId;
    /** 校友人员id */
    @ApiModelProperty("校友人员id")
    @NotNull(message = "校友人员id不能为空")
    private Long alumnaId;
    /** 捐赠金额 */
    @ApiModelProperty("捐赠金额")
    @NotBlank(message = "捐赠金额不能为空")
    private String amount;
    /** 捐赠时间 */
    @ApiModelProperty("捐赠时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "捐赠时间不能为空")
    private Date donateTime;
    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "创建时间不能为空")
    private Date createTime;
    /** 修改时间 */
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "修改时间不能为空")
    private Date updateTime;
    /** 创建人 */
    @ApiModelProperty("创建人")
    @NotBlank(message = "创建人不能为空")
    private String createBy;
    /** 修改人 */
    @ApiModelProperty("修改人")
    @NotBlank(message = "修改人不能为空")
    private String updateBy;
    /** 基金会 */
    @ApiModelProperty("基金会id")
    @NotBlank(message = "基金会id不能为空")
    private String foundationId;
    /** 用户id */
    @ApiModelProperty("用户id")
    private Long userId;
}

package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jl.common.annotation.Excels;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.jl.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 募捐记录管理对象 activity_record
 * 
 * @author jl
 * @date 2021-05-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("activity_record")
public class ActivityRecord implements Serializable {

private static final long serialVersionUID=1L;


    /** 记录id */
    @TableId(value = "ar_id")
    private Long arId;

    /** 捐赠编号 */
    private String donateNumber;

    /** 活动id */
    private Long activityId;

    /** 校友人员id */
    private Long alumnaId;

    /** 用户id */
    private Long userId;

    /** 基金会id */
    private Long foundationId;
    /** 捐赠金额 */
    private String amount;

    /** 捐赠时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date donateTime;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 修改人 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /** 身份 */
    @TableField(exist = false)
    @Excel(name = "身份",sort = 3)
    private String activityIdentityType;

    /** 活动类型 */
    @TableField(exist = false)
    @Excel(name = "活动类型",sort = 4)
    private String activityTypeName;

    @TableField(exist = false)
    /** 部门对象 */
    @Excels({
            @Excel(name = "募捐项目", targetAttr = "activityName", type = Excel.Type.EXPORT),
            @Excel(name = "活动类型", targetAttr = "activityType", type = Excel.Type.EXPORT,dictType = "sys_activity_type")
    })
    private Activity activity;

    @TableField(exist = false)
    /** 部门对象 */
    @Excels({
            @Excel(name = "身份", targetAttr = "identity", type = Excel.Type.EXPORT,dictType = "sys_alumni_identity"),
            @Excel(name = "捐赠护照编号", targetAttr = "idfNo", type = Excel.Type.EXPORT),
            @Excel(name = "联系方式", targetAttr = "mobile", type = Excel.Type.EXPORT),
            @Excel(name = "身份证号", targetAttr = "idCard", type = Excel.Type.EXPORT)
    })
    private Alumna alumna;

    /** 基金会管理对象*/
    @TableField(exist = false)
    private Foundation foundation;
}

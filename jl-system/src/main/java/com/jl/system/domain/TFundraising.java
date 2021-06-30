package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.jl.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 募捐对象 t_fundraising
 * 
 * @author jl
 * @date 2021-05-21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_fundraising")
public class TFundraising implements Serializable {

private static final long serialVersionUID=1L;


    /** 活动主键id */
    @TableId(value = "fdr_id")
    private Long fdrId;

    /** 活动名称 */
    private String fdrName;

    /** 活动类别 */
    private String fdrType;

    /** 活动状态 */
    private String fdrStatus;

    /** 活动报名截止时间 */
    private Date fdrFinishTime;

    /** 创建后自动上架 */
    private Integer fdrAutoPush;

    /** 截止后自动下架 */
    private Integer fdrAutoFailure;

    /** 宣传图 */
    private String fdrPics;

    /** 活动简介 */
    private String fdrInfo;

    /** 活动详情 */
    private String fdrContent;

    /** 删除标志（0代表存在 2代表删除） */
    @TableLogic
    private String delFlag;

    /** 创建者 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新者 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

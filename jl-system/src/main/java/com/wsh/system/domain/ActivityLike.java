package com.wsh.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jl.common.annotation.Excel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 活动点赞记录对象 activity_like
 * 
 * @author wsh
 * @date 2021-04-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("xyh_activity_like")
public class ActivityLike implements Serializable {

private static final long serialVersionUID=1L;


    /** $column.columnComment */
    @TableId(value = "pl_id")
    private Long plId;

    /** 活动id */
    @Excel(name = "活动id")
    private Long activityId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();
}

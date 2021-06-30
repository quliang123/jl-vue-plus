package com.wsh.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jl.common.annotation.Excel;
import com.jl.common.core.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 评论对象 discuss
 * 
 * @author wsh
 * @date 2021-04-13
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("xyh_discuss")
public class Discuss implements Serializable {

private static final long serialVersionUID=1L;


    /** $column.columnComment */
    @TableId(value = "discuss_id")
    private Long discussId;

    /** 活动id */
    @Excel(name = "活动id")
    @NotNull(message = "请传入活动id")
    private Long activityId;

    /** 内容 */
    @NotBlank(message = "请传入评论内容")
    @Excel(name = "内容")
    private String content;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    @TableField(exist = false)
    private User user;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();
}

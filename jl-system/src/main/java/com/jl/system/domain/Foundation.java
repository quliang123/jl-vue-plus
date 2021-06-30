package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基金会管理表
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("Foundation")
public class Foundation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "foundation_id", type = IdType.AUTO)
    private Integer foundationId;
    /** 基金会编码 */
    private String foundationCode;

    /**
     * 基金会名称
     */
    private String foundationName;

    /**
     * 基金会介绍内容
     */
    private String foundationContent;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}

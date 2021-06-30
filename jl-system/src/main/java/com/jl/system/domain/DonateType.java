package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *  捐款款项类型
 * </p>
 *
 * @author liang qu
 * @since 2021-06-21
 */
@Data
@ApiModel("捐款款项类型对象")
public class DonateType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 捐赠主键id
     */
    @TableId(value = "dt_id", type = IdType.AUTO)
    private Long dtId;

    /**
     * 捐款类型名称
     */
    private String title;

    /**
     * 金额
     */
    private Long amount;

    /**
     * 备注
     */
    private String remark;


}

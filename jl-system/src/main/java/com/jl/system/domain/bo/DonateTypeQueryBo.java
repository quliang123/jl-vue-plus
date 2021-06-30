package com.jl.system.domain.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @program: jl-vue-plus
 * @description: 查询对象
 * @author: quliang
 * @create: 2021-06-21 16:48
 **/

public class DonateTypeQueryBo {

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

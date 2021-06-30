package com.jl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.DonateType;
import com.jl.system.domain.bo.DonateTypeQueryBo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang qu
 * @since 2021-06-21
 */
public interface IDonateTypeService extends IService<DonateType> {
    /**
     *查询全部捐赠钱款类别
     * @return
     */
    AjaxResult selectDonateType();
}

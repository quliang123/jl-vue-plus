package com.jl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.Foundation;
import com.jl.system.domain.bo.AlumnaQueryBo;
import com.jl.system.domain.bo.FoundationQueryBo;

/**
 * <p>
 * 基金会管理表 服务类
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
public interface IFoundationService extends IService<Foundation> {
    /**
     * 获取当前用户的基金会
     * @param fId
     * @return
     */
    AjaxResult getCurrentAlumnaFoundation(Long fId);
}

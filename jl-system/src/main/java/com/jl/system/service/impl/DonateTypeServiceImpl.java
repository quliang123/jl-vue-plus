package com.jl.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.DonateType;
import com.jl.system.domain.bo.DonateTypeQueryBo;
import com.jl.system.mapper.DonateTypeMapper;
import com.jl.system.service.IDonateTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang qu
 * @since 2021-06-21
 */
@Service
public class DonateTypeServiceImpl extends ServiceImpl<DonateTypeMapper, DonateType> implements IDonateTypeService {
    /**
     * 查询全部捐赠钱款类别
     * @return
     */
    @Override
    public AjaxResult selectDonateType() {
        return AjaxResult.success(this.list());
    }

}

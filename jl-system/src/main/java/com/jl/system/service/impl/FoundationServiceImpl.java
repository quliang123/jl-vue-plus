package com.jl.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.Alumna;
import com.jl.system.domain.Foundation;
import com.jl.system.domain.bo.AlumnaQueryBo;
import com.jl.system.domain.bo.FoundationQueryBo;
import com.jl.system.mapper.FoundationMapper;
import com.jl.system.service.IFoundationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 基金会管理表 服务实现类
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
@Service
public class FoundationServiceImpl extends ServiceImpl<FoundationMapper, Foundation> implements IFoundationService {
    /**
     * 获取当前用户的基金会
     * @param fId
     * @return
     */
    @Override
    public AjaxResult getCurrentAlumnaFoundation(Long fId) {
        Foundation foundation = baseMapper.selectOne(new QueryWrapper<Foundation>().eq("foundation_id", fId));
        return AjaxResult.success(foundation);
    }
}

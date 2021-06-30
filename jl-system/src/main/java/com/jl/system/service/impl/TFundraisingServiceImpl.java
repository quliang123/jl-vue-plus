package com.jl.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.common.utils.PageUtils;
import com.jl.common.core.page.PagePlus;
import com.jl.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jl.system.domain.bo.TFundraisingAddBo;
import com.jl.system.domain.bo.TFundraisingQueryBo;
import com.jl.system.domain.bo.TFundraisingEditBo;
import com.jl.system.domain.TFundraising;
import com.jl.system.mapper.TFundraisingMapper;
import com.jl.system.domain.vo.TFundraisingVo;
import com.jl.system.service.ITFundraisingService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 募捐Service业务层处理
 *
 * @author jl
 * @date 2021-05-21
 */
@Service
public class TFundraisingServiceImpl extends ServiceImpl<TFundraisingMapper, TFundraising> implements ITFundraisingService {

    @Override
    public TFundraisingVo queryById(Long fdrId){
        return getVoById(fdrId, TFundraisingVo.class);
    }

    @Override
    public TableDataInfo<TFundraisingVo> queryPageList(TFundraisingQueryBo bo) {
        PagePlus<TFundraising, TFundraisingVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TFundraisingVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TFundraisingVo> queryList(TFundraisingQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFundraisingVo.class);
    }

    private LambdaQueryWrapper<TFundraising> buildQueryWrapper(TFundraisingQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TFundraising> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getFdrName()), TFundraising::getFdrName, bo.getFdrName());
        lqw.eq(StrUtil.isNotBlank(bo.getFdrType()), TFundraising::getFdrType, bo.getFdrType());
        lqw.eq(StrUtil.isNotBlank(bo.getFdrStatus()), TFundraising::getFdrStatus, bo.getFdrStatus());
        lqw.eq(bo.getFdrFinishTime() != null, TFundraising::getFdrFinishTime, bo.getFdrFinishTime());
        lqw.eq(bo.getFdrAutoPush() != null, TFundraising::getFdrAutoPush, bo.getFdrAutoPush());
        lqw.eq(bo.getFdrAutoFailure() != null, TFundraising::getFdrAutoFailure, bo.getFdrAutoFailure());
        lqw.eq(StrUtil.isNotBlank(bo.getFdrPics()), TFundraising::getFdrPics, bo.getFdrPics());
        lqw.eq(StrUtil.isNotBlank(bo.getFdrInfo()), TFundraising::getFdrInfo, bo.getFdrInfo());
        lqw.eq(StrUtil.isNotBlank(bo.getFdrContent()), TFundraising::getFdrContent, bo.getFdrContent());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFundraisingAddBo bo) {
        TFundraising add = BeanUtil.toBean(bo, TFundraising.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFundraisingEditBo bo) {
        TFundraising update = BeanUtil.toBean(bo, TFundraising.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFundraising entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}

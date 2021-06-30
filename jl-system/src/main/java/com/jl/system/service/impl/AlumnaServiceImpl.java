package com.jl.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.utils.PageUtils;
import com.jl.common.core.page.PagePlus;
import com.jl.common.core.page.TableDataInfo;
import com.jl.system.domain.bo.AlumnaAddBo;
import com.jl.system.domain.bo.AlumnaEditBo;
import com.jl.system.domain.bo.AlumnaQueryBo;
import com.jl.system.domain.vo.AlumnaVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jl.system.domain.Alumna;
import com.jl.system.mapper.AlumnaMapper;
import com.jl.system.service.IAlumnaService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 校友管理Service业务层处理
 *
 * @author jl
 * @date 2021-05-25
 */
@Service
public class AlumnaServiceImpl extends ServiceImpl<AlumnaMapper, Alumna> implements IAlumnaService {

    @Override
    public AlumnaVo queryById(Long alumnaId){
        return getVoById(alumnaId, AlumnaVo.class);
    }

    @Override
    public TableDataInfo<AlumnaVo> queryPageList(AlumnaQueryBo bo) {
        PagePlus<Alumna, AlumnaVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), AlumnaVo.class);

        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<AlumnaVo> queryList(AlumnaQueryBo bo) {
        return listVo(buildQueryWrapper(bo), AlumnaVo.class);
    }

    private LambdaQueryWrapper<Alumna> buildQueryWrapper(AlumnaQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Alumna> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getIdfNo()), Alumna::getIdfNo, bo.getIdfNo());
        lqw.eq(bo.getIdentity() != null, Alumna::getIdentity, bo.getIdentity());
        lqw.like(StrUtil.isNotBlank(bo.getName()), Alumna::getName, bo.getName());
        lqw.eq(bo.getGender() != null, Alumna::getGender, bo.getGender());
        lqw.eq(StrUtil.isNotBlank(bo.getIdCard()), Alumna::getIdCard, bo.getIdCard());
        lqw.eq(bo.getBirthday() != null, Alumna::getBirthday, bo.getBirthday());
        lqw.eq(bo.getGraduateTime() != null, Alumna::getGraduateTime, bo.getGraduateTime());
        lqw.eq(StrUtil.isNotBlank(bo.getMobile()), Alumna::getMobile, bo.getMobile());
        lqw.like(StrUtil.isNotBlank(bo.getNickName()), Alumna::getNickName, bo.getNickName());
        lqw.eq(StrUtil.isNotBlank(bo.getAddress()), Alumna::getAddress, bo.getAddress());
        lqw.eq(StrUtil.isNotBlank(bo.getEmail()), Alumna::getEmail, bo.getEmail());
        lqw.eq(StrUtil.isNotBlank(bo.getQq()), Alumna::getQq, bo.getQq());
        lqw.eq(StrUtil.isNotBlank(bo.getIndustry()), Alumna::getIndustry, bo.getIndustry());
        lqw.eq(bo.getItwork() != null, Alumna::getItwork, bo.getItwork());
        lqw.eq(StrUtil.isNotBlank(bo.getDept()), Alumna::getDept, bo.getDept());
        lqw.eq(StrUtil.isNotBlank(bo.getCompany()), Alumna::getCompany, bo.getCompany());
        lqw.eq(StrUtil.isNotBlank(bo.getUniversity()), Alumna::getUniversity, bo.getUniversity());
        lqw.eq(StrUtil.isNotBlank(bo.getPosition()), Alumna::getPosition, bo.getPosition());
        lqw.eq(StrUtil.isNotBlank(bo.getProfession()), Alumna::getProfession, bo.getProfession());
        lqw.eq(StrUtil.isNotBlank(bo.getEducation()), Alumna::getEducation, bo.getEducation());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(AlumnaAddBo bo) {
        Alumna add = BeanUtil.toBean(bo, Alumna.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(AlumnaEditBo bo) {
        Alumna update = BeanUtil.toBean(bo, Alumna.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Alumna entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }

    /**
     * 更新校友
     * @param alumna
     * @return
     */
    @Override
    public Boolean updateAlumna(Alumna alumna) {
        int update = baseMapper.updateById(alumna);
        if (update > 0) {
            return true;
        }
        return false;
    }
}

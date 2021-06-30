package com.jl.system.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.Activity;
import com.jl.system.domain.Certificate;
import com.jl.system.domain.bo.ActivityQueryBo;
import com.jl.system.domain.bo.CertificateQueryBo;
import com.jl.system.mapper.CertificateMapper;
import com.jl.system.service.ICertificateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 证书管理表 服务实现类
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
@Service
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {

    private static final Logger log = LoggerFactory.getLogger(CertificateServiceImpl.class);


    /**
     * 获取当前用户的证书数量
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getCurrentUserCertificate(CertificateQueryBo bo) {
         return AjaxResult.success(baseMapper.selectCount(buildQueryWrapper(bo)));
    }

    private LambdaQueryWrapper<Certificate> buildQueryWrapper(CertificateQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        QueryWrapper<Certificate> qw = new QueryWrapper<>();
        qw.eq("alumna_id", bo.getAlumnaId());
        qw.eq("user_id", bo.getUserId());
        LambdaQueryWrapper<Certificate> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 获取当前用户的证书列表
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getCertificateList(CertificateQueryBo bo) {
        return AjaxResult.success(baseMapper.getCertificateList(bo));
    }

    /**
     * 获取当前证书的详情
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getCertificateDetails(CertificateQueryBo bo) {
        return AjaxResult.success(baseMapper.getCertificateDetails(bo));
    }
}

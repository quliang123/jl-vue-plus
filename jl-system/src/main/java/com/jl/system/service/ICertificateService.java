package com.jl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.Certificate;
import com.jl.system.domain.bo.CertificateQueryBo;

/**
 * <p>
 * 证书管理表 服务类
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
public interface ICertificateService extends IService<Certificate> {
    /**
     * 获取当前用户的证书数量
     * @param bo
     * @return
     */
    AjaxResult getCurrentUserCertificate(CertificateQueryBo bo);

    /**
     * 获取当前用户证书列表
     * @param bo
     * @return
     */
    AjaxResult getCertificateList(CertificateQueryBo bo);
    /**
     * 获取当前证书的详情
     * @param bo
     * @return
     */
    AjaxResult getCertificateDetails(CertificateQueryBo bo);
}

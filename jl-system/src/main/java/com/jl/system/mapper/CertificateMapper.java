package com.jl.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.Certificate;
import com.jl.system.domain.bo.CertificateQueryBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 证书管理表 Mapper 接口
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
public interface CertificateMapper extends BaseMapper<Certificate> {
    /**
     * 获取当前用户的证书数量
     *
     * @param bo
     * @return
     */
    List<Certificate> getCertificateList(@Param("bo") CertificateQueryBo bo);

    /**
     * 获取当前证书的详情
     * @param bo
     * @return
     */
    Certificate getCertificateDetails(@Param("bo")CertificateQueryBo bo);
}

package com.jl.app.controller;


import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.bo.CertificateQueryBo;
import com.jl.system.service.ICertificateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 证书管理表 前端控制器
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */

@Api(value = "证书管理小程序控制器", tags = {"证书管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/miniapp/certificate")
public class CertificateController {

    private final ICertificateService certificateService;

    /**
     * 获取当前用户的证书数量
     * 我的证书
     * @param bo
     * @return
     */
    @ApiOperation("获取当前用户的证书数量")
    @GetMapping("/getCurrentUserCertificate")
    public AjaxResult getCurrentUserCertificate(CertificateQueryBo bo) {
        return certificateService.getCurrentUserCertificate(bo);
    }

    /**
     * 获取当前用户的证书列表
     * 我的证书
     * @param bo
     * @return
     */
    @ApiOperation("获取当前用户的证书列表")
    @GetMapping("/getCertificateList")
    public AjaxResult getCertificateList(CertificateQueryBo bo) {
        return certificateService.getCertificateList(bo);
    }

    /**
     * 获取当前证书的详情
     * @param bo
     * @return
     */
    @ApiOperation("获取当前证书的详情")
    @GetMapping("/getCertificateDetails")
    public AjaxResult getCertificateDetails(CertificateQueryBo bo) {
        return certificateService.getCertificateDetails(bo);
    }

}

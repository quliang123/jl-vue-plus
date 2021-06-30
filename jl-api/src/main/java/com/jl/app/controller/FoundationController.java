package com.jl.app.controller;


import com.jl.common.core.domain.AjaxResult;
import com.jl.system.service.IFoundationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 基金会管理表 前端控制器
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
@Api(value = "基金会管理小程序控制器", tags = {"基金会管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/miniapp/foundation")
public class FoundationController {

    private final IFoundationService foundationService;

    
}

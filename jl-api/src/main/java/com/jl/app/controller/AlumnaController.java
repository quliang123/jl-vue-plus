package com.jl.app.controller;

import com.jl.common.annotation.Log;
import com.jl.common.core.controller.BaseController;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.core.page.TableDataInfo;
import com.jl.common.enums.BusinessType;
import com.jl.common.utils.poi.ExcelUtil;
import com.jl.system.domain.bo.AlumnaAddBo;
import com.jl.system.domain.bo.AlumnaEditBo;
import com.jl.system.domain.bo.AlumnaQueryBo;
import com.jl.system.domain.bo.FoundationQueryBo;
import com.jl.system.domain.vo.AlumnaVo;
import com.jl.system.service.IAlumnaService;
import com.jl.system.service.IFoundationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 校友管理Controller
 * 
 * @author jl
 * @date 2021-05-25
 */
@Api(value = "校友管理控制器", tags = {"校友管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/miniapp/alumna")
public class AlumnaController extends BaseController {

    private final IAlumnaService iAlumnaService;

    private final IFoundationService iFoundationService;
    /**
     * 查询当前用户的基金会机构
     * @param fId
     * @return
     */
    @ApiOperation("查询当前用户的基金会机构")
    @GetMapping("/get/{fId}")
    public AjaxResult get(@PathVariable Long fId){
        return iFoundationService.getCurrentAlumnaFoundation(fId);
    }

}

package com.jl.web.controller.system;

import java.util.List;
import java.util.Arrays;

import com.jl.system.domain.bo.AlumnaAddBo;
import com.jl.system.domain.bo.AlumnaEditBo;
import com.jl.system.domain.bo.AlumnaQueryBo;
import com.jl.system.domain.vo.AlumnaVo;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.jl.common.annotation.Log;
import com.jl.common.core.controller.BaseController;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.enums.BusinessType;
import com.jl.system.service.IAlumnaService;
import com.jl.common.utils.poi.ExcelUtil;
import com.jl.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 校友管理Controller
 * 
 * @author jl
 * @date 2021-05-25
 */
@Api(value = "校友管理控制器", tags = {"校友管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/system/alumna")
public class AlumnaController extends BaseController {

    private final IAlumnaService iAlumnaService;

    /**
     * 查询校友管理列表
     */
    @ApiOperation("查询校友管理列表")
    @PreAuthorize("@ss.hasPermi('system:alumna:list')")
    @GetMapping("/list")
    public TableDataInfo<AlumnaVo> list(@Validated AlumnaQueryBo bo) {
        return iAlumnaService.queryPageList(bo);
    }

    /**
     * 导出校友管理列表
     */
    @ApiOperation("导出校友管理列表")
    @PreAuthorize("@ss.hasPermi('system:alumna:export')")
    @Log(title = "校友管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<AlumnaVo> export(@Validated AlumnaQueryBo bo) {
        List<AlumnaVo> list = iAlumnaService.queryList(bo);
        ExcelUtil<AlumnaVo> util = new ExcelUtil<AlumnaVo>(AlumnaVo.class);
        return util.exportExcel(list, "校友管理");
    }

    /**
     * 获取校友管理详细信息
     */
    @ApiOperation("获取校友管理详细信息")
    @PreAuthorize("@ss.hasPermi('system:alumna:query')")
    @GetMapping("/{alumnaId}")
    public AjaxResult<AlumnaVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("alumnaId") Long alumnaId) {
        return AjaxResult.success(iAlumnaService.queryById(alumnaId));
    }

    /**
     * 新增校友管理
     */
    @ApiOperation("新增校友管理")
    @PreAuthorize("@ss.hasPermi('system:alumna:add')")
    @Log(title = "校友管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody AlumnaAddBo bo) {
        return toAjax(iAlumnaService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改校友管理
     */
    @ApiOperation("修改校友管理")
    @PreAuthorize("@ss.hasPermi('system:alumna:edit')")
    @Log(title = "校友管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody AlumnaEditBo bo) {
        return toAjax(iAlumnaService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除校友管理
     */
    @ApiOperation("删除校友管理")
    @PreAuthorize("@ss.hasPermi('system:alumna:remove')")
    @Log(title = "校友管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{alumnaIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] alumnaIds) {
        return toAjax(iAlumnaService.deleteWithValidByIds(Arrays.asList(alumnaIds), true) ? 1 : 0);
    }
}

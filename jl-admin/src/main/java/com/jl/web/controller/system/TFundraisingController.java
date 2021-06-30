package com.jl.web.controller.system;

import java.util.List;
import java.util.Arrays;

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
import com.jl.system.domain.vo.TFundraisingVo;
import com.jl.system.domain.bo.TFundraisingQueryBo;
import com.jl.system.domain.bo.TFundraisingAddBo;
import com.jl.system.domain.bo.TFundraisingEditBo;
import com.jl.system.service.ITFundraisingService;
import com.jl.common.utils.poi.ExcelUtil;
import com.jl.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 募捐Controller
 * 
 * @author jl
 * @date 2021-05-21
 */
@Api(value = "募捐控制器", tags = {"募捐管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/system/fundraising")
public class TFundraisingController extends BaseController {

    private final ITFundraisingService iTFundraisingService;

    /**
     * 查询募捐列表
     */
    @ApiOperation("查询募捐列表")
    @PreAuthorize("@ss.hasPermi('system:fundraising:list')")
    @GetMapping("/list")
    public TableDataInfo<TFundraisingVo> list(@Validated TFundraisingQueryBo bo) {
        return iTFundraisingService.queryPageList(bo);
    }

    /**
     * 导出募捐列表
     */
    @ApiOperation("导出募捐列表")
    @PreAuthorize("@ss.hasPermi('system:fundraising:export')")
    @Log(title = "募捐", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TFundraisingVo> export(@Validated TFundraisingQueryBo bo) {
        List<TFundraisingVo> list = iTFundraisingService.queryList(bo);
        ExcelUtil<TFundraisingVo> util = new ExcelUtil<TFundraisingVo>(TFundraisingVo.class);
        return util.exportExcel(list, "募捐");
    }

    /**
     * 获取募捐详细信息
     */
    @ApiOperation("获取募捐详细信息")
    @PreAuthorize("@ss.hasPermi('system:fundraising:query')")
    @GetMapping("/{fdrId}")
    public AjaxResult<TFundraisingVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fdrId") Long fdrId) {
        return AjaxResult.success(iTFundraisingService.queryById(fdrId));
    }

    /**
     * 新增募捐
     */
    @ApiOperation("新增募捐")
    @PreAuthorize("@ss.hasPermi('system:fundraising:add')")
    @Log(title = "募捐", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TFundraisingAddBo bo) {
        return toAjax(iTFundraisingService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改募捐
     */
    @ApiOperation("修改募捐")
    @PreAuthorize("@ss.hasPermi('system:fundraising:edit')")
    @Log(title = "募捐", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TFundraisingEditBo bo) {
        return toAjax(iTFundraisingService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除募捐
     */
    @ApiOperation("删除募捐")
    @PreAuthorize("@ss.hasPermi('system:fundraising:remove')")
    @Log(title = "募捐" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fdrIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fdrIds) {
        return toAjax(iTFundraisingService.deleteWithValidByIds(Arrays.asList(fdrIds), true) ? 1 : 0);
    }
}

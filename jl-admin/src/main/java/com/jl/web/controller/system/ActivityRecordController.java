package com.jl.web.controller.system;

import java.util.List;
import java.util.Arrays;

import com.jl.system.domain.ActivityRecord;
import com.jl.system.domain.bo.ActivityRecordAddBo;
import com.jl.system.domain.bo.ActivityRecordEditBo;
import com.jl.system.domain.bo.ActivityRecordQueryBo;
import com.jl.system.domain.vo.ActivityRecordVo;
import com.jl.system.service.impl.ActivityRecordServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.jl.common.annotation.Log;
import com.jl.common.core.controller.BaseController;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.enums.BusinessType;
import com.jl.system.service.IActivityRecordService;
import com.jl.common.utils.poi.ExcelUtil;
import com.jl.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 募捐记录管理Controller
 *
 * @author jl
 * @date 2021-05-25
 */
@Api(value = "募捐记录管理控制器", tags = {"募捐记录管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/system/record")
public class ActivityRecordController extends BaseController {

    private final IActivityRecordService iActivityRecordService;
    private static final Logger log = LoggerFactory.getLogger(ActivityRecordServiceImpl.class);

    /**
     * 查询募捐记录管理列表
     */
    @ApiOperation("查询募捐记录管理列表")
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo<ActivityRecord> list(@Validated ActivityRecordQueryBo bo) {
         return iActivityRecordService.queryPageList(bo);
    }

    /**
     * 导出募捐记录管理列表
     */
    @ApiOperation("导出募捐记录管理列表")
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "募捐记录管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<ActivityRecordVo> export(@Validated ActivityRecordQueryBo bo) {
        List<ActivityRecordVo> list = iActivityRecordService.getActivityRecordList(bo);
        ExcelUtil<ActivityRecordVo> util = new ExcelUtil<ActivityRecordVo>(ActivityRecordVo.class);
        return util.exportExcel(list, "募捐记录管理");
    }

    /**
     * 获取募捐记录管理详细信息
     */
    @ApiOperation("获取募捐记录管理详细信息")
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping("/{arId}")
    public AjaxResult<ActivityRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                                @PathVariable("arId") Long arId) {
        return AjaxResult.success(iActivityRecordService.queryById(arId));
    }

    /**
     * 新增募捐记录管理
     */
    @ApiOperation("新增募捐记录管理")
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "募捐记录管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody ActivityRecordAddBo bo) {
        return toAjax(iActivityRecordService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改募捐记录管理
     */
    @ApiOperation("修改募捐记录管理")
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "募捐记录管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody ActivityRecordEditBo bo) {
        return toAjax(iActivityRecordService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除募捐记录管理
     */
    @ApiOperation("删除募捐记录管理")
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "募捐记录管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{arIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                   @PathVariable Long[] arIds) {
        return toAjax(iActivityRecordService.deleteWithValidByIds(Arrays.asList(arIds), true) ? 1 : 0);
    }

    /**
     * 统计累计捐款金额
     */
    @ApiOperation("统计累计捐款金额")
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping("/getTotalAmount")
    public AjaxResult getTotalAmount() {
        return iActivityRecordService.getTotalAmount();
    }

    /**
     * 统计累计捐款次数
     */
    @ApiOperation("统计累计捐款次数")
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping("/getDonateTotal")
    public AjaxResult getDonateTotal() {
        return iActivityRecordService.getDonateTotal();
    }

    /**
     * 统计累计捐款人数(人)
     */
    @ApiOperation("统计累计捐款人数")
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping("/getPeopleTotal")
    public AjaxResult getPeopleTotal() {
        return iActivityRecordService.getPeopleTotal();
    }

    /**
     * 捐款金额折线图
     *
     * @param selectType 请求的类别   0、日   1、月    2、年
     * @return
     */
    @ApiModelProperty("捐款金额折线图")
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping("/getActivityAmount")
    public AjaxResult getActivityAmount(String selectType) {
        return iActivityRecordService.getActivityAmount(selectType);
    }


}

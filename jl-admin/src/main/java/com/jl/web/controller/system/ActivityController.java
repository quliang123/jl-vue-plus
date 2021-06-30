package com.jl.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.jl.common.constant.ScheduleConstants;
import com.jl.common.exception.job.TaskException;
import com.jl.common.utils.DateUtils;
import com.jl.common.utils.SecurityUtils;
import com.jl.common.utils.StringUtils;
import com.jl.quartz.domain.SysJob;
import com.jl.quartz.service.ISysJobService;
import com.jl.quartz.util.CronUtils;
import com.jl.system.domain.Activity;
import com.jl.system.domain.ActivityRecord;
import com.jl.system.domain.bo.*;
import com.jl.system.domain.vo.ActivityVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

import org.quartz.SchedulerException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.jl.common.annotation.Log;
import com.jl.common.core.controller.BaseController;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.enums.BusinessType;
import com.jl.system.service.IActivityService;
import com.jl.common.utils.poi.ExcelUtil;
import com.jl.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 募捐活动管理Controller
 *
 * @author jl
 * @date 2021-05-25
 */
@Api(value = "募捐活动管理控制器", tags = {"募捐活动管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/system/activity")
public class ActivityController extends BaseController {

    private final IActivityService iActivityService;

    private final ISysJobService sysJobService;

    /**
     * 查询募捐活动管理列表
     */
    @ApiOperation("查询募捐活动管理列表")
    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/list")
    public TableDataInfo<Activity> list(@Validated ActivityQueryBo bo) {
        return iActivityService.queryPageList(bo);
    }

    /**
     * 导出募捐活动管理列表
     */
    @ApiOperation("导出募捐活动管理列表")
    @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "募捐活动管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<ActivityVo> export(@Validated ActivityQueryBo bo) {
        List<ActivityVo> list = iActivityService.queryList(bo);
        ExcelUtil<ActivityVo> util = new ExcelUtil<ActivityVo>(ActivityVo.class);
        return util.exportExcel(list, "募捐活动管理");
    }

    /**
     * 获取募捐活动管理详细信息
     */
    @ApiOperation("获取募捐活动管理详细信息")
    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping("/{activityId}")
    public AjaxResult<ActivityVo> getInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable("activityId") Long activityId) {
        return AjaxResult.success(iActivityService.queryById(activityId));
    }

    /**
     * 新增募捐活动管理
     */
    @ApiOperation("新增募捐活动管理")
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @Log(title = "募捐活动管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody ActivityAddBo bo) {
        AjaxResult ajaxResult = iActivityService.insertByAddBo(bo);
        if (ajaxResult.getCode().equals(HttpStatus.HTTP_OK)) {
            Activity activity = (Activity) ajaxResult.getData();
            if (activity.getSetUp().contains("2") || activity.getSetUp().contains("3")) {
                return createJob(activity);
            }
        }
        return ajaxResult;
    }

    /**
     * 创建定时任务
     */
    public AjaxResult createJob(Activity activity) {
        SysJob job = new SysJob();
        job.setJobGroup("DEFAULT");
        job.setJobName("关闭募捐活动" + activity.getActivityId());
        job.setCreateBy(SecurityUtils.getUsername());
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        job.setMisfirePolicy(ScheduleConstants.MISFIRE_IGNORE_MISFIRES);
        job.setConcurrent("1");
        job.setCronExpression(CronUtils.getCron(activity.getDueDate()));
        job.setInvokeTarget(StringUtils.format("jyTask.jyTaskParams({})", "'" + JSON.toJSON(job.getJobName() + "'")));
        job.setCreateTime(DateUtils.getNowDate());

//        Map<String, Object> params = new HashMap<>();
//        params.put("activityId", activity.getActivityId());
//        job.setParams(params);
        try {
            int i = sysJobService.insertJob(job);
            if (i == 0) {
                return AjaxResult.error("创建失败");
            }
        } catch (Exception e) {
            return AjaxResult.error("定时下架异常");
        }
        return AjaxResult.success();
    }

    /**
     * 修改募捐活动管理
     */
    @ApiOperation("修改募捐活动管理")
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "募捐活动管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody ActivityEditBo bo) {
        return toAjax(iActivityService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除募捐活动管理
     */
    @ApiOperation("删除募捐活动管理")
    @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "募捐活动管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                   @PathVariable Long[] activityIds) {
        return toAjax(iActivityService.deleteWithValidByIds(Arrays.asList(activityIds), true) ? 1 : 0);
    }

    /**
     * 确认发布项目
     */
    @ApiOperation("确认发布项目")
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @GetMapping("/publishProject")
    public AjaxResult publishProject(@NotEmpty(message = "主键不能为空") @RequestParam Long activityId) {
        return iActivityService.publishProject(activityId);
    }

    /**
     * 关闭活动
     */
    @ApiOperation("关闭活动")
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @GetMapping("/closeActivity")
    public AjaxResult closeActivity(@NotEmpty(message = "主键不能为空") @RequestParam Long activityId) {
        return iActivityService.closeActivity(activityId);
    }

    /**
     * 累计发布活动数
     *
     * @return
     */
    @ApiOperation("累计发布活动数")
    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping("/getPublishActivity")
    public AjaxResult getPublishActivity() {
        return iActivityService.getPublishActivity();
    }

}

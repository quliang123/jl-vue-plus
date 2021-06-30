package com.jl.app.controller;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.jl.common.annotation.Log;
import com.jl.common.constant.ScheduleConstants;
import com.jl.common.core.controller.BaseController;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.core.page.TableDataInfo;
import com.jl.common.enums.BusinessType;
import com.jl.common.utils.DateUtils;
import com.jl.common.utils.SecurityUtils;
import com.jl.common.utils.StringUtils;
import com.jl.common.utils.poi.ExcelUtil;
import com.jl.quartz.domain.SysJob;
import com.jl.quartz.service.ISysJobService;
import com.jl.quartz.util.CronUtils;
import com.jl.system.domain.Activity;
import com.jl.system.domain.TranOrder;
import com.jl.system.domain.bo.*;
import com.jl.system.domain.vo.ActivityVo;
import com.jl.system.service.IActivityRecordService;
import com.jl.system.service.IActivityService;
import com.jl.system.service.IDonateTypeService;
import com.jl.system.service.ISysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
 * 活动管理Controller
 *
 * @author jl
 * @date 2021-05-25
 */
@Api(value = "活动管理小程序控制器", tags = {"活动管理管理小程序"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/miniapp/activity")
public class ActivityController extends BaseController {

    private final IActivityService iActivityService;

    private final IActivityRecordService iActivityRecordService;

    private final ISysDictDataService iSysDictDataService;

    private final IDonateTypeService iDonateTypeService;
    /**
     * 获取活动的首页列表
     *  默认全部活动     selectType  ( 1、进行中 2、已结束)
     * @return
     */
    @ApiOperation("获取活动首页列表")
    @GetMapping("/getActivityList")
    public AjaxResult getActivityList(ActivityQueryBo bo) {
        return iActivityService.getActivityList(bo);
    }


    /**
     * 获取募捐活动管理详细信息
     */
    @ApiOperation("获取募捐活动管理详细信息")
    @GetMapping("/getActivityById")
    public AjaxResult<ActivityVo> getInfo(ActivityQueryBo bo) {
        return iActivityService.getActivityById(bo);
    }

    /**
     * 通过活动id获取募捐人员
     *
     * @return
     */
    @ApiOperation("通过活动id获取募捐人员")
    @GetMapping("/getDoneStaffByActivityId")
    public AjaxResult getDoneStaffByActivityId(ActivityQueryBo bo) {
        return iActivityService.getDoneStaffByActivityId(bo);
    }

    /**
     * 我的捐赠
     *
     * @param bo 请求的类别   0、按项目归类   1、按时间归类
     * @return
     */
    @ApiOperation("我的捐赠")
    @GetMapping("/getActivityRecordBySelectType")
    public AjaxResult getActivityRecordBySelectType(ActivityRecordQueryBo bo) {
        return iActivityService.getActivityRecordBySelectType(bo);
    }

    /**
     * 获取当前用户的累计捐款
     *
     * @param bo
     * @return
     */
    @ApiOperation("获取当前用户的累计捐款")
    @GetMapping("/getCurrentUserTotalAmount")
    public AjaxResult getCurrentUserTotalAmount(ActivityRecordQueryBo bo) {
        return iActivityService.getCurrentUserTotalAmount(bo);
    }

    /**
     * 获取当前用户参与的项目数量
     *  我的捐赠
     * @param bo
     * @return
     */
    @ApiOperation("获取当前用户参与的项目")
    @GetMapping("/getCurrentUserJoinProjectTotal")
    public AjaxResult getCurrentUserJoinProjectTotal(ActivityRecordQueryBo bo) {
        return iActivityRecordService.getCurrentUserJoinProjectTotal(bo);
    }



    /**
     * 获取首页筛选条件
     *
     * @return
     */
    @ApiOperation("获取首页筛选条件")
    @GetMapping("/getHomeFilterActivityTypeList")
    public AjaxResult getHomeFilterActivityTypeList() {
        return iSysDictDataService.getActivityType();
    }

    /**
     *捐款类别
     * @return
     */
    @ApiOperation("捐款类别")
    @GetMapping("/getDonateTypeList")
    public AjaxResult getDonateTypeList(){
        return iDonateTypeService.selectDonateType();
    }

}

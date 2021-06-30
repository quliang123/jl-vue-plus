package com.jl.app.controller;

import com.jl.common.annotation.Log;
import com.jl.common.core.controller.BaseController;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.core.page.TableDataInfo;
import com.jl.common.enums.BusinessType;
import com.jl.common.utils.poi.ExcelUtil;
import com.jl.system.domain.ActivityRecord;
import com.jl.system.domain.bo.ActivityRecordAddBo;
import com.jl.system.domain.bo.ActivityRecordEditBo;
import com.jl.system.domain.bo.ActivityRecordQueryBo;
import com.jl.system.domain.vo.ActivityRecordVo;
import com.jl.system.service.IActivityRecordService;
import com.jl.system.service.impl.ActivityRecordServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 募募捐记录小程序Controller
 *
 * @author jl
 * @date 2021-05-25
 */
@Api(value = "募捐记录小程序控制器", tags = {"募捐记录小程序管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/miniapp/record")
public class ActivityRecordController extends BaseController {

    private final IActivityRecordService iActivityRecordService;

    private static final Logger log = LoggerFactory.getLogger(ActivityRecordController.class);

    /**
     * 获取当前用户捐赠项目数量
     * @param bo
     * @return
     */
    @ApiOperation("获取当前用户捐赠项目数量")
    @GetMapping("/getCurrentUserDonationProject")
    public AjaxResult getCurrentUserDonationProject(ActivityRecordQueryBo bo){
        return iActivityRecordService.getCurrentUserDonationProject(bo);
    }

    /**
     * 查询募捐记录管理列表
     */
    @ApiOperation("查询募捐记录管理列表")
    @GetMapping("/list")
    public TableDataInfo<ActivityRecord> list(@Validated ActivityRecordQueryBo bo) {
        return iActivityRecordService.queryPageList(bo);
    }



}

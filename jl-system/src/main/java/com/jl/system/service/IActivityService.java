package com.jl.system.service;

import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.Activity;
import com.jl.system.domain.TranOrder;
import com.jl.system.domain.bo.*;
import com.jl.system.domain.vo.ActivityVo;
import com.jl.common.core.page.IServicePlus;
import com.jl.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 募捐活动管理Service接口
 *
 * @author jl
 * @date 2021-05-25
 */
public interface IActivityService extends IServicePlus<Activity> {
    /**
     * 查询单个
     *
     * @return
     */
    ActivityVo queryById(Long activityId);

    /**
     * 查询列表
     */
    TableDataInfo<Activity> queryPageList(ActivityQueryBo bo);

    /**
     * 查询列表
     */
    List<ActivityVo> queryList(ActivityQueryBo bo);

    /**
     * 根据新增业务对象插入募捐活动管理
     *
     * @param bo 募捐活动管理新增业务对象
     * @return
     */
    AjaxResult insertByAddBo(ActivityAddBo bo);

    /**
     * 根据编辑业务对象修改募捐活动管理
     *
     * @param bo 募捐活动管理编辑业务对象
     * @return
     */
    Boolean updateByEditBo(ActivityEditBo bo);

    /**
     * 校验并删除数据
     *
     * @param ids     主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 确认发布项目
     */
    AjaxResult publishProject(Long activityId);

    /**
     * 关闭活动
     */
    AjaxResult closeActivity(Long activityId);

    /**
     * 累计发布活动数
     *
     * @return
     */
    AjaxResult getPublishActivity();

    /**
     * 累计发布活动数
     *
     * @return
     */
    AjaxResult getFinishActivity();

    /**
     * 通过进行中、已结束状态来获取活动列表
     *  1、进行中       2、已结束
     * @param selectStatus
     * @return
     */
    AjaxResult getActivityListBySelectStatus(String selectStatus);

    /**
     * 通过活动id获取募捐人员
     * @param bo
     * @return
     */
    AjaxResult getDoneStaffByActivityId(ActivityQueryBo bo);

    /**
     * 记录募捐信息
     * @param bo
     * @return
     */
    boolean addDoneInfo(TranOrder bo);

    /**
     * 获取当前登录用户捐赠项目
     * @param bo
     * @return
     */
    AjaxResult getActivityRecordBySelectType(ActivityRecordQueryBo bo);

    /**
     * 获取当前用户的累计捐款
     * @param bo
     * @return
     */
    AjaxResult getCurrentUserTotalAmount(ActivityRecordQueryBo bo);


    /**
     * 获取活动首页列表
     * @param bo
     * @return
     */
    AjaxResult getActivityList(ActivityQueryBo bo);



    /**
     * 通过活动id获取活动详情
     * @param bo
     * @return
     */
    AjaxResult getActivityById(ActivityQueryBo bo);
}

package com.jl.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.core.domain.entity.SysRole;
import com.jl.system.domain.Activity;
import com.jl.common.core.page.BaseMapperPlus;
import com.jl.system.domain.bo.ActivityAddBo;
import com.jl.system.domain.bo.ActivityQueryBo;
import com.jl.system.domain.bo.ActivityRecordQueryBo;
import com.jl.system.domain.vo.ActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 募捐活动管理Mapper接口
 *
 * @author jl
 * @date 2021-05-25
 */
public interface ActivityMapper extends BaseMapperPlus<Activity> {
    /**
     * 通过活动id获取募捐人员
     * @param bo
     * @return
     */
    List<Activity> getDoneStaffByActivityId(@Param("bo")ActivityQueryBo bo);
    /**
     * 我的捐赠
     *
     * @param bo 请求的类别   0、按项目归类   1、按时间归类
     * @return
     */
    List<Activity> getActivityRecordBySelectType(@Param("bo")ActivityRecordQueryBo bo);

    /**
     *  获取当前用户的累计捐款
     * @param bo
     * @return
     */
    int getCurrentUserTotalAmount(@Param("bo")ActivityRecordQueryBo bo);


    /**
     * 获取活动列表
     * @param bo
     * @return
     */
    List<Activity> getActivityList(@Param("buildPage") Page<Object> buildPage,@Param("bo")ActivityQueryBo bo);



}

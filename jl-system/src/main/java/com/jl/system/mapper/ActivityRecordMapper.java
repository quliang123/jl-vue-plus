package com.jl.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jl.common.core.domain.entity.SysRole;
import com.jl.system.domain.ActivityRecord;
import com.jl.common.core.page.BaseMapperPlus;
import com.jl.system.domain.bo.ActivityRecordQueryBo;
import org.apache.ibatis.annotations.Param;

import javax.swing.*;
import java.util.List;

/**
 * 募捐记录管理Mapper接口
 *
 * @author jl
 * @date 2021-05-25
 */
public interface ActivityRecordMapper extends BaseMapperPlus<ActivityRecord> {

    /**
     * 分页查询活动记录列表
     */
    Page<ActivityRecord> selectActivityRecordVo(@Param("buildPage") Page<Object> buildPage, @Param("bo") ActivityRecordQueryBo bo);

    /**
     * @return
     */
    public int countDistinct();

    /**
     * 获取最近七天的活动募捐金额
     *
     * @return
     */
    List<ActivityRecord> get7DayActivityRecord();

    /**
     * 获取最近六个月的活动募捐金额
     *
     * @return
     */
    List<ActivityRecord> get6MonthActivityRecord();

    /**
     * 获取每年活动募捐金额
     *
     * @return
     */
    List<ActivityRecord> getYearActivityRecord();

    /**
     * 统计累计捐款金额
     * @return
     */
    int getTotalActivityAmount();

    /**
     * 获取募捐记录管理列表
     * @param buildPage 分页对象
     * @param bo    查询对象
     * @return
     */
    List<ActivityRecord> getActivityRecordList(@Param("buildPage") Page<Object> buildPage, @Param("bo") ActivityRecordQueryBo bo);

    /**
     * 返回id
     * 添加募捐记录返回募捐对象
     * @param ar
     * @return
     */
    int insertActivityRecord(ActivityRecord ar);

    /**
     *  获取当前用户的参与项目数量
     * @param bo
     * @return
     */
    int getCurrentUserJoinProject(@Param("bo")ActivityRecordQueryBo bo);
}

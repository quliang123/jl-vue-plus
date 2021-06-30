package com.jl.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.core.page.PagePlus;
import com.jl.common.utils.DateUtils;
import com.jl.common.utils.PageUtils;
import com.jl.common.core.page.TableDataInfo;
import com.jl.system.domain.Activity;
import com.jl.system.domain.Alumna;
import com.jl.system.domain.bo.ActivityRecordAddBo;
import com.jl.system.domain.bo.ActivityRecordEditBo;
import com.jl.system.domain.bo.ActivityRecordQueryBo;
import com.jl.system.domain.vo.ActivityRecordVo;
import com.jl.system.domain.vo.ActivityVo;
import com.jl.system.domain.vo.AlumnaVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jl.system.domain.ActivityRecord;
import com.jl.system.mapper.ActivityRecordMapper;
import com.jl.system.service.IActivityRecordService;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 募捐记录管理Service业务层处理
 *
 * @author jl
 * @date 2021-05-25
 */
@Service
public class ActivityRecordServiceImpl extends ServiceImpl<ActivityRecordMapper, ActivityRecord> implements IActivityRecordService {
    private static final Logger log = LoggerFactory.getLogger(ActivityRecordServiceImpl.class);
    @Autowired
    private IActivityRecordService activityRecordService;

    @Override
    public ActivityRecordVo queryById(Long arId) {
        return getVoById(arId, ActivityRecordVo.class);
    }

    @Override
    public TableDataInfo<ActivityRecord> queryPageList(ActivityRecordQueryBo bo){
        SimpleDateFormat sdf=new SimpleDateFormat(DateUtils.YYYY_MM_DD);
        Map<String, Object> params = bo.getParams();
         if (params.size()>0) {
            String beginTime = (String) params.get("beginTime");
            String endTime = (String) params.get("endTime");
            try {
                if (!Objects.equals(beginTime,null)) {
                    bo.setDonateTime(sdf.parse(beginTime));
                    bo.setDueDate(sdf.parse(endTime));
                 }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return PageUtils.buildDataInfo(baseMapper.selectActivityRecordVo(PageUtils.buildPage(), bo));
    }

    @Override
    public List<ActivityRecordVo> queryList(ActivityRecordQueryBo bo) {
        return listVo(buildQueryWrapper(bo), ActivityRecordVo.class);
    }

    private LambdaQueryWrapper<ActivityRecord> buildQueryWrapper(ActivityRecordQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ActivityRecord> lqw = Wrappers.lambdaQuery();


        lqw.ge(params != null && params.get("beginTime") != null, ActivityRecord::getCreateTime, params.get("beginTime"));
        lqw.le(params != null && params.get("endTime") != null, ActivityRecord::getCreateTime, params.get("endTime"));
        lqw.eq(StrUtil.isNotBlank(bo.getDonateNumber()), ActivityRecord::getDonateNumber, bo.getDonateNumber());
        lqw.eq(bo.getActivityId() != null, ActivityRecord::getActivityId, bo.getActivityId());
        lqw.eq(bo.getAlumnaId() != null, ActivityRecord::getAlumnaId, bo.getAlumnaId());
        lqw.eq(StrUtil.isNotBlank(bo.getAmount()), ActivityRecord::getAmount, bo.getAmount());
        lqw.eq(bo.getDonateTime() != null, ActivityRecord::getDonateTime, bo.getDonateTime());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(ActivityRecordAddBo bo) {
        ActivityRecord add = BeanUtil.toBean(bo, ActivityRecord.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(ActivityRecordEditBo bo) {
        ActivityRecord update = BeanUtil.toBean(bo, ActivityRecord.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(ActivityRecord entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }

    /**
     * 统计累计捐款金额
     */
    @Override
    public AjaxResult getTotalAmount() {
        return AjaxResult.success(baseMapper.getTotalActivityAmount());
    }

    /**
     * 统计累计捐款次数
     */
    @Override
    public AjaxResult getDonateTotal() {
        return AjaxResult.success(activityRecordService.count());
    }

    /**
     * 统计累计捐赠人数
     */
    @Override
    public AjaxResult getPeopleTotal() {
//        LambdaQueryWrapper<ActivityRecord> lqw = Wrappers.lambdaQuery();
//        lqw.select("distinct ",ActivityRecord::getAlumnaId);
        int count = baseMapper.countDistinct();
        return AjaxResult.success(count);
    }

    /**
     * 获取活动募捐金额
     *
     * @param selectType 0、日 1、月 2、年
     * @return
     */
    @Override
    public AjaxResult getActivityAmount(String selectType) {

        //如果没有选择日月年，默认显示七天数据
        if (Objects.equals(selectType, null)) {
            //获取七天
            List<ActivityRecord> recordList = baseMapper.get7DayActivityRecord();
            if (recordList.size() < 7) {
                return AjaxResult.success(builderData(recordList, DateUtils.YYYY_MM_DD));
            }
            return AjaxResult.success(recordList);
        } else {
            if ("0".equals(selectType)) {
                List<ActivityRecord> recordList = baseMapper.get7DayActivityRecord();
                return AjaxResult.success(builderData(recordList, DateUtils.YYYY_MM_DD));
            } else if ("1".equals(selectType)) {
                List<ActivityRecord> monthActivityRecord = baseMapper.get6MonthActivityRecord();
                return AjaxResult.success(builderData(monthActivityRecord, DateUtils.YYYY_MM));
            } else if ("2".equals(selectType)) {
                List<ActivityRecord> recordList = baseMapper.getYearActivityRecord();
                return AjaxResult.success(builderData(recordList, DateUtils.YYYY));
            }
        }
        return AjaxResult.error();
    }

    /**
     * 获取募捐记录管理列表
     *
     * @return
     */
    @Override
    public List<ActivityRecordVo> getActivityRecordList(ActivityRecordQueryBo bo) {
        List<ActivityRecord> activityRecordList = baseMapper.getActivityRecordList(PageUtils.buildPage(), bo);
        List<ActivityRecordVo> arList = new LinkedList<ActivityRecordVo>();
        activityRecordList.forEach(ar -> {
            ActivityRecordVo activityRecord = BeanUtil.toBean(ar, ActivityRecordVo.class);
            ActivityVo activityVo = BeanUtil.toBean(ar.getActivity(), ActivityVo.class);
            AlumnaVo alumnaVo = BeanUtil.toBean(ar.getAlumna(), AlumnaVo.class);
            activityRecord.setActivityVo(activityVo);
            activityRecord.setAlumnaVo(alumnaVo);
            arList.add(activityRecord);
        });
        return arList;
    }

    /**
     * 构建7日、6月、每年的图表数据
     *
     * @param recordList
     * @param patterns
     * @return
     */
    public List<ActivityRecordVo> builderData(List<ActivityRecord> recordList, String patterns) {
        SimpleDateFormat sdf = new SimpleDateFormat(patterns);
        //获取七天内日期
        List<String> nDaysList = com.jl.system.utils.DateUtils.getNDaysList(null, null, 7, patterns);

        Map<String, String> recordMap = new HashMap<>();
        recordList.forEach((activity) -> {
            String format = sdf.format(activity.getDonateTime());
            String amount = activity.getAmount();
            recordMap.put(format, amount);
        });

        //累计募捐金额
        AtomicInteger totalAmount = new AtomicInteger();

        //循环前七天日期
        nDaysList.forEach((d -> {
            String amount = recordMap.get(d);
            //如果在对比七天内，如果没有获取成功的话，金额初始化为0
            if (Objects.equals(amount, null)) {
                totalAmount.updateAndGet(v -> v + 0);
                recordMap.put(d, String.valueOf(totalAmount));
            } else {
                totalAmount.getAndSet(Double.valueOf(amount).intValue());
                recordMap.put(d, String.valueOf(totalAmount));
            }
        }));
        List<ActivityRecordVo> recordVoList = new ArrayList<>();
        //将某段时间内数据转换成集合
        recordMap.forEach((k, v) -> {
            ActivityRecordVo activityRecord = new ActivityRecordVo();
            BigDecimal amount = new BigDecimal(v);
            activityRecord.setDonationTime(k);
            activityRecord.setSumAmount(amount);
            recordVoList.add(activityRecord);
        });
        //进行排序
        recordVoList.sort((ActivityRecordVo a1, ActivityRecordVo a2) -> a1.getDonationTime().compareTo(a2.getDonationTime()));
        return recordVoList;
    }

    /**
     * 获取当前用户的捐赠次数
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getCurrentUserDonationProject(ActivityRecordQueryBo bo) {
        return AjaxResult.success(baseMapper.selectCount(new QueryWrapper<ActivityRecord>().eq("alumna_id",bo.getAlumnaId())));
    }

    /**
     * 返回id
     * 添加募捐记录返回募捐对象
     * @param bo
     * @return
     */
    @Override
    public AjaxResult insertActivityRecord(ActivityRecord ar) {
        //ActivityRecord ar = BeanUtil.toBean(bo, ActivityRecord.class);
        return  AjaxResult.success(baseMapper.insertActivityRecord(ar));
    }

    /**
     * 获取当前用户参与的项目数量
     *
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getCurrentUserJoinProjectTotal(ActivityRecordQueryBo bo) {
        return AjaxResult.success(activityRecordService.count(buildQueryWrapper(bo)));
    }


    /**
     * 获取当前用户参与的项目
     *  我的捐赠
     * @param bo
     * @return
     */
    @Override
    @ApiOperation("获取当前用户参与的项目")
    @GetMapping("/getCurrentUserJoinProject")
    public AjaxResult getCurrentUserJoinProject(ActivityRecordQueryBo bo) {
        return AjaxResult.success(baseMapper.getCurrentUserJoinProject(bo));
    }
}


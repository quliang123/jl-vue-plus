package com.jl.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.common.constant.CheckConstants;
import com.jl.common.constant.ScheduleConstants;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.enums.ActivityStatus;
import com.jl.common.exception.CustomException;
import com.jl.common.exception.user.UserException;
import com.jl.common.utils.DateUtils;
import com.jl.common.utils.DictUtils;
import com.jl.common.utils.PageUtils;
import com.jl.common.core.page.PagePlus;
import com.jl.common.core.page.TableDataInfo;
import com.jl.common.utils.SecurityUtils;
import com.jl.system.domain.*;
import com.jl.system.domain.Dto.CheckEntityDto;
import com.jl.system.domain.bo.*;
import com.jl.system.domain.vo.ActivityRecordVo;
import com.jl.system.domain.vo.ActivityVo;
import com.jl.system.domain.vo.AlumnaVo;
import com.jl.system.domain.vo.User;
import com.jl.system.service.*;
import com.jl.system.utils.IdSeq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jl.system.mapper.ActivityMapper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 募捐活动管理Service业务层处理
 *
 * @author jl
 * @date 2021-05-25
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private IAlumnaService alumnaService;

    @Autowired
    private IActivityRecordService activityRecordService;

    @Autowired
    private ICertificateService certificateService;

    @Autowired
    private IFoundationService foundationService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IActivityService iActivityService;

    @Override
    public ActivityVo queryById(Long activityId) {
        return getVoById(activityId, ActivityVo.class);
    }

    @Override
    public TableDataInfo<Activity> queryPageList(ActivityQueryBo bo) {
        PagePlus<Activity, Activity> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), Activity.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<ActivityVo> queryList(ActivityQueryBo bo) {
        return listVo(buildQueryWrapper(bo), ActivityVo.class);
    }

    private LambdaQueryWrapper<Activity> buildQueryWrapper(ActivityQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Activity> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getActivityName()), Activity::getActivityName, bo.getActivityName());
        lqw.eq(bo.getActivityType() != null, Activity::getActivityType, bo.getActivityType());
        lqw.eq(bo.getActivityStatus() != null, Activity::getActivityStatus, bo.getActivityStatus());
        lqw.eq(bo.getDueDate() != null, Activity::getDueDate, bo.getDueDate());
        lqw.eq(bo.getSetUp() != null, Activity::getSetUp, bo.getSetUp());
        lqw.eq(StrUtil.isNotBlank(bo.getLogo()), Activity::getLogo, bo.getLogo());
        lqw.eq(StrUtil.isNotBlank(bo.getDescription()), Activity::getDescription, bo.getDescription());
        lqw.eq(StrUtil.isNotBlank(bo.getDetails()), Activity::getDetails, bo.getDetails());
        lqw.ge(params != null && params.get("beginTime") != null, Activity::getCreateTime, params.get("beginTime"));
        lqw.le(params != null && params.get("endTime") != null, Activity::getCreateTime, params.get("endTime"));
        return lqw;
    }

    @Override
    public AjaxResult insertByAddBo(ActivityAddBo bo) {
        Activity add = BeanUtil.toBean(bo, Activity.class);
        CheckEntityDto checkEntityDto = validEntityBeforeSave(add);
        if (checkEntityDto.getStatus().equals(CheckConstants.FAIL)) {
            return AjaxResult.error(checkEntityDto.getMsg());
        }
        add.setCreateBy(SecurityUtils.getUsername());
        add.setCreateTime(DateUtils.getNowDate());
        //如果项目设置中有'1，3',项目创建需要自动上架
        if (add.getSetUp().equals("1") || add.getSetUp().equals("3")) {
            add.setActivityStatus(DictUtils.getDictValue("sys_activity_status", "进行中"));
        }
        boolean save = save(add);
        //baseMapper.saveActivity(add);
        if (save) {
            return AjaxResult.success(add);
        }
        return AjaxResult.error("创建失败");
    }

    @Override
    public Boolean updateByEditBo(ActivityEditBo bo) {
        Activity update = BeanUtil.toBean(bo, Activity.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private CheckEntityDto validEntityBeforeSave(Activity entity) {
        //TODO 做一些数据校验,如唯一约束
        CheckEntityDto checkEntityDto = new CheckEntityDto();
        if (StringUtils.isBlank(entity.getActivityName())) {
            checkEntityDto.setStatus(CheckConstants.FAIL);
            checkEntityDto.setMsg("请填写活动名称");
            return checkEntityDto;
        }
        if (entity.getActivityType() == null) {
            checkEntityDto.setStatus(CheckConstants.FAIL);
            checkEntityDto.setMsg("请选择活动类别");
            return checkEntityDto;
        }
        if (entity.getDueDate() == null) {
            checkEntityDto.setStatus(CheckConstants.FAIL);
            checkEntityDto.setMsg("截止时间不能为空");
            return checkEntityDto;
        }
        checkEntityDto.setStatus(CheckConstants.OK);
        return checkEntityDto;

    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }

    /**
     * 确认发布项目
     */
    @Override
    public AjaxResult publishProject(Long activityId) {
        String username = SecurityUtils.getUsername();
        Activity activity = baseMapper.selectById(activityId);
//        activity.setActivityStatus(ActivityStatus.PROCESSING.getStatus());
        activity.setUpdateBy(username);
        activity.setUpdateTime(DateUtils.getNowDate());
        int i = baseMapper.updateById(activity);
        if (i > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error("发布募捐活动失败");
    }

    /**
     * 关闭活动
     */
    @Override
    public AjaxResult closeActivity(Long activityId) {
        String username = SecurityUtils.getUsername();
        Activity activity = baseMapper.selectById(activityId);
//        activity.setActivityStatus(ActivityStatus.OVER.getStatus());
        activity.setUpdateBy(username);
        activity.setUpdateTime(DateUtils.getNowDate());
        int i = baseMapper.updateById(activity);
        if (i > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error("关闭活动失败");

    }

    /**
     * 累计发布活动数
     *
     * @return
     */
    @Override
    public AjaxResult getPublishActivity() {
        QueryWrapper<Activity> qw = new QueryWrapper<Activity>();
        qw.notIn("activity_status", "0");
        int count = this.count(qw);
        return AjaxResult.success(count);
    }

    /**
     * 累计已经结束活动数量
     *
     * @return
     */
    @Override
    public AjaxResult getFinishActivity() {
        QueryWrapper<Activity> qw = new QueryWrapper<Activity>();
        qw.in("activity_status", "2");
        int count = this.count(qw);
        return AjaxResult.success(count);
    }

    /**
     * 通过进行中、已结束状态来获取活动列表
     *
     * @param selectStatus 1、进行中       2、已结束
     * @return 10条数据
     */
    @Override
    public AjaxResult getActivityListBySelectStatus(String selectStatus) {
        QueryWrapper<Activity> qw = new QueryWrapper<Activity>();
        qw.eq("activity_status", selectStatus);
        List<Activity> list = this.list(qw);
        return AjaxResult.success(list);
    }

    /**
     * 通过活动id获取募捐人员
     *
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getDoneStaffByActivityId(ActivityQueryBo bo) {

        return AjaxResult.success(baseMapper.getDoneStaffByActivityId(bo));
    }

    /**
     * 添加捐募信息
     *
     * @param bo
     * @return
     */
    @Override
    public boolean addDoneInfo(TranOrder bo) {

        //当前用户
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", bo.getUserId()));

        //查询校友
        Alumna alumna = alumnaService.getOne(new QueryWrapper<Alumna>().eq("user_id", bo.getUserId()));

        //当前募捐活动
        Activity activity = iActivityService.getOne(new QueryWrapper<Activity>().eq("activity_id", bo.getActivityId()));

        //如果校友存在
        if (!Objects.equals(user, null) && !Objects.equals(activity, null)) {
            //
            ActivityRecord record = new ActivityRecord();
            //捐赠编码暂未生成
            record.setDonateNumber(IdSeq.generateSequence(false));
            record.setActivityId(bo.getActivityId());
            record.setAlumnaId(alumna.getAlumnaId());
            record.setUserId(user.getUserId());
            record.setFoundationId(alumna.getFoundationId());
            record.setAmount(bo.getTotalFee());
            record.setDonateTime(new Date());
            record.setCreateTime(new Date());
            record.setCreateBy(alumna.getName());

            //增加募捐记录,并返回主键id
            activityRecordService.insertActivityRecord(record);
            //证书对象
            Certificate certificate = new Certificate();

            //证书编码暂未生成
            certificate.setCertificateNumber(IdSeq.generateSequence(false));
            certificate.setUserId(user.getUserId());
            certificate.setFoundationId(activity.getFoundationId());
            //设置关联募捐活动id
            certificate.setArId(Long.valueOf(record.getArId()));

            if (!Objects.equals(alumna, null)) {
                AlumnaAddBo alumnaAddBo = BeanUtil.toBean(alumna, AlumnaAddBo.class);

                certificate.setAlumnaId(alumna.getAlumnaId());


                //如果是校友身份
                if (Objects.equals(alumna.getIdentity(), 0)) {

                    alumna.setTotalAmount(new BigDecimal(alumna.getTotalAmount()).add(new BigDecimal(bo.getTotalFee())).toString());
                    alumna.setTotalDonate(alumna.getTotalDonate() + 1);

                    //增加校友记录
                    Boolean alumnaAdd = alumnaService.updateAlumna(alumna);

                    //添加证书
                    boolean certificateAdd = certificateService.save(certificate);
                    return certificateAdd && alumnaAdd;
                } else {
                    alumnaAddBo.setMobile(bo.getMobile());
                    alumnaAddBo.setIdCard(bo.getIdCard());
                    alumnaAddBo.setEmail(bo.getEmail());
                    alumnaAddBo.setIdentity(bo.getIdentity());
                    try {
                        alumnaAddBo.setGraduateTime(DateUtils.parseDate(bo.getGraduateTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //增加非校友
                    Boolean alumnaAdd = alumnaService.insertByAddBo(alumnaAddBo);

                    //添加证书
                    boolean certificateAdd = certificateService.save(certificate);

                    return alumnaAdd && certificateAdd;
                }
            }
        }
        return false;
    }

    /**
     * 我的捐赠
     *
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getActivityRecordBySelectType(ActivityRecordQueryBo bo) {
        return AjaxResult.success(baseMapper.getActivityRecordBySelectType(bo));
    }

    /**
     * 获取当前用户的累计捐款
     *
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getCurrentUserTotalAmount(ActivityRecordQueryBo bo) {
        return AjaxResult.success(baseMapper.getCurrentUserTotalAmount(bo));
    }

    /**
     * 获取活动列表
     *
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getActivityList(ActivityQueryBo bo) {
        List<Activity> activityList = baseMapper.getActivityList(PageUtils.buildPage(), bo);
        activityList.forEach(ay -> {
            ActivityRecord activityRecord = ay.getActivityRecord();
            if (!Objects.equals(activityRecord, null)) {
                Long alumnaId = activityRecord.getAlumnaId();
                List<Alumna> list = alumnaService.list(new QueryWrapper<Alumna>().eq("alumna_Id", alumnaId));
                ay.setAlumnaList(list);
            }
        });
        return AjaxResult.success(activityList);
    }

    /**
     * 通过活动id获取活动详情
     *
     * @param bo
     * @return
     */
    @Override
    public AjaxResult getActivityById(ActivityQueryBo bo) {
        return AjaxResult.success(baseMapper.selectOne(new QueryWrapper<Activity>().eq("activity_id", bo.getActivityId())));
    }

}

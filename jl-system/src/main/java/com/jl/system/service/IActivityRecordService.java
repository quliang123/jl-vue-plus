package com.jl.system.service;

import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.ActivityRecord;
import com.jl.system.domain.bo.ActivityRecordAddBo;
import com.jl.system.domain.bo.ActivityRecordEditBo;
import com.jl.system.domain.bo.ActivityRecordQueryBo;
import com.jl.system.domain.vo.ActivityRecordVo;
import com.jl.common.core.page.IServicePlus;
import com.jl.common.core.page.TableDataInfo;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

/**
 * 募捐记录管理Service接口
 *
 * @author jl
 * @date 2021-05-25
 */
public interface IActivityRecordService extends IServicePlus<ActivityRecord> {
	/**
	 * 查询单个
	 * @return
	 */
	ActivityRecordVo queryById(Long arId);

	/**
	 * 查询列表
	 */
    TableDataInfo<ActivityRecord> queryPageList(ActivityRecordQueryBo bo);
	/**
	 * 查询列表
	 */
	List<ActivityRecordVo> queryList(ActivityRecordQueryBo bo);

	/**
	 * 添加募捐记录返回募捐对象填充id
	 * @param bo
	 * @return
	 */
	AjaxResult insertActivityRecord(ActivityRecord bo);
	/**
	 * 根据新增业务对象插入募捐记录管理
	 * @param bo 募捐记录管理新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(ActivityRecordAddBo bo);

	/**
	 * 根据编辑业务对象修改募捐记录管理
	 * @param bo 募捐记录管理编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(ActivityRecordEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

	/**
	 * 统计累计捐款金额
	 */
	AjaxResult getTotalAmount();

	/**
	 * 统计累计捐款次数
	 */
	AjaxResult getDonateTotal();

	/**
	 * 统计捐赠人数
	 */
	AjaxResult getPeopleTotal();

	/**
	 * 获取活动募捐金额
	 *
	 * @param selectType 0、日 1、月 2、年
	 * @return
	 */
	AjaxResult getActivityAmount(String selectType);

	/**
	 * 获取募捐记录管理列表
	 * @return
	 */
	List<ActivityRecordVo> getActivityRecordList(ActivityRecordQueryBo bo);

	/**
	 * 获取当前校友的捐赠数量
	 * @param bo
	 * @return
	 */
	AjaxResult getCurrentUserDonationProject(ActivityRecordQueryBo bo);

	/**
	 * 获取当前用户的参与项目
	 * @param bo
	 * @return
	 */
	AjaxResult getCurrentUserJoinProject(ActivityRecordQueryBo bo);
	/**
	 * 获取当前用户参与的项目数量
	 * @param bo
	 * @return
	 */
	AjaxResult getCurrentUserJoinProjectTotal(ActivityRecordQueryBo bo);
}

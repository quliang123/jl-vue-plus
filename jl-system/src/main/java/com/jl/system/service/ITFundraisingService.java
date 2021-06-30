package com.jl.system.service;

import com.jl.system.domain.TFundraising;
import com.jl.system.domain.vo.TFundraisingVo;
import com.jl.system.domain.bo.TFundraisingQueryBo;
import com.jl.system.domain.bo.TFundraisingAddBo;
import com.jl.system.domain.bo.TFundraisingEditBo;
import com.jl.common.core.page.IServicePlus;
import com.jl.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 募捐Service接口
 *
 * @author jl
 * @date 2021-05-21
 */
public interface ITFundraisingService extends IServicePlus<TFundraising> {
	/**
	 * 查询单个
	 * @return
	 */
	TFundraisingVo queryById(Long fdrId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFundraisingVo> queryPageList(TFundraisingQueryBo bo);
	/**
	 * 查询列表
	 */
	List<TFundraisingVo> queryList(TFundraisingQueryBo bo);

	/**
	 * 根据新增业务对象插入募捐
	 * @param bo 募捐新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFundraisingAddBo bo);

	/**
	 * 根据编辑业务对象修改募捐
	 * @param bo 募捐编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFundraisingEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

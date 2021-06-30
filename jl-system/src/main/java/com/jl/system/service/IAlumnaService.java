package com.jl.system.service;

import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.Alumna;
import com.jl.system.domain.bo.AlumnaAddBo;
import com.jl.system.domain.bo.AlumnaEditBo;
import com.jl.system.domain.bo.AlumnaQueryBo;
import com.jl.system.domain.vo.AlumnaVo;
import com.jl.common.core.page.IServicePlus;
import com.jl.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 校友管理Service接口
 *
 * @author jl
 * @date 2021-05-25
 */
public interface IAlumnaService extends IServicePlus<Alumna> {
	/**
	 * 查询单个
	 * @return
	 */
	AlumnaVo queryById(Long alumnaId);

	/**
	 * 查询列表
	 */
    TableDataInfo<AlumnaVo> queryPageList(AlumnaQueryBo bo);
	/**
	 * 查询列表
	 */
	List<AlumnaVo> queryList(AlumnaQueryBo bo);

	/**
	 * 根据新增业务对象插入校友管理
	 * @param bo 校友管理新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(AlumnaAddBo bo);

	/**
	 * 根据编辑业务对象修改校友管理
	 * @param bo 校友管理编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(AlumnaEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

	/**
	 * 更新校友
	 * @param alumna
	 * @return
	 */
	Boolean updateAlumna(Alumna alumna);
}

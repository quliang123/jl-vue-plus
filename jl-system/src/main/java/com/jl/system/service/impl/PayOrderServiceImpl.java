package com.jl.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.PayOrder;
import com.jl.system.mapper.PayOrderMapper;
import com.jl.system.service.IPayOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付订单表 服务实现类
 * </p>
 *
 * @author liang qu
 * @since 2021-06-11
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements IPayOrderService {

    /**
     * 保存订单
     *
     * @param payOrder
     * @return
     */
    @Override
    public AjaxResult createPayOrder(PayOrder payOrder) {
        return AjaxResult.success(baseMapper.insert(payOrder));
    }

    /**
     * 根据订单号来查询订单
     *
     * @param orderId
     * @return
     */
    @Override
    public AjaxResult selectPayOrderByOrderId(Long orderId) {
    return AjaxResult.success(baseMapper.selectOne(new QueryWrapper<PayOrder>().eq("order_id", orderId)));
    }

    /**
     * 更新订单信息
     * @param payOrder
     * @return
     */
    @Override
    public AjaxResult updatePayOrder(PayOrder payOrder) {
        return AjaxResult.success(baseMapper.updateById(payOrder));
    }
}

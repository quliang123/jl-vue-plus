package com.jl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jl.common.core.domain.AjaxResult;
import com.jl.system.domain.PayOrder;

/**
 * <p>
 * 支付订单表 服务类
 * </p>
 *
 * @author liang qu
 * @since 2021-06-11
 */
public interface IPayOrderService extends IService<PayOrder> {
    /**
     * 创建订单
     * @param payOrder
     * @return
     */
    AjaxResult createPayOrder(PayOrder payOrder);

    /**
     *根据订单号查询订单
     * @param orderId
     * @return
     */
    AjaxResult selectPayOrderByOrderId(Long orderId);

    /**
     * 更新订单信息
     * @param payOrder
     * @return
     */
    AjaxResult updatePayOrder(PayOrder payOrder);
}

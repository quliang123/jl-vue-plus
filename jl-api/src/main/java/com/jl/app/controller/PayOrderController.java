package com.jl.app.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 支付订单表 前端控制器
 * </p>
 *
 * @author liang qu
 * @since 2021-06-11
 */
@RestController
@Api(value = "支付订单管理小程序控制器", tags = {"支付订单管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/miniapp/pay")
public class PayOrderController {

}

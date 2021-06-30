package com.jl.app.config.pay;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @program: weixin-java-pay
 * @description:
 * @author: liang qu
 * @create: 2021-06-10 18:46
 **/
@Configuration
public class WxPay {

  @Autowired
  private WxPayProperties properties;

  public WxPayUnifiedOrderRequest setWxPayConfig(WxPayUnifiedOrderRequest request) {
    request.setAppid(this.properties.getAppId());
    request.setMchId(this.properties.getMchId());
    request.setSubAppId(this.properties.getSubAppId());
    request.setSubMchId(this.properties.getSubMchId());
    request.setNotifyUrl(this.properties.getNotifyUrl());
    return request;
  }
}

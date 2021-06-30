package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付订单表
 * </p>
 *
 * @author liang qu
 * @since 2021-06-11
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("payorder")
public class PayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流水号
     */
    @TableId("order_id")
    private Long orderId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    private String orderNo;
    /**
     * 支付金额,单位分
     */
    private Long amount;

    /**
     * 支付状态,0-订单生成,1-支付中,2-支付成功,3-业务处理完成,4-已退款
     */
    private Integer status;

    private String clientIp;

    /**
     * 设备
     */
    private String device;

    /**
     * 标题
     */
    private String subject;

    /**
     * 商品描述信息
     */
    private String body;

    /**
     * 支付宝账号、oppenId
     */
    private String channelUser;

    /**
     * 是否退款  0、未退款     1、退款
     */
    private String refundStatus;

    /**
     * 退款次数
     */
    private Integer refundTimes;

    /**
     * 退款金额
     */
    private Long refundAmount;

    /**
     * 回调地址url
     */
    private String notifyUrl;

    /**
     * 跳转地址url
     */
    private String returnUrl;

    /**
     * 失效时间
     */
    private Date expireTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    /**
     * 支付成功时间
     */
    private Date paySuccTime;

    /**
     *
     */
    public String openId;
}

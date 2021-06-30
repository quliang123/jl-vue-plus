package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 校友管理添加对象 alumna
 *
 * @author jl
 * @date 2021-05-25
 */
@Data
@ApiModel("订单传输对象")
public class TranOrder {
    /** 用户id */
    @ApiModelProperty("用户id")
    private Long userId;
    /** 活动id */
    @ApiModelProperty("活动id")
    private Long activityId;
    /** 校友id */
    @ApiModelProperty("校友id")
    private Long alumnaId;
    /** 基金会 */
    @ApiModelProperty("基金会id")
    private String foundationId;
    /** 护照编号 */
    @ApiModelProperty("护照编号")
    private String idfNo;
    /** 校友身份 */
    @ApiModelProperty("校友身份")
    private Integer identity;
    /** 校友名称 */
    @ApiModelProperty("校友名称")
    private String name;
    /** 校友性别 */
    @ApiModelProperty("校友性别")
    private Integer gender;
    /** 身份证号 */
    @ApiModelProperty("身份证号")
    private String idCard;
    /** 生日 */
    @ApiModelProperty("生日")
    private String birthday;
    /** 毕业年份 */
    @ApiModelProperty("毕业年份")
    private String graduateTime;
    /** 联系电话 */
    @ApiModelProperty("联系电话")
    private String mobile;
    /** 微信昵称 */
    @ApiModelProperty("微信昵称")
    private String nickName;
    /** 联系地址 */
    @ApiModelProperty("联系地址")
    private String address;
    /** 电子邮箱 */
    @ApiModelProperty("电子邮箱")
    private String email;



    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 修改时间 */
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createBy;
    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateBy;
    /**  总捐赠金额 */
    @ApiModelProperty("总捐赠金额")
    private String totalFee;
    /** 总捐赠次数   */
    @ApiModelProperty("总捐赠次数")
    private Integer totalDonate;

    @ApiModelProperty("商品描述")
    private String body;
}

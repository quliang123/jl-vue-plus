package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.jl.system.domain.vo.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.jl.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 校友管理对象 alumna
 * 
 * @author jl
 * @date 2021-05-25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("alumna")
public class Alumna implements Serializable {

    private static final long serialVersionUID=1L;


    /** 校友id */
    @TableId(value = "alumna_id")
    private Long alumnaId;
    /**
     * 基金会id
     */
    private Long foundationId;

    /** 护照编号 */
    private String idfNo;

    /** 校友身份 */
    private String identity;

    /** 校友名称 */
    private String name;

    /** 校友性别 */
    private Integer gender;

    /** 身份证号 */
    private String idCard;

    /** 生日 */
    private Date birthday;

    /** 毕业年份 */
    private Date graduateTime;

    /** 联系电话 */
    private String mobile;

    /** 微信昵称 */
    private String nickName;

    /** 联系地址 */
    private String address;

    /** 电子邮箱 */
    private String email;

    /** qq号 */
    private String qq;

    /** 所在行业 */
    private String industry;

    /** 是否工作 */
    private Integer itwork;

    /** 所在部门 */
    private String dept;

    /** 所属公司 */
    private String company;

    /** 就读大学 */
    private String university;

    /** 所处职务 */
    private String position;

    /** 所读专业 */
    private String profession;

    /** 学历 */
    private String education;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 修改人 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**  总捐赠金额 */
    private String totalAmount;
    /** 总捐赠次数   */
    private Integer totalDonate;

    @TableField(exist = false)
    private User user;
}

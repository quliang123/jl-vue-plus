package com.jl.system.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @program: jl-vue-plus
 * @description: 微信用户信息
 * @author: quliang
 * @create: 2021-06-09 21:44
 **/
@Data
@TableName("user")
@ApiModel(value = "微信用户信息对象", description = "微信用户信息表")
public class User implements UserDetails{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "开放id")
    private String openId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "性别  0:男 1:女")
    private String gender;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "证件类型 0:身份证 1:护照 2:居住证 3:临时居住证 4:其他证件")
    private Integer documentType;

    @ApiModelProperty(value = "证件号")
    private String idNo;

    @ApiModelProperty("付款方式")
    @TableField(exist = false)
    private Integer paymentAccountType;

    @ApiModelProperty("付款账号")
    @TableField(exist = false)
    private String paymentAccount;

    @ApiModelProperty(value = "证件正面照")
    private String documentPositive;

    @ApiModelProperty(value = "证件反面照")
    private String documentReverse;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;


    @ApiModelProperty("微信开放id 共用")
    private String unionId;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("微信号")
    private String vxNumber;

    @ApiModelProperty("祖籍")
    private String home;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("认证(0未认证 1已认证)")
    private Integer identification;

    @ApiModelProperty("城市")
    private String city;

    @TableField(exist = false)
    private String sessionKey;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

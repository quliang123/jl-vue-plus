package com.jl.app.model;


import com.jl.system.domain.vo.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author quliang
 */
@Data
public class MinAppLoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String openId;
    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户名id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipAddr;

    /**
     * 服务有效时间
     */
    private Long validTime;


    private String sessionKey;
    /**
     * 用户信息
     */
    private User user;


}

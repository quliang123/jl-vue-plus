package com.jl.app.config.security.service;

import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSONObject;
import com.jl.app.model.MinAppLoginUser;
import com.jl.app.service.MinAppTokenService;
import com.jl.app.service.RedisService;
import com.jl.common.core.domain.entity.SysUser;
import com.jl.common.core.domain.model.LoginUser;
import com.jl.common.enums.UserStatus;
import com.jl.common.exception.BaseException;
import com.jl.framework.web.service.SysPermissionService;
import com.jl.system.domain.vo.User;
import com.jl.system.service.ISysUserService;
import com.jl.system.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author jl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private MinAppTokenService minAppTokenService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisService redisService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        log.info("用户请求参数 {}", username);

        User user = iUserService.findByOpenId(username);
        if (Validator.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        return user;
    }

}

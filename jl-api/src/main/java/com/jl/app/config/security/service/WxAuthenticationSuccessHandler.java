package com.jl.app.config.security.service;

import cn.hutool.core.lang.Validator;
import com.jl.app.service.MinAppTokenService;
import com.jl.app.service.RedisService;
import com.jl.common.utils.DateUtils;
import com.jl.system.domain.vo.User;
import com.jl.system.service.ISysUserService;
import com.jl.system.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户验证处理
 *
 * @author jl
 */
@Service
public class WxAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    private static final Logger log = LoggerFactory.getLogger(WxAuthenticationSuccessHandler.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private MinAppTokenService minAppTokenService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisService redisService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

    }


}

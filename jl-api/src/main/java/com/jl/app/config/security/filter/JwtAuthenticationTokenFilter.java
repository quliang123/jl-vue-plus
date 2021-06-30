package com.jl.app.config.security.filter;

import cn.hutool.core.lang.Validator;
import com.jl.app.controller.WxPayController;
import com.jl.app.model.MinAppLoginUser;
import com.jl.app.service.MinAppTokenService;
import com.jl.common.core.domain.model.LoginUser;
import com.jl.common.utils.SecurityUtils;
import com.jl.common.utils.StringUtils;
import com.jl.framework.web.service.TokenService;
import com.jl.system.domain.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * token过滤器 验证token有效性
 *
 * @author jl
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private MinAppTokenService minAppTokenService;

    @Autowired
    private UserDetailsService userDetailsService;


    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.length()!=0) {
            MinAppLoginUser user = minAppTokenService.validateToken(token);
            if (user != null) {
                User currentUser = user.getUser();
                if (currentUser != null) {
                    String openId = currentUser.getOpenId();

                    UserDetails userDetails = userDetailsService.loadUserByUsername(openId);
                    if (Validator.isNotNull(userDetails)) {
                        //minAppTokenService.refreshToken(user);
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(currentUser, null,new ArrayList<>());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}

package com.jl.app.service;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jl.app.controller.WxPayController;
import com.jl.app.model.MinAppLoginUser;
import com.jl.common.constant.CacheConstants;
import com.jl.common.constant.Constants;

import com.jl.common.core.domain.AjaxResult;
import com.jl.common.core.domain.entity.SysUser;
import com.jl.common.utils.IdUtils;
import com.jl.common.utils.SecurityUtils;
import com.jl.common.utils.ServletUtils;
import com.jl.common.utils.StringUtils;
import com.jl.common.utils.ip.IpUtils;
import com.jl.system.domain.vo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author quliang
 */
@Component
public class MinAppTokenService {

    @Autowired
    private RedisService redisService;

    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    protected static final long MILLIS_SECOND = 1000;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_MCHID = "id";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private Long expiration;

    private static final Logger logger = LoggerFactory.getLogger(MinAppTokenService.class);

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(MinAppLoginUser loginUser) {
        // 生成token
        String token = generateToken(loginUser);
        loginUser.setToken(token);
        loginUser.setUserId(loginUser.getUserId());
        loginUser.setUserName(loginUser.getUser().getOpenId());
        loginUser.setIpAddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginUser);

        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("access_token", token);
        map.put("expires_in", EXPIRE_TIME);
        map.put("sessionkey", loginUser.getSessionKey());
        //map.put("openid", loginUser.getUser().getOpenId());
        redisService.setCacheObject(ACCESS_TOKEN + token, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
        return map;
    }


    public String generateToken(MinAppLoginUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        /**
         * CLAIM_KEY_USERNAME   暂时未存任何东西
         */
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUserName());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_MCHID, userDetails.getUser().getUserId());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public User getUserByToken(String token) {
        Object cacheObject = redisService.getCacheObject(ACCESS_TOKEN + token);
        logger.info("cacheObject {}", cacheObject);
        try {
            Map<String, Object> map = objectToMap(cacheObject);
            User user = (User) map.get("user");
            return user;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSON.parseObject(cacheObject.toString());

        User user = (User) jsonObject.get("user");
        return user;
    }

    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        logger.info("map {}", map.toString());
        return map;
    }
    public String generateToken(String openid) {
        Map<String, Object> claims = Maps.newHashMap();
        return doGenerateToken(claims, openid);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = DateTime.now();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public SysUser getLoginUser() {
        return getLoginUser(ServletUtils.getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public SysUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            SysUser user = redisService.getCacheObject(userKey);
            return user;
        }
        return null;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 通过
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(MinAppLoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisService.deleteObject(userKey);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(MinAppLoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + EXPIRE_TIME * MILLIS_SECOND);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 验证用户是否存在
     *
     * @param token
     * @return
     */
    public MinAppLoginUser validateToken(String token) {
        Object cacheObject = redisService.getCacheObject(CacheConstants.LOGIN_TOKEN_KEY + token);
        if (Objects.equals(cacheObject, null)) {
            return null;
        }
        String user = JSONObject.toJSONString(cacheObject);
        MinAppLoginUser parse = JSONObject.parseObject(user, MinAppLoginUser.class);
        return Objects.equals(parse, null) ? null : parse;
    }

    public User parseUser(String token) {
        MinAppLoginUser minAppLoginUser = validateToken(token);
        return Objects.equals(minAppLoginUser.getUser(), null) ? null : minAppLoginUser.getUser();
    }


    private String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }
}

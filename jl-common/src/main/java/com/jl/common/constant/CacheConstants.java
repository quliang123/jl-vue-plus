package com.jl.common.constant;

/**
 * 缓存的key 常量
 * 
 * @author jl
 */
public class CacheConstants
{
    /**
     * 令牌自定义标识
     */
    public static final String HEADER = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";




    /**
     * 商户权限缓存前缀
     */
    public final static String MERCHANTS_LOGIN_TOKEN_KEY = "merchants_login_tokens:";

    /**
     * 用户ID字段
     */
    public static final String DETAILS_USER_ID = "user_id";

    /**
     * 商户ID字段
     */
    public static final String MER_ID = "mer_id";

    /**
     * 商户服务有效期字段
     */
    public static final String VALID_TIME = "valid_time";

    /**
     * 用户名字段
     */
    public static final String DETAILS_USERNAME = "username";

    /**
     * 授权信息字段
     */
    public static final String AUTHORIZATION_HEADER = "authorization";
}

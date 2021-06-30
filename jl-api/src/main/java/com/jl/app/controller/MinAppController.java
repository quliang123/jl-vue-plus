package com.jl.app.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jl.app.config.miniapp.WxMaConfiguration;
import com.jl.app.config.miniapp.WxMaProperties;
import com.jl.app.model.MinAppLoginUser;
import com.jl.app.service.MinAppService;
import com.jl.app.service.MinAppTokenService;
import com.jl.app.service.RedisService;
import com.jl.common.constant.CacheConstants;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.utils.DateUtils;
import com.jl.system.domain.vo.User;
import com.jl.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author 123
 */
@Api(value = "小程序授权控制器", tags = {"小程序授权"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
public class MinAppController {


    private final MinAppService minAppService;

    private final MinAppTokenService minAppTokenService;

    private final IUserService userService;

    @Value("${wx.miniapp.configs[0].appid}")
    private String appId;


    private final RedisService redisService;
    private static final Logger logger = LoggerFactory.getLogger(MinAppController.class);

    @GetMapping("/getJSON")
    public AjaxResult getJSON(String token) {
        MinAppLoginUser minAppLoginUser = minAppTokenService.validateToken(token);
        return AjaxResult.success(minAppLoginUser.getUser());
    }

    /**
     * 小程序授权
     * @param code
     * @return
     */
    @ApiOperation("小程序授权")
    @PostMapping("loginByMinApp")
    public AjaxResult<?> loginByMinApp(@RequestParam String code) {
        MinAppLoginUser login = minAppService.login(code);
        // 获取登录token
        return AjaxResult.success(minAppTokenService.createToken(login));
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @ApiOperation("获取用户信息接口")
    @GetMapping("/applet/info")
    public AjaxResult info(String sessionKey,
                           String signature, String rawData, String encryptedData, String iv, HttpServletRequest request) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appId);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return AjaxResult.error("用户校验失败");
        }
        // 解密用户信息
        String token = request.getHeader("Authorization");

        User user = minAppTokenService.getUserByToken(token);
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

       User currentUser= minAppService.updateUser(user, userInfo);
        if (Objects.equals(currentUser,null)) {
            return AjaxResult.error("信息同步失败，请重新尝试");
        }
        return AjaxResult.success(currentUser);
    }


    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @ApiOperation("获取用户绑定手机号信息")
    @GetMapping("/applet/phone")
    public AjaxResult phone(String sessionKey, String encryptedData, String iv, HttpServletRequest request) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appId);
        // 用户信息校验
//        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return R.error("用户信息校验失败");
//        }
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        String token = request.getHeader("Authorization");

        User user = minAppTokenService.getUserByToken(token);

        String phoneNumber = phoneNoInfo.getPhoneNumber();
        user.setMobile(phoneNumber);
        user.setUpdateTime(DateUtils.getNowDate());
        int i = userService.bindMobile(user);
        if (i == 0) {
            return AjaxResult.error("信息同步失败，请重新尝试");
        }
        return AjaxResult.success(phoneNoInfo);
    }

}

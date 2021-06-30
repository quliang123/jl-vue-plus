package com.jl.app.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.jl.app.config.miniapp.WxMaConfiguration;
import com.jl.common.core.domain.AjaxResult;
import com.jl.common.core.domain.model.WxLoginForm;
import com.jl.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

/**
 * @author 123
 * @ClassName 登陆授权
 * @Date 2019/6/27
 **/
//@RequestMapping(value = "/")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Api(value = "认证授权模块", tags = "认证授权模块", description = "认证授权模块")
public class AuthController {
    private final WxMaService wxService;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    /**
     * 小程序登陆，颁发token，暂时模拟openid
     *
     * @return token字符串
     */
    @PostMapping("/oauth/access_token")
    //@ApiOperation(value = "获取token", notes = "获取token")
    public AjaxResult loginReturnToken(@Validated @RequestBody WxLoginForm wxLoginForm) {
        Boolean isProduct = false; //true 开启真实小程序环境
        String openid = "orIMY4xGhMmipwFZoSL1vOhUNFZ0";
        WxMaUserInfo userInfo = null;
        try {
            if (isProduct) {
                WxMaJscode2SessionResult session = null;

                session = wxService.getUserService()
                        .getSessionInfo(wxLoginForm.getCode());

                //log.info(session.getSessionKey());
                //log.info(session.getOpenid());
                //TODO 可以增加自己的逻辑，关联业务相关数据

                String sessionKey = session.getSessionKey();
                openid = session.getOpenid();
                // 解密用户信息
                userInfo = wxService.getUserService()
                        .getUserInfo(sessionKey, wxLoginForm.getEncryptedData(), wxLoginForm.getIv());
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 小程序登陆，颁发token
     *
     * @return token字符串
     */
    @PostMapping("/oauth/login")
    //@ApiOperation(value = "小程序登录", notes = "获取token")
    public AjaxResult login(@Validated @RequestBody WxLoginForm wxLoginForm) {
        if (StringUtils.isNotBlank(wxLoginForm.getAppId()) && StringUtils.isNotBlank(wxLoginForm.getCode())) {
            return AjaxResult.error(" Plase check appId or code is empty");
        }

        WxMaService maService = WxMaConfiguration.getMaService(wxLoginForm.getAppId());
        try {
            WxMaJscode2SessionResult sessionInfo = maService.getUserService().getSessionInfo(wxLoginForm.getCode());
            log.info("openId {}  sessionKey {} unionId {}", sessionInfo.getOpenid(), sessionInfo.getSessionKey(), sessionInfo.getUnionid());

            //通过openId查询用户


            return AjaxResult.success(sessionInfo);
        } catch (WxErrorException exception) {
            exception.printStackTrace();
            return AjaxResult.error(exception.getMessage());
        }
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @PostMapping("/oauth/info")
//    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public AjaxResult getInfo(@Validated @RequestBody WxLoginForm wxLoginForm) {
        WxMaService maService = WxMaConfiguration.getMaService(wxLoginForm.getAppId());
        if (maService.getUserService().checkUserInfo(wxLoginForm.getSessionKey(), wxLoginForm.getRawData(), wxLoginForm.getSignature())) {
            return AjaxResult.error("user check failed");
        }
        WxMaUserInfo userInfo = maService.getUserService().getUserInfo(wxLoginForm.getSessionKey(), wxLoginForm.getEncryptedData(), wxLoginForm.getIv());
        try {
            String decode = URLDecoder.decode(wxLoginForm.getEncryptedData(), "UTF-8");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return AjaxResult.success(userInfo);
    }


    /**
     * 获取用户绑定手机号信息
     *
     * @return
     */
    @PostMapping("/oauth/getPhoneNoInfo")
//    @ApiOperation(value = "获取用户绑定手机号信息", notes = "获取用户绑定手机号信息")
    public AjaxResult getPhoneNoInfo(@Validated @RequestBody WxLoginForm wxLoginForm) {
        WxMaService maService = WxMaConfiguration.getMaService(wxLoginForm.getAppId());
        if (maService.getUserService().checkUserInfo(wxLoginForm.getSessionKey(), wxLoginForm.getRawData(), wxLoginForm.getSignature())) {
            return AjaxResult.error("user check failed");
        }
        WxMaPhoneNumberInfo phoneNoInfo = maService.getUserService().getPhoneNoInfo(wxLoginForm.getSessionKey(), wxLoginForm.getEncryptedData(), wxLoginForm.getIv());
        return AjaxResult.success(phoneNoInfo);
    }
}

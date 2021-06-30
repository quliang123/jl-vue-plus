package com.jl.app.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.jl.app.config.miniapp.WxMaConfiguration;
import com.jl.app.controller.WxPayController;
import com.jl.app.model.MinAppLoginUser;
import com.jl.common.exception.user.UserException;
import com.jl.common.utils.DateUtils;
import com.jl.system.domain.vo.User;
import com.jl.system.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class MinAppService {

    @Autowired
    private IUserService userService;

    @Value("${wx.miniapp.configs[0].appid}")
    private String appId;

    private static final Logger logger = LoggerFactory.getLogger(MinAppService.class);

    /**
     * 1、通过 wx.login() 获取到用户的code
     * 2、通过 wx.request() 方法请求我们自己的后端，我们自己的服务端把 appid , appsecret 和 code 一起发送到微信服务器。 appid 和 appsecret 都是微信提供的，可以在管理员后台找到
     * 3、微信服务器返回了 openid
     * 4、我们在自己的数据库中，查找 openid ，如果没有查到记录，说明该用户没有注册，如果有记录，则继续往下走
     * 5、我们生成一个第三方 session , 也就是 session_id , 也就是用户唯一标识符。在 redis 中，把 session_id 和用户的身份存进去。
     * 6、返回 3rd_session
     * 7、小程序把 3rd_session 存到 storage 里面
     * 8、下次请求时，先从 storage 里面读取，然后带给服务端
     * 9、服务端从 redis 里面找到 3rd_session 对应的记录，然后校验有效期
     * 注意：上面提到的 3rd_session 只是一种实现方式，可以自定义登录态。
     *
     * 登录
     */
    public MinAppLoginUser login(String code) {
        User user = null;
        WxMaJscode2SessionResult session = null;
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appId);
        try {
            //获取微信用户session
            session = wxMaService.getUserService().getSessionInfo(code);
            String openId = session.getOpenid();

            //如果没有查到记录，说明该用户没有注册
            user = userService.findByOpenId(openId);
            //未注册
            if (null == user) {
                //创建用户进行注册
                User createUser = new User();
                createUser.setOpenId(openId);
                createUser.setCreateTime(DateUtils.getNowDate());
                createUser.setUnionId(session.getUnionid());

                //保存用户
                user = userService.addUser(createUser);
                if (null == user) {
                    throw new UserException("4004", null);
                }
            }

        } catch (Exception e) {
            logger.error("获取用户信息错误{}", e.getMessage());
            return null;
        }
        MinAppLoginUser loginUser = new MinAppLoginUser();
        loginUser.setUser(user);
        loginUser.setUserName(user.getOpenId());
        loginUser.setSessionKey(session.getSessionKey());
        return loginUser;
    }


    public User updateUser(User user, WxMaUserInfo userInfo) {
        user.setCity(userInfo.getCity());
        user.setCountry(userInfo.getCountry());
        user.setGender(userInfo.getGender());
        user.setProvince(userInfo.getProvince());
        user.setNickName(userInfo.getNickName());
        user.setAvatar(userInfo.getAvatarUrl());
        return userService.updateUser(user);
    }

    public int updateUserMobile(User user, WxMaPhoneNumberInfo phoneNoInfo) {
//        tenant.setMobile(CodeUtils.phoneMask(phoneNoInfo.getPhoneNumber()));
       /* UserPrivacy userPrivacy = userPrivacyService.getByUserId(user.getUserId());
        if(userPrivacy!=null){
            userPrivacy.setMobile(phoneNoInfo.getPhoneNumber());
            userPrivacyService.editSave(userPrivacy);
        }*/
//        tenant.setUpdateTime(com.jiuling.common.utils.DateUtils.getNowDate());
        return 0;
    }
}

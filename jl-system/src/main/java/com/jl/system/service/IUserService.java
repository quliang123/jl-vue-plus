package com.jl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
 import com.jl.system.domain.vo.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author quliang
 * @since 2020-11-03
 */
public interface IUserService extends IService<User> {

    /**
     *根据openID查询微信用户
     *
     */
    User findByOpenId(String openId);

    /**
     *添加微信用户
     *
     */
    User addUser(User user);

    /**
     *
     * 绑定用户信息手机号
     *
     */
    int bindMobile(User user);

    /**
     *修改用户信息
     *
     */
    User updateUser(User user);

    /**
     *根据用户信息id查询用户信息信息
     *
     */
    User getByUserId(Long userId);

    /**
     *根据用户信息id查询用户信息信息
     *
     */
    User getUserById(Long alumnaId);
}

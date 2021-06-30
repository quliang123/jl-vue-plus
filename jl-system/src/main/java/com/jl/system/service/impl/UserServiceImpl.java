package com.jl.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.system.domain.vo.User;
import com.jl.system.mapper.UserMapper;
import com.jl.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByOpenId(String openId) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
    }

    @Override
    public User addUser(User user) {
        int insert = baseMapper.insert(user);
        if (insert > 0) {
            return baseMapper.selectOne(new QueryWrapper<User>().eq("open_id", user.getOpenId()));
        }
        return null;
    }

    @Override
    public int bindMobile(User user) {
        return baseMapper.updateById(user);
    }

    @Override
    public User updateUser(User user) {
        int i = baseMapper.updateById(user);
        if (i > 0) {
            return baseMapper.selectOne(new QueryWrapper<User>().eq("open_id", user.getOpenId()));
        }
        return null;
    }

    @Override
    public User getByUserId(Long userId) {
        return baseMapper.selectById(userId);
    }

    @Override
    public User getUserById(Long alumnaId) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("alumna_id", alumnaId));
    }
}

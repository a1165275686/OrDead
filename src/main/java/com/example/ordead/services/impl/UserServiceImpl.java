package com.example.ordead.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordead.mapper.UserMapper;
import com.example.ordead.model.entity.User;
import com.example.ordead.services.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User userLogin(User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String encryptPassword = DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes());

        queryWrapper.eq(User::getUserName,user.getUserName())
                .eq(User::getUserPassword,encryptPassword)
                .eq(User::getIsDelete,0);
        return userMapper.selectOne(queryWrapper);
    }
}


package com.example.ordead.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordead.model.entity.User;
import org.springframework.stereotype.Service;



public interface IUserService extends IService<User> {

    User userLogin(User user);
}

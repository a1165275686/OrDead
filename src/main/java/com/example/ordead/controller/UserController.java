package com.example.ordead.controller;

import com.example.ordead.common.BaseResponse;
import com.example.ordead.model.entity.User;
import com.example.ordead.model.request.user.UserRegisterRequest;
import com.example.ordead.services.IUserService;
import jakarta.annotation.Resource;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 用户控制器（处理用户注册、登录等相关接口）
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 用户注册接口
     * @param userRegisterRequest 前端传入的注册请求参数（@RequestBody接收JSON格式数据）
     * @return 统一响应结果 BaseResponse
     */
    @PostMapping("/register")
    public BaseResponse<User> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 1. 校验请求参数（基础非空、格式校验已通过注解完成，此处做业务逻辑校验）
        if (userRegisterRequest == null) {
            return BaseResponse.error(400, "请求参数不能为空");
        }
        String userName = userRegisterRequest.getUserName();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String phone = userRegisterRequest.getPhone();

        // 2. 密码一致性校验
        if (!userPassword.equals(checkPassword)) {
            return BaseResponse.error(400, "两次输入的密码不一致");
        }

        // 3. 模拟用户业务处理（实际项目中需调用Service层，此处简化实现）
        // 3.1 密码加密（使用MD5加密，实际项目推荐使用BCrypt等更安全的加密方式）
        String encryptPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());

        // 3.2 构建User实体对象（封装用户注册信息）
        User user = new User();
        user.setName(userRegisterRequest.getName());
        user.setUserName(userName);
        user.setUserPassword(encryptPassword);
        user.setPhone(phone);
        user.setStatus(0); // 默认正常状态
        user.setIsDelete(0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 3.3 模拟数据库插入操作（实际项目中需调用Mapper/Repository层持久化数据）
        userService.save(user);

        // 4. 返回注册成功结果（返回用户核心信息，隐藏密码等敏感数据）
        User resultUser = new User();
        resultUser.setId(1L); // 模拟数据库自增主键ID
        resultUser.setUserName(userName);
        resultUser.setPhone(phone);
        resultUser.setCreateTime(user.getCreateTime());

        return BaseResponse.success(resultUser);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody User user) {
        if(ObjectUtils.isEmpty(user)){
            return BaseResponse.error(400, "请求参数不能为空");
        }
        return BaseResponse.success(userService.userLogin(user));

    }
}
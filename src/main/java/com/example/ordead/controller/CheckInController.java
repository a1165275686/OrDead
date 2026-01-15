package com.example.ordead.controller;

import com.example.ordead.common.BaseResponse;
import com.example.ordead.model.entity.CheckEntity;
import com.example.ordead.model.request.check.CheckRequest;
import com.example.ordead.services.ICheckService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作控制器
 * 用于处理签到相关的HTTP请求
 */
@RestController
@RequestMapping("/operation")
public class CheckInController {

    /**
     * 签到服务接口
     * 用于处理签到相关的业务逻辑
     */
    @Resource
    private ICheckService checkInService;

    /**
     * 签到接口
     * 处理签到请求，接收签到实体数据
     *
     * @param request 签到实体数据，包含签到所需信息
     * @return BaseResponse<String> 返回处理结果，成功时返回签到信息，失败时返回错误信息
     */
    @RequestMapping("/checkIn")
    public BaseResponse<String> checkIn(@RequestBody CheckRequest request) {

        // 参数校验：检查签到实体是否为空
        if (ObjectUtils.isEmpty(request)) {
            return BaseResponse.error(400, "参数不能为空");
        }
        // 调用服务层处理签到逻辑
        return checkInService.checkIn(request);
    }
}

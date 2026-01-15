package com.example.ordead.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordead.common.BaseResponse;
import com.example.ordead.model.entity.CheckEntity;
import com.example.ordead.model.request.check.CheckRequest;

public interface ICheckService extends IService<CheckEntity> {
    BaseResponse<String> checkIn(CheckRequest entity);
}

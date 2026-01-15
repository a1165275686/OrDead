package com.example.ordead.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordead.common.BaseResponse;
import com.example.ordead.mapper.CheckEntityMapper;
import com.example.ordead.model.entity.CheckEntity;
import com.example.ordead.model.request.check.CheckRequest;
import com.example.ordead.services.ICheckService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Slf4j
@Service
public class ICheckServiceImpl extends ServiceImpl<CheckEntityMapper, CheckEntity> implements ICheckService {
    // 常量定义优化：增加语义化注释，统一管理
    private static final Integer NOT_DELETED = 0;
    private static final Boolean CHECKED_IN = Boolean.TRUE;
    private static final Boolean NOT_CHECKED_IN = Boolean.FALSE;
    // 响应信息常量
    private static final String MSG_CHECK_SUCCESS = "签到成功";
    private static final String MSG_CHECK_REPEAT = "今日已签到，无需重复签到";
    private static final String MSG_CHECK_FAIL = "签到失败";
    private static final String MSG_PARAM_INVALID = "参数无效：用户名或姓名不能为空";

    /**
     * 签到接口方法
     *
     * @param checkEntity 签到请求参数（包含userName/name等核心字段）
     * @return 统一响应结果BaseResponse
     */
    @Override
    public BaseResponse<String> checkIn(CheckRequest checkEntity) {
        // 1. 入参合法性校验（核心：防止空指针和无效请求）
        if (Objects.isNull(checkEntity) || !StringUtils.hasText(checkEntity.getUserName())){
            log.warn("签到请求参数无效：{}", checkEntity);
            return BaseResponse.error(MSG_PARAM_INVALID);
        }
        try {
            String userName = checkEntity.getUserName();
            LocalDate today = LocalDate.now();
            // 构建当日时间范围：00:00:00 ~ 23:59:59.999
            LocalDateTime startTime = LocalDateTime.of(today, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(today, LocalTime.MAX);

            // 查询用户当天的有效签到记录（复用MP的lambda查询）
            LambdaQueryWrapper<CheckEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(CheckEntity::getUserName, userName)
                    .eq(CheckEntity::getIsDelete, NOT_DELETED)
                    .between(CheckEntity::getCheckTime, startTime, endTime);
            CheckEntity todayCheckRecord = this.getOne(queryWrapper);

            // 重复签到判断：已有已签到记录则直接返回
            if (Objects.nonNull(todayCheckRecord) && CHECKED_IN.equals(todayCheckRecord.getCheckStatus())) {
                log.info("用户【({})】重复签到：今日已完成签到", userName);
                return BaseResponse.success(MSG_CHECK_REPEAT);
            }

            // 执行签到操作（更新或新增）
            LocalDateTime now = LocalDateTime.now();
            if (Objects.nonNull(todayCheckRecord)) {
                // 场景1：存在当日未签到记录 → 更新状态和时间
                todayCheckRecord.setCheckStatus(CHECKED_IN);
                todayCheckRecord.setCheckTime(now);
                this.updateById(todayCheckRecord); // 复用ServiceImpl的updateById方法
                log.info("用户【({})】签到成功：更新当日未签到记录为已签到", userName);
            } else {
                // 场景2：无当日记录 → 新增签到记录
                CheckEntity newCheckRecord = new CheckEntity();
                newCheckRecord.setUserName(userName);
                newCheckRecord.setCheckStatus(CHECKED_IN);
                newCheckRecord.setCheckTime(now);
                newCheckRecord.setIsDelete(NOT_DELETED);
                this.save(newCheckRecord); // 复用ServiceImpl的save方法
                log.info("用户【{}】签到成功：新增当日签到记录", userName);
            }

            // 签到成功响应
            return BaseResponse.success(MSG_CHECK_SUCCESS);

        } catch (Exception e) {
            // 全局异常捕获：记录详细日志+返回错误响应
            log.error("用户【{}】签到失败：", checkEntity.getUserName(), e);
            return BaseResponse.error(MSG_CHECK_FAIL + "：" + e.getMessage());
        }
    }
}

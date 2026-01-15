package com.example.ordead.model.request.check;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckRequest {

    private String userName;

    // 签到状态
    private Boolean checkStatus;

    // 签到时间
    private LocalDateTime checkTime;
}

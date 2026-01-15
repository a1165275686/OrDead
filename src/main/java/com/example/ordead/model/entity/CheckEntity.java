package com.example.ordead.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 检查实体类
 * 用于存储检查相关的数据信息
 */
@Data
@TableName("t_check")
public class CheckEntity {

    // 主键ID
    private Long id;

    // 名称
    private String name;

    // 用户名
    private String userName;

    // 签到状态
    private Boolean checkStatus;

    // 签到时间
    private LocalDateTime checkTime;

    // 是否删除标记（0：未删除，1：已删除）
    private Integer isDelete;
}

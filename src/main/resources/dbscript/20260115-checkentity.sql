CREATE TABLE `t_check` (
                           `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
                           `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名（唯一标识用户，如账号/工号）',
                           `check_status` tinyint(1) DEFAULT '0' COMMENT '签到状态：0-未签到，1-已签到',
                           `check_time` datetime DEFAULT NULL COMMENT '签到时间',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（扩展字段，建议添加）',
                           `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（扩展字段，建议添加）',
                           `is_delete` tinyint(1) DEFAULT '0' COMMENT '删除标记：0-未删除，1-已删除',
                           PRIMARY KEY (`id`) USING BTREE,
                           KEY `idx_user_name_check_time` (`user_name`,`check_time`,`is_delete`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='签到记录表';
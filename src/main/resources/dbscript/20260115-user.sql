CREATE TABLE `user` (
                        `id` bigint NOT NULL COMMENT '用户唯一标识（雪花算法生成）',
                        `name` varchar(50) NOT NULL COMMENT '用户昵称',
                        `user_name` varchar(50) NOT NULL COMMENT '用户id名（唯一）',
                        `user_password` varchar(100) NOT NULL COMMENT '加密后的密码（不可存储明文）',
                        `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
                        `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                        `status` tinyint DEFAULT '0' COMMENT '用户状态（0：正常，1：禁用）',
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（用户注册时间）',
                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（用户信息修改时间）',
                        `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标识（0：未删除，1：已删除）',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户核心表';
package com.example.ordead;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ordead.mapper")
public class OrDeadApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrDeadApplication.class, args);
    }

}

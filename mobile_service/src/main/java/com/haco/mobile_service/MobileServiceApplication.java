package com.haco.mobile_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("com.haco.mobile_service.DAO")
public class MobileServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobileServiceApplication.class, args);
    }
}

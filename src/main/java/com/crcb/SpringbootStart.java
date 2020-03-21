package com.crcb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Classname SpringbootStart
 * @Description 启动类
 * @Date 2020/3/17 17:06
 * @Created by gangye
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.crcb.mapper")
public class SpringbootStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStart.class,args);
    }
}

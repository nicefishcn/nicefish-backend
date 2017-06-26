package com.nicefish.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages={
        "com.nicefish.config","com.nicefish.common","com.nicefish.service",
        "com.nicefish.message","com.nicefish.swagger","com.nicefish.web"})
@RestController
@MapperScan(basePackages = {"com.nicefish.mapper"})
public class NicefishApplication {

    @RequestMapping("/")
    public String greeting() {

        return "Hello World,kimmking!";
    }

    public static void main(String[] args) {

        SpringApplication.run(NicefishApplication.class, args);
    }
}

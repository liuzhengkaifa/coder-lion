package com.coder.lion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.coder.lion.demo.mapper")
public class CoderLionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoderLionApplication.class, args);
    }

}

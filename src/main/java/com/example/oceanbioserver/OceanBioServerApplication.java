package com.example.oceanbioserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.oceanbioserver.mapper")
public class OceanBioServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OceanBioServerApplication.class, args);
    }

}

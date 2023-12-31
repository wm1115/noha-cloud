package com.noha.datasource;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
@MapperScan("com.noha.datasource.mapper")
public class DatasourceApplication {



    public static void main(String[] args) {
        SpringApplication.run(DatasourceApplication.class, args);
    }

}

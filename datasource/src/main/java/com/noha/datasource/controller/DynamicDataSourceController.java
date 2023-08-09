package com.noha.datasource.controller;


import com.noha.datasource.annotation.TargetDataSource;
import com.noha.datasource.dysource.DynamicDataSource;
import com.noha.datasource.mapper.Dao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ds/")
@Slf4j
public class DynamicDataSourceController {



    @Autowired
    private Dao dao;


    @Autowired
    private DynamicDataSource dataSource;

    @RequestMapping("testSwitch1")
    @TargetDataSource(name = "first")
    public Integer testSwitch(){

        Integer num = dao.getNum();
        log.info("值为{}",num);
        return num;

    }
}

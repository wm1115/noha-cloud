package com.noha.datasource.dysource;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Component
@Slf4j
public class DataSourceUtil {

    private static DynamicDataSource dynamicDataSource;



    @Autowired
    public DataSourceUtil(DynamicDataSource dynamicDataSource) {
        this.dynamicDataSource = dynamicDataSource;
    }

    public static void addDataSource(String key, DataSource dataSource) {
        dynamicDataSource.addDataSource(key, dataSource);
        log.info("已添加数据源{}",key);
    }

}

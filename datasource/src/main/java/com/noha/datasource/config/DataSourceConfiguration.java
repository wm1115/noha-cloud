package com.noha.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.noha.datasource.dysource.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DataSourceConfiguration {
    public DataSourceConfiguration() {
    }


    /**
     * 初始化主数据源
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource defaultDataSource() {
        return new DruidDataSource();
    }



    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    /**
     * 初始化DynamicDataSource，将主数据源写入targetDataSources
     *
     * @param defaultDataSource
     * @return
     */
    @Bean
    public DynamicDataSource dataSource(DataSource defaultDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(1);
        targetDataSources.put("defaultDataSource", defaultDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        return dynamicDataSource;
    }

}

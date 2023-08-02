package com.noha.datasource.dysource;

import javax.sql.DataSource;

public class DataSourceUtil {

    public static DynamicDataSource dynamicDataSource;

    public DataSourceUtil() {
    }

    public static void addDataSource(String key, DataSource dataSource) {
        dynamicDataSource.addDataSource(key, dataSource);
    }

}

package com.noha.datasource.dysource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.lang.reflect.Field;
import java.util.Map;


public class DynamicDataSource extends AbstractRoutingDataSource {



    @Override
    protected Object determineCurrentLookupKey() {
        return DyContextHolder.getDataSource();
    }


    /**
     * spring容器初始化后,向AbstractRoutingDataSource添加数据源
     *
     * @param key
     * @param dataSource
     */
    public void addDataSource(String key, Object dataSource) {

        Map<Object, Object> targetDataSources = (Map<Object, Object>) getTargetDataSources(this);
        targetDataSources.put(key, dataSource);
        this.setTargetDataSources(targetDataSources);
        this.afterPropertiesSet();

    }

    /**
     * 通过反射获取AbstractRoutingDataSource中的动态数据源map
     *
     * @param object
     * @return
     */
    private static Object getTargetDataSources(Object object) {
        try {
            Field field = AbstractRoutingDataSource.class.getDeclaredField("targetDataSources");
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception var3) {
            throw new RuntimeException();
        }
    }


}

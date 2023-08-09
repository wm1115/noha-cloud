package com.noha.datasource.dysource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


@Slf4j
public class DyContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 使用该方法设置需要使用的数据源
     *
     * @param key
     */

    public static void setDataSourceKey(String key) {
        contextHolder.set(key);
        log.info("设置当前数据源为{}",key);

    }


    /**
     * 为determineCurrentLookupKey方法返回需要使用的数据源的key
     *
     * @return
     */
    public static String getDataSource() {
        String source = contextHolder.get();
        return StringUtils.isEmpty(source) ? "defaultDataSource" : source;
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }


}

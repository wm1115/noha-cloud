package com.noha.datasource.config;


import com.noha.datasource.dysource.DataSourceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class DataSourceInitListener implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private Environment environment;

    /**
     * 需要加载的数据源，可以从配置文件读取，也可以从主数据库中读取
     */
    private Map<String, DataSource> DataSources = new HashMap<>();


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            this.loadDataSource();
        }
    }


    /**
     * 遍历数据源，加载到DynamicDataSource对象中
     */
    private void loadDataSource() {
        loadDataSource2Map();
        DataSources.forEach(DataSourceUtil::addDataSource);
    }


    private void loadDataSource2Map() {
        String dataSourceNames = environment.getProperty("spring.datasource.druid.names");
        if (StringUtils.isNoneBlank(dataSourceNames)) {
            for (String dsPrefix : dataSourceNames.split(",")) {
                Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
                Binder binder = new Binder(sources);
                BindResult<Properties> bindResult = binder.bind("spring.datasource.druid." + dsPrefix, Properties.class);
                Properties properties = bindResult.get();
                Map<String, Object> dsMap = new HashMap<>();
                dsMap.put("type", properties.getProperty("type"));
                dsMap.put("driver-class-name", properties.getProperty("driverClassName"));
                dsMap.put("url", properties.getProperty("url"));
                dsMap.put("username", properties.getProperty("username"));
                dsMap.put("password", properties.getProperty("password"));
                DataSource ds = buildDataSource(dsMap);
                DataSources.put(dsPrefix, ds);
            }
        }
    }


    /**
     * 利用读取的配置创建数据源 *
     *
     * @param dsMap
     * @return
     */
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        Object type = dsMap.get("type");
        Class<? extends DataSource> dataSourceType;
        try {
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dsMap.get("driver-class-name").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            DataSource defaultDataSource = DataSourceBuilder.create().type(dataSourceType).driverClassName(driverClassName).url(url).username(username).password(password).build();
            return defaultDataSource;
        } catch (ClassNotFoundException e) {

        }
        return null;
    }

}

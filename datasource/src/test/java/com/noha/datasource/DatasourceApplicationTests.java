package com.noha.datasource;

import com.noha.datasource.mapper.Dao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatasourceApplicationTests {
    @Autowired(required=true)
    Dao dao;
    @Test
    void contextLoads() {
        dao.getNum();
    }

}

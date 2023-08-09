package com.noha.datasource.mapper;


import com.noha.datasource.annotation.TargetDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Dao {

//    @TargetDataSource(name = "first")
    @Select("select * from ds_table")
    @TargetDataSource(name = "second")
    Integer getNum();
}

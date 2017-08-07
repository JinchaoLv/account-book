package com.lvjc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lvjc on 2017/6/27.
 */
@Mapper
public interface DropTableDao {

    void dropTable(@Param("targetTable") String targetTable);
}

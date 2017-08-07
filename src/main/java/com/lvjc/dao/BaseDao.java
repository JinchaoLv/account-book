package com.lvjc.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lvjc on 2017/6/26.
 */
public interface BaseDao<T> {

    public void createTable(@Param("targetTable") String tableName);

    public void dropTable(@Param("targetTable") String tableName);

    public void addEntity(@Param("targetTable")String targetTable, @Param("entity") T entity);

    public void updateEntity(@Param("targetTable")String targetTable, @Param("entity")T entity);

    public void deleteEntity(@Param("targetTable") String targetTable, @Param("entity") T entity);

    public void deleteEntityById(@Param("targetTable") String targetTable, @Param("id") String id);

    public T getEntityById(@Param("targetTable") String targetTable, @Param("id") String id);

    public List<T> getAllEntities(@Param("targetTable") String targetTable);
}

package com.lvjc.support.incrementer.impl;

import com.lvjc.support.incrementer.IdGenerator;
import com.lvjc.support.incrementer.PersistentObjectInfo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvjc on 2017/6/20.
 */
public abstract class AbstractIdGenerator implements IdGenerator {

    /**
     * 使用前的属性配置
     *
     * dataSource 数据源（必需）
     * incrementerTableName 序列表名（必需）
     * cacheSize 缓存容量（可选，默认10）
     * strategyMap 表名相应的id生成策略（可选）
     */
    protected DataSource dataSource;
    protected String sequenceTableName;
    protected int cacheSize = 10;
    protected Map<Class, IdGenerationStrategy> strategyMap;


    /**
     * 实现接口
     */
    @Override
    public int nextIntId(PersistentObjectInfo info) throws Exception{
        return (int)nextKeyValue(info);
    }

    @Override
    public long nextLongId(PersistentObjectInfo info) throws Exception {
        return nextKeyValue(info);
    }

    @Override
    public String nextStringId(PersistentObjectInfo info) throws Exception {
        return Long.toString(nextKeyValue(info));
    }

    /**
     * 属性配置
     */
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public void setSequenceTableName(String tableName){
        this.sequenceTableName = tableName;
    }
    public void setCacheSize(int size){
        this.cacheSize = size;
    }
    public void setIdGenerationStrategyMap(Map map){
        this.strategyMap = map;
    }

    /**
     * 初始化配置
     */
    protected abstract void init();

    protected abstract long nextKeyValue(PersistentObjectInfo info);

    public String getSequenceTableName(){
        return this.sequenceTableName;
    }

    public PersistentObjectInfo getPersistentObjectInfo(String id, Class persisClass) throws Exception {
        return this.strategyMap.get(persisClass).getPersistentObjectInfo(id);
    }
}
